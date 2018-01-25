/*
 * Copyright(c) 2017-2018, fenxiquan.com, Inc. All rights reserved.
 * 
 * This software is the confidential and proprietary information of fenxiquan, Inc.
 * You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into with fenxiquan.
 */
package com.yanhua.cloud.utils;

/**
 * Test.java是财数FOF分析平台的。
 *
 * @author jason
 * @version $Id: Test.java, v 0.1 2018-01-06 12:02 jason Exp $$
 */
public class Test {
    public static void main(String[] args) {

        Person p = new Person();
        p.setName("21");
        Man m = (Man) p;
        System.out.print(m);
    }
}
