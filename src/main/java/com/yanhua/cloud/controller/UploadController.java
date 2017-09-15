package com.yanhua.cloud.controller;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Administrator
 * @version $Id: UploadController.java, v 0.1 2017-09-07 14:41 Administrator Exp $$
 */
@Controller
public class UploadController extends BaseController {
    @PostMapping(value = "upload")
    @ResponseBody
    public String upload(HttpServletRequest request, @RequestParam("file") MultipartFile file) {
        try {
            logger.info("开始上传");
            if (!file.isEmpty()) {
                String originalFileName = file.getOriginalFilename();
                String fileType = originalFileName.substring(originalFileName.lastIndexOf("."));
                //.csv文件上传
                if (fileType.equalsIgnoreCase(".csv")) {
                    String path = request.getSession().getServletContext().getRealPath("upload") + "\\";
                    String fileName = System.currentTimeMillis() + fileType;
                    String pathname = path + fileName;
                    File localFile = new File(pathname);
                    logger.info("upload file {} save to local {} ", fileName, localFile.getAbsoluteFile());
                    FileUtils.writeByteArrayToFile(localFile, file.getBytes());
                    return pathname;
                }
            }
        } catch (IOException e) {

        }
        return null;
    }

    @RequestMapping(value = "loadCsv")
    public String loadCsv(HttpServletRequest request) {
        String fileUrl = request.getParameter("file_url");
        File csv = new File(fileUrl);  // CSV文件路径
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(csv));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String line = "";
        String everyLine = "";
        try {
            List<String> allString = new ArrayList<String>();
            while ((line = br.readLine()) != null)  //读取到的内容给line变量
            {
                everyLine = line;
                System.out.println(everyLine);
                allString.add(everyLine);
            }
            System.out.println("csv表格中所有行数：" + allString.size());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}
