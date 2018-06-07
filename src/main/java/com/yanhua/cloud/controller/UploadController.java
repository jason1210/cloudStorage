package com.yanhua.cloud.controller;

import com.yanhua.cloud.model.UploadFile;
import com.yanhua.cloud.service.UploadFileService;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
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
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import static com.yanhua.cloud.utils.Constants.SEPARATOR_PATH;
import static org.apache.commons.lang.StringUtils.isNotBlank;

/**
 * @author Administrator
 * @version $Id: UploadController.java, v 0.1 2017-09-07 14:41 Administrator Exp $$
 */
@Controller
public class UploadController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(UploadController.class);

    private static final String uploadDirPath = "/upload/";

    @Autowired
    private UploadFileService uploadFileService;

    @RequestMapping(value = "upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseBody
    public String upload(HttpServletRequest request, @RequestParam("file") MultipartFile file) {
        logger.info("开始上传");
        if (!file.isEmpty()) {
            String originalFileName = file.getOriginalFilename();
            try {
                String fileType = originalFileName.substring(originalFileName.lastIndexOf("."));
                //.csv文件上传
                String path = request.getSession().getServletContext().getRealPath("upload") + SEPARATOR_PATH;
                String fileName = System.currentTimeMillis() + fileType;
                String pathname = path + fileName;
                File localFile = new File(pathname);
                logger.info("upload file {} save to local {} ", fileName, localFile.getAbsoluteFile());
                FileUtils.writeByteArrayToFile(localFile, file.getBytes());
                UploadFile uploadFile = new UploadFile();
                uploadFile.setFileName(originalFileName);
                uploadFile.setFileType((byte) 0);
                uploadFile.setFilePath(uploadDirPath + fileName);
                uploadFile.setCreateTime(new Date());
                if (uploadFileService.save(uploadFile) > 0) {
                    logger.info("写入数据库成功");
                } else {
                    logger.error("写入数据库失败");
                }
                return pathname;
            } catch (Exception e) {
                logger.error("上传文件{}出现异常", originalFileName, e);
            }
        }
        return "error";
    }

    @RequestMapping(value = "get_upload_file")
    @ResponseBody
    public List<UploadFile> upload(HttpServletRequest request) {
        Byte type = 0;
        String typeStr = request.getParameter("type");
        if (isNotBlank(typeStr)) {
            type = Byte.parseByte(typeStr);
        }
        return uploadFileService.getByType(type);
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
