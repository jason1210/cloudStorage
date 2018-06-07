/*
 * Copyright(c) 2017-2018, fenxiquan.com, Inc. All rights reserved.
 *
 * This software is the confidential and proprietary information of fenxiquan, Inc.
 * You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into with fenxiquan.
 */
package com.yanhua.cloud.service;

import com.yanhua.cloud.dao.UploadFileMapper;
import com.yanhua.cloud.model.UploadFile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * UploadFileService.java是财数FOF分析平台的。
 *
 * @author jason
 * @version $Id: UploadFileService.java, v 0.1 2018-06-07 9:00 jason Exp $$
 */
@Service
public class UploadFileService {

    @Autowired
    private UploadFileMapper uploadFileMapper;

    public int save(UploadFile uploadFile) {
        return uploadFileMapper.insertSelective(uploadFile);
    }

    public List<UploadFile> getByType(Byte type) {
        return uploadFileMapper.selectByType(type);
    }
}
