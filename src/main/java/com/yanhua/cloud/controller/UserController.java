package com.yanhua.cloud.controller;

import com.alibaba.fastjson.JSON;
import com.yanhua.cloud.model.AccessToken;
import com.yanhua.cloud.model.UserCollect;
import com.yanhua.cloud.result.FileModel;
import com.yanhua.cloud.service.UserCollectService;
import com.yanhua.cloud.utils.HttpRequestUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;


/**
 * Created by zhusc on 2017/8/7.
 */
@Controller
public class UserController extends BaseController {

    @Autowired
    private UserCollectService userCollectService;

    /**
     * 天翼云授权接口
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/login", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String login(HttpServletRequest request, HttpServletResponse response) {
        logger.info("login------------------------>");
        //检测是否登录云盘
        String accessToken = getAccessToken(request);
        if (null == accessToken) {
            String appId = eCloudUtils.getAppId();
            String redirectUri = eCloudUtils.getRedirectUri();
            String appSecret = eCloudUtils.getAppSecret();
            //直接获取令牌
            String url = "https://oauth.api.189.cn/emp/oauth2/v3/authorize?app_id=" + appId + "&app_secret=" + appSecret + "&redirect_uri=" + redirectUri + "&response_type=token";
            logger.info("login登录云盘授权url：----->" + url);
            String res = HttpRequestUtils.sendGet(url);
            logger.info(res);
            return res;
        } else {
            return "success";
        }
    }

    /**
     * 天翼云回调接口
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/redirect")
    public String redirect(HttpServletRequest request, HttpServletResponse response) {
        logger.info("redirect------------------------>");
        try {
            String resCode = request.getParameter("res_code");//标准返回码。返回0表示成功；其他表示调用出错或异常。
            String resMessage = request.getParameter("res_message");//返回码描述信息
            String accessToken = request.getParameter("access_token");//获取到的AT访问令牌
            String expiresIn = request.getParameter("expires_in");//AT访问令牌的有效期（以秒为单位）
            String openId = request.getParameter("open_id");//它是用户的唯一标识，根据APPID以及天翼账号用户标识生成，即不同的APPID下，同一个天翼账号用户标识生成的OpenID是不一样的
            logger.info("redirect---------------->resCode-->" + resCode + ",resMessage-->" + resMessage + ",accessToken-->" + accessToken + ",openId-->" + openId);
            if ("0".equals(resCode)) {
                HttpSession session = request.getSession();
                AccessToken token = new AccessToken();
                token.setAccessToken(accessToken);
                token.setExpiresIn(System.currentTimeMillis() / 1000 + Long.parseLong(expiresIn));
                session.setAttribute("accessToken", token);
                session.setAttribute("openId", openId);
                try {
                    //登录云盘成功后，即与设备绑定
                    String deivceId = (String) request.getSession().getAttribute("deivceId");
                    if (StringUtils.isNotEmpty(deivceId)) {
                        boolean bindFlag = checkBindDisk(openId, deivceId);
                        if (!bindFlag) {
                            bindFlag = bindDisk(openId, deivceId);
                            logger.info("用户云盘与设备绑定结果：---------" + bindFlag);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return "myDisk";
            } else {
                return "index";
            }
        } catch (Exception e) {
            logger.error("redirect---------------------->" + e);
            return "error/404";
        }
    }

    /**
     * 获取我的云盘文件列表
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/diskFilelist", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public List<FileModel> listDiskFiles(HttpServletRequest request, HttpServletResponse response) {
        try {
            logger.info("diskFilelist------------------------>");
            String accessToken = getAccessToken(request);
            if (accessToken == null) {
                return null;
            }
            //获取该用户云盘上的文件列表
            String collectOpenId = (String) request.getSession().getAttribute("openId");
            List<FileModel> diskFiles = getFileInfoListByAccessToken(accessToken, collectOpenId);
            String appId = eCloudUtils.getAppId();
            //获取该用户收藏的文件列表
            List<FileModel> collectFiles = userCollectService.getCollectFiles(appId, collectOpenId);
            if (null != collectFiles && collectFiles.size() > 0) {
                diskFiles.addAll(collectFiles);
            }
            logger.info("视频下载链接的数量-------->" + diskFiles.size());
            return diskFiles;
        } catch (Exception e) {
            logger.error("diskFilelist---------------------->" + e);
            return null;
        }
    }

    /**
     * 删除文件
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/deleteFile")
    @ResponseBody
    public String deleteFile(HttpServletRequest request, HttpServletResponse response) {
        try {
            logger.info("deleteFile------------------------>");
            String fileId = request.getParameter("fileId");
            logger.info("文件ID------------------>" + fileId);
            String producerOpenId = request.getParameter("producerOpenId");
            String collectorOpenId = (String) request.getSession().getAttribute("openId");//当前用户openId
            int res = 0;
            if (producerOpenId.equals(collectorOpenId)) {
                //通过文件id删除云盘中该文件
                String appId = eCloudUtils.getAppId();
                String accessToken = getAccessToken(request);
                String getFileInfoUrl = "http://api.189.cn/ChinaTelecom/deleteFile.action?app_id=" + appId + "&access_token=" + accessToken + "&fileId=" + fileId;
                String jsonRes = HttpRequestUtils.sendPost(getFileInfoUrl, null);
                Map jsonMap = (Map) JSON.parse(jsonRes);
                if ("0".equals(jsonMap.get("res_code").toString())) {
                    res = 1;
                }
            } else {
                //删除收藏该文件的记录
                UserCollect collect = new UserCollect();
                collect.setOpenIdCollector(collectorOpenId);
                collect.setOpenIdProducer(producerOpenId);
                collect.setFileId(fileId);
                res = userCollectService.delete(collect);
            }
            if (1 == res) {
                return "success";
            }
        } catch (Exception e) {
            logger.error("deleteFile---------------------->" + e);
        }
        return "error";
    }

    /**
     * 收藏文件
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/collectFile", produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String collectFile(HttpServletRequest request, HttpServletResponse response) {
        try {
            logger.info("collectFile------------------------>");
            String accessToken = getAccessToken(request);
            if (null == accessToken) {
                return "对不起，请先登录你的天翼云盘！";
            }
            String fileId = request.getParameter("fileId");
            String producerOpenId = request.getParameter("producerOpenId");
            String collectorOpenId = (String) request.getSession().getAttribute("openId");//当前用户openId
            UserCollect userCollect = new UserCollect();
            userCollect.setFileId(fileId);
            userCollect.setOpenIdProducer(producerOpenId);
            userCollect.setOpenIdCollector(collectorOpenId);
            int res = userCollectService.save(userCollect);
            if (1 == res) {
                return "success";
            }
        } catch (Exception e) {
            logger.error("collectFile---------------------->" + e);
        }
        return "error";
    }

    /**
     * 投屏播放
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/play", produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String play(HttpServletRequest request) {
        logger.info("play------------------------>");
        try {
            String loginStatus = request.getParameter("loginStatus");
            String openId = (String) request.getSession().getAttribute("openId");
            String deivceId = (String) request.getSession().getAttribute("deivceId");
            if (StringUtils.isEmpty(deivceId)) {
                return "对不起，你的设备id为空，请求播放无效！";
            }
            if ("1".equals(loginStatus)) {//1代表需要登录云盘授权后才可以投屏；0则不需要
                String accessToken = getAccessToken(request);
                if (null == accessToken) {
                    return "对不起，请先登录你的天翼云盘！";
                }
                String bindStatus = (String) request.getSession().getAttribute("bindStatus");
                if (!"1".equals(bindStatus)) {
                    boolean bindFlag = bindDisk(openId, deivceId);
                    if (!bindFlag) {
                        return "对不起，您的天翼云账号未绑定该设备，请求播放无效！";
                    }
                    request.getSession().setAttribute("bindStatus", "1");
                }
            }
            //进行投屏播放
            String fileDownloadUrl = request.getParameter("fileDownloadUrl");
            String fileName = request.getParameter("fileName");
            String msg = "{\"action\": \"play\",\"data\": {\"assetname\":\"" + fileName + "\", \"playurl\":\"" + fileDownloadUrl + "\"}}";
            String playUrl = "http://183.131.15.28/msg?deivceId=" + deivceId + "&weipid=" + openId + "&msg=" + URLEncoder.encode(msg, "UTF-8");
            String jsonRes = HttpRequestUtils.sendGet(playUrl);
            logger.info("请求投屏url------------------>" + playUrl);
            logger.info("播放返回结果信息------------------>" + jsonRes);
            Map jsonMap = (Map) JSON.parse(jsonRes);
            if ("SUCESS".equals(jsonMap.get("message"))) {
                return "success";
            } else {
                return "error";
            }
        } catch (Exception e) {
            logger.error("play---------------------->" + e);
            return "error";
        }
    }

    /**
     * 获取accessToken
     *
     * @param request
     * @return
     */
    public String getAccessToken(HttpServletRequest request) {
        //判断accessToken是否过期
        AccessToken token = (AccessToken) request.getSession().getAttribute("accessToken");
        if (token != null) {
            Long expireTime = token.getExpiresIn();
            if ((expireTime - System.currentTimeMillis() / 1000) > 0) {
                return token.getAccessToken();
            }
        }
        return null;
    }

    /**
     * 云盘和电视机绑定
     *
     * @param openId
     * @return
     */
    public boolean bindDisk(String openId, String deviceId) {
        String bindUrl = "http://183.131.15.28/bind?deivceId=" + deviceId + "&weipid=" + openId;
        String jsonRes = HttpRequestUtils.sendGet(bindUrl);
        Map jsonMap = (Map) JSON.parse(jsonRes);
        if ("SUCESS".equals(jsonMap.get("message"))) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 是否绑定电视机
     *
     * @param openId
     * @return
     */
    public boolean checkBindDisk(String openId, String deivceId) {
        String queryBindUrl = "http://183.131.15.28/query?weipid=" + openId + "&deivceId=" + deivceId;
        String jsonRes = HttpRequestUtils.sendGet(queryBindUrl);
        Map jsonMap = (Map) JSON.parse(jsonRes);
        if ("SUCESS".equals(jsonMap.get("message"))) {
            List list = (List) jsonMap.get("data");
            if (list != null && list.size() > 0)
                return true;
        }
        return false;
    }
}

