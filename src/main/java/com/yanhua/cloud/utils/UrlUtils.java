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
 *
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
 *
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

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * url解析工具类
 * @author Administrator
 * @version $Id: UrlUtils.java, v 0.1 2017-09-08 15:54 Administrator Exp $$
 */
public class UrlUtils {

    /**
     * 根据地址获得数据的字节流
     * @param strUrl 网络连接地址
     * @return
     */
    public static byte[] getByteArrayFromNetByUrl(String strUrl) {
        try {
            URL url = new URL(strUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5 * 1000);
            InputStream inStream = conn.getInputStream();//通过输入流获取图片数据
            byte[] btImg = readInputStream(inStream);//得到图片的二进制数据
            return btImg;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 从输入流中获取数据
     * @param inStream 输入流
     * @return
     * @throws Exception
     */
    public static byte[] readInputStream(InputStream inStream) throws Exception {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, len);
        }
        inStream.close();
        return outStream.toByteArray();
    }

    /**
     * 将图片写入到输出流
     * @param img 图片数据流
     * @param stream 输出流
     */
    public static void writeImageToOutputStream(byte[] img, OutputStream stream) {
        try {
            stream.write(img);
            stream.flush();
            stream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
