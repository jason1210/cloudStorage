package com.yanhua.cloud.controller;

import com.yanhua.cloud.model.Producer;
import com.yanhua.cloud.result.FileModel;
import com.yanhua.cloud.utils.HttpRequestUtils;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by jasonzhu on 2017/08/08.
 */
@Controller
public class IndexController extends BaseController {

    @RequestMapping(value = "/")
    public String index(HttpServletRequest request, HttpServletResponse response) {
        logger.info("index------------------------>");
        try {
            HttpSession session = request.getSession();
            String deivceId = (String) session.getAttribute("deivceId");
            if (StringUtils.isEmpty(deivceId)) {
                deivceId = request.getParameter("deivceId");
            }
            session.setAttribute("deivceId", deivceId);
            if (StringUtils.isNotEmpty(deivceId)) {
                String msgUrl = "http://183.131.15.28/msg?deivceId=" + deivceId + "&msg=" + URLEncoder.encode("{\"action\": \"binding\"}", "UTF-8");
                logger.info("云盘入口msgUrl------->" + msgUrl);
                String res = HttpRequestUtils.sendGet(msgUrl);
                logger.info("发送消息响应结果：------" + res);
            } else {
                logger.info("deivceId为空！");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "index-welcome";
    }

    @RequestMapping(value = "toIndex")
    public String toIndex(HttpServletRequest request) {
        return "index-welcome";
    }

    /**
     * 获取首页的云盘文件列表
     */
    @RequestMapping(value = "indexFilelist", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public List<FileModel> getAllFileInfoListByAccessToken() {
        List<FileModel> fileModelList = new ArrayList<FileModel>();
        List<Producer> producers = getAllProducers();
        for (Producer p : producers) {
            //通过遍历token获取所有的云盘用户视频列表
            List<FileModel> filelist = getFileInfoListByAccessToken(p.getAccessToken(), p.getOpenIdProducer());
            fileModelList.addAll(filelist);
        }
        return fileModelList;
    }

}
