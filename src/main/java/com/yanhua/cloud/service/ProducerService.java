package com.yanhua.cloud.service;

import com.yanhua.cloud.dao.ProducerMapper;
import com.yanhua.cloud.model.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhusc on 2017/8/15.
 */
@Service
public class ProducerService {
    @Autowired
    private ProducerMapper producerMapper;

    public List<Producer> getAllProducers() {
        return producerMapper.getAllProducers();
    }

    public Producer selectByProducerOpenId(String producerOpenId){
        return producerMapper.selectByProducerOpenId(producerOpenId);
    }
}
