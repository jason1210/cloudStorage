/*
 * Copyright(c) 2017-2017, fenxiquan.com, Inc. All rights reserved.
 * 
 * This software is the confidential and proprietary information of fenxiquan, Inc.
 * You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into with fenxiquan.
 */
package com.yanhua.cloud.utils;

/**
 * Person.java是财数FOF分析平台的。
 *
 * @author jason
 * @version $Id: Person.java, v 0.1 2017-11-24 14:32 jason Exp $$
 */
public class Person {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                '}';
    }
}
