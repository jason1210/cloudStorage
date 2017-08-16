package com.yanhua.cloud.dao;

import com.yanhua.cloud.model.Producer;

import java.util.List;

public interface ProducerMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Producer record);

    int insertSelective(Producer record);

    Producer selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Producer record);

    int updateByPrimaryKey(Producer record);

    /**
     * 获取所以的云盘提供者用户
     *
     * @return
     */
    List<Producer> getAllProducers();

    /**
     * 通过openId获取该用户的账号信息
     *
     * @param producerOpenId
     * @return
     */
    Producer selectByProducerOpenId(String producerOpenId);

}