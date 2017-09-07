/**
 * Class
 * chenxitech.cn Inc.
 * Copyright (c) 2017-2017 All Rights Reserved.
 *
 * chenxitech.cn Inc.
 * Copyright (c) 2017-2017 All Rights Reserved.
 *
 * chenxitech.cn Inc.
 * Copyright (c) 2017-2017 All Rights Reserved.
 *
 * chenxitech.cn Inc.
 * Copyright (c) 2017-2017 All Rights Reserved.
 */

/**
 * chenxitech.cn Inc.
 * Copyright (c) 2017-2017 All Rights Reserved.
 */
package com.yanhua.cloud.utils;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Administrator
 * @version $Id: FileUtils.java, v 0.1 2017-09-07 14:45 Administrator Exp $$
 */
public class FileUtils {
    public static void main(String[] args) {
        String fileUrl = "E:\\workspace\\personal\\cloudStorage\\src\\main\\webapp\\upload\\1.csv";
        BufferedReader br = null;
        try {
            //csv文件默认编码为ANSI，这里出现乱码主要是编码不一致问题
            DataInputStream in = new DataInputStream(new FileInputStream(new File(fileUrl)));
            br = new BufferedReader(new InputStreamReader(in, "GBK"));//这里如果csv文件编码格式是utf-8,改成utf-8即可
            String line = "";
            String everyLine = "";
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
    }

}
