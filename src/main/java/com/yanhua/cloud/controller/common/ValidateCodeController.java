/*
 * Copyright(c) 2017-2017, fenxiquan.com, Inc. All rights reserved.
 * 
 * This software is the confidential and proprietary information of fenxiquan, Inc.
 * You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into with fenxiquan.
 */
package com.yanhua.cloud.controller.common;

import com.yanhua.cloud.controller.BaseController;
import com.yanhua.cloud.utils.ValidateCodeUtil;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * ValidateCodeController.java是生成图片验证码。
 *
 * @author jason
 * @version $Id: ValidateCodeController.java, v 0.1 2017-10-30 14:27 jason Exp $$
 */
@RequestMapping(value = "/common")
@Controller
public class ValidateCodeController extends BaseController {

    @RequestMapping(value = "/validateCode")
    public String validateCode(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 设置响应的类型格式为图片格式
        response.setContentType("image/jpeg");
        //禁止图像缓存。
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        HttpSession session = request.getSession();
        ValidateCodeUtil vCode = new ValidateCodeUtil(120, 40, 5, 100);
        session.setAttribute("code", vCode.getCode());
        vCode.write(response.getOutputStream());
        return null;
    }

    @RequestMapping(value = "/checkValidateCode")
    @ResponseBody
    private boolean checkValidateCode(HttpServletRequest request) {
        String code = request.getParameter("code");
        HttpSession session = request.getSession();
        String sessionCode = (String) session.getAttribute("code");
        if (!StringUtils.equalsIgnoreCase(code, sessionCode)) {  //忽略验证码大小写
            return false;
        }
        return true;
    }
}
