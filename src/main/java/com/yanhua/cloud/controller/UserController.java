package com.yanhua.cloud.controller;

import com.alibaba.fastjson.JSON;
import com.yanhua.cloud.model.AccessToken;
import com.yanhua.cloud.utils.ECloudUtils;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * Created by zhusc on 2017/8/7.
 */
@Controller
public class UserController extends BaseController {
    @Autowired
    private ECloudUtils eCloudUtils;

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
     * 获取文件列表
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/listFiles", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public List<String> listFiles(HttpServletRequest request, HttpServletResponse response) {
        try {
            logger.info("listFiles------------------------>");
            String appId = eCloudUtils.getAppId();
            String accessToken = getAccessToken(request);
            if (accessToken == null) {
                return null;
            }
            String url = "http://api.189.cn/ChinaTelecom/listFiles.action?app_id=" + appId + "&access_token=" + accessToken + "&mediaAttr=0&mediaType=3&iconOption=0&pageNum=1&pageSize=17&fileType=0&orderBy=filename&descending=false";
            logger.info("listFiles获取我的云盘文件列表url：------->" + url);
            String res = HttpRequestUtils.sendGet(url);
            logger.info("listFiles--->" + res);
            //解析文件列表,获取文件下载地址
            Map jsonMap = (Map) JSON.parse(res);
            Map fileMap = (Map) jsonMap.get("fileList");
            List<Map<String, Object>> mapList = (List<Map<String, Object>>) fileMap.get("file");
            List<String> fileDownloadUrls = new ArrayList<String>();
            for (Map<String, Object> map : mapList) {
                //通过文件id获取文件下载地址
                String getFileInfoUrl = "http://api.189.cn/ChinaTelecom/getFileInfo.action?app_id=" + appId + "&access_token=" + accessToken + "&fileId=" + map.get("id");
                String jspnRes = HttpRequestUtils.sendGet(getFileInfoUrl);
                fileDownloadUrls.add(jspnRes);
            }
            logger.info("视频下载链接的数量-------->" + fileDownloadUrls.size());
            return fileDownloadUrls;
        } catch (Exception e) {
            logger.error("listFiles---------------------->" + e);
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
            //通过文件id删除云盘中该文件
            String appId = eCloudUtils.getAppId();
            String accessToken = getAccessToken(request);
            String getFileInfoUrl = "http://api.189.cn/ChinaTelecom/deleteFile.action?app_id=" + appId + "&access_token=" + accessToken + "&fileId=" + fileId;
            String jsonRes = HttpRequestUtils.sendPost(getFileInfoUrl, null);
            Map jsonMap = (Map) JSON.parse(jsonRes);
            if ("0".equals(jsonMap.get("res_code")))
                return "success";
            else {
                return "error";
            }
        } catch (Exception e) {
            logger.error("deleteFile---------------------->" + e);
            return "error";
        }
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
            String openId = (String) request.getSession().getAttribute("openId");
            String deivceId = (String) request.getSession().getAttribute("deivceId");
            if (StringUtils.isEmpty(deivceId)) {
                return "对不起，你的设备id为空，请求播放无效！";
            }
            if (!"1".equals(request.getSession().getAttribute("bindStatus"))) {
                boolean bindFlag = bindDisk(openId, deivceId);
                if (!bindFlag) {
                    return "对不起，您的天翼云账号未绑定该设备，请求播放无效！";
                }
                request.getSession().setAttribute("bindStatus", "1");
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
            logger.error("deleteFile---------------------->" + e);
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

