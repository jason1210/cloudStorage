/*
 * Copyright(c) 2017-2018, fenxiquan.com, Inc. All rights reserved.
 * 
 * This software is the confidential and proprietary information of fenxiquan, Inc.
 * You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into with fenxiquan.
 */
package com.yanhua.cloud.utils;

import com.lowagie.text.pdf.BaseFont;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;

/**
 * PdfUtil.java是财数FOF分析平台的html2pdf工具类。
 *
 * @author jason
 * @version $Id: PdfUtil.java, v 0.1 2018-01-25 17:52 jason Exp $$
 */
public class PdfUtil {
    protected static Logger logger = LoggerFactory.getLogger(PdfUtil.class);

    /**
     * @param htmlFile html文件存储路径
     * @param pdfFile 生成的pdf文件存储路径
     * @param chineseFontPath 中文字体存储路径
     */
    public static void html2pdf(String htmlFile, String pdfFile, String chineseFontPath) {
        // step 1
        String url;
        OutputStream os = null;
        try {
            url = new File(htmlFile).toURI().toURL().toString();
            os = new FileOutputStream(pdfFile);
            ITextRenderer renderer = new ITextRenderer();
            renderer.setDocument(url);
            // 解决中文不显示问题
            ITextFontResolver fontResolver = renderer.getFontResolver();
            fontResolver.addFont(chineseFontPath, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);

            renderer.layout();
            renderer.createPDF(os);
        } catch (MalformedURLException e) {
            logger.warn(e.toString(), e);
        } catch (FileNotFoundException e) {
            logger.warn(e.toString(), e);
        } catch (com.lowagie.text.DocumentException e) {
            logger.warn(e.toString(), e);
        } catch (IOException e) {
            logger.warn(e.toString(), e);
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    logger.warn(e.toString(), e);
                }
            }
        }
    }

    public static void main(String[] args) {
        try {
            //html文件路径
            String htmlFilePath = "E:\\projects\\learn\\spring-boot\\springboot-learn\\springboot-learn\\springboot-poi\\src\\main\\resources\\templates\\index.html";
            // 中文字体存储路径
            String chineseFontPath = "E:\\projects\\learn\\spring-boot\\springboot-learn\\springboot-learn\\springboot-poi\\src\\main\\resources\\Fonts\\simsun.ttc";
            // html转pdf
            html2pdf(htmlFilePath, "E:\\projects\\learn\\spring-boot\\springboot-learn\\springboot-learn\\springboot-poi\\src\\main\\resources\\templates\\index.pdf", chineseFontPath);
            System.out.println("转换成功！");
        } catch (Exception e) {
            logger.error("html转换为pdf失败", e);
        }
    }
}
