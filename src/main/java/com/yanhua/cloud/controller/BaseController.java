package com.yanhua.cloud.controller;

import com.alibaba.fastjson.JSON;
import com.yanhua.cloud.model.Producer;
import com.yanhua.cloud.service.ProducerService;
import com.yanhua.cloud.utils.ECloudUtils;
import com.yanhua.cloud.utils.HttpRequestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by jasonzhu on 2017/8/15.
 */
@CrossOrigin
public class BaseController {
    @Autowired
    protected ECloudUtils eCloudUtils;
    @Autowired
    protected ProducerService producerService;
    protected static Logger logger = LoggerFactory.getLogger(BaseController.class);

    /**
     * 写数据
     *
     * @param outStr   {"result":%1$s}
     * @param outValue 返回页面的code
     * @param out      输出流
     */


    protected void write(String outStr, String outValue, PrintWriter out) {
        String result = String.format(outStr, outValue);
        logger.info("result:" + result);
        out.write(result);
        out.close();
    }

    /**
     * 获取所以的云盘提供者用户
     *
     * @return
     */
    protected List<Producer> getAllProducers() {
        return producerService.getAllProducers();
    }

    /**
     * 通过accessToken获取某个用户的云盘视频列表
     *
     * @param accessToken
     * @return
     */
    protected List<String> getFileInfoListByAccessToken(String accessToken) {
        String appId = eCloudUtils.getAppId();
        String url = "http://api.189.cn/ChinaTelecom/listFiles.action?app_id=" + appId + "&access_token=" + accessToken + "&mediaAttr=0&mediaType=3&iconOption=0&pageNum=1&pageSize=17&fileType=0&orderBy=filename&descending=false";
        logger.info("listFiles获取我的云盘文件列表url：------->" + url);
        String res = HttpRequestUtils.sendGet(url);
        logger.info("listFiles--->" + res);
        //解析文件列表,获取文件下载地址
        Map jsonMap = (Map) JSON.parse(res);
        Map fileMap = (Map) jsonMap.get("fileList");
        List<Map<String, Object>> mapList = (List<Map<String, Object>>) fileMap.get("file");
        List<String> fileInfos = new ArrayList<String>();
        for (Map<String, Object> map : mapList) {
            //通过文件id获取文件下载地址
            String jspnRes = ECloudUtils.getFileInfo(appId, accessToken, map.get("id").toString());
            fileInfos.add(jspnRes);
        }
        return fileInfos;
    }


}
