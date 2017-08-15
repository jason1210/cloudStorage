package com.yanhua.cloud.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.io.PrintWriter;

/**
 * Created by jasonzhu on 2017/2/7.
 */
@CrossOrigin
public class BaseController {
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
}
