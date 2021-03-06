/*
 * Copyright(c) 2017-2018, fenxiquan.com, Inc. All rights reserved.
 * 
 * This software is the confidential and proprietary information of fenxiquan, Inc.
 * You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into with fenxiquan.
 */
package com.yanhua.cloud.utils;

/**
 * Man.java是财数FOF分析平台的。
 *
 * @author jason
 * @version $Id: Man.java, v 0.1 2018-01-06 12:01 jason Exp $$
 */
public class Man extends Person {
    private String age;

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Man{" +
                "age='" + age + '\'' +
                '}';
    }
}
