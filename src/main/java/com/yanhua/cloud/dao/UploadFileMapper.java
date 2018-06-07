package com.yanhua.cloud.dao;

import com.yanhua.cloud.model.UploadFile;

import java.util.List;

public interface UploadFileMapper {
    int deleteByPrimaryKey(Long id);

    int insertSelective(UploadFile record);

    List<UploadFile> selectByType(Byte type);

}