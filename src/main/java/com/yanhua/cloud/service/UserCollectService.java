package com.yanhua.cloud.service;

import com.alibaba.fastjson.JSON;
import com.yanhua.cloud.dao.ProducerMapper;
import com.yanhua.cloud.dao.UserCollectMapper;
import com.yanhua.cloud.model.Producer;
import com.yanhua.cloud.model.UserCollect;
import com.yanhua.cloud.result.FileModel;
import com.yanhua.cloud.utils.ECloudUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by zhusc on 2017/8/16.
 */
@Service
public class UserCollectService {
    @Autowired
    private UserCollectMapper userCollectMapper;
    @Autowired
    private ProducerMapper producerMapper;

    public int save(UserCollect userCollect) {
        int res = userCollectMapper.count(userCollect);
        if (res == 0) {
            Date now = new Date();
            userCollect.setCreateTime(now);
            userCollect.setUpdateTime(now);
            res = userCollectMapper.insertSelective(userCollect);
        }
        return res;
    }

    public int delete(UserCollect record) {
        return userCollectMapper.delete(record);
    }

    public List<FileModel> getCollectFiles(String appId, String collectOpenId) {
        List<UserCollect> collects = userCollectMapper.selectByCollectOpenId(collectOpenId);
        List<FileModel> collectFiles = new ArrayList<FileModel>();
        for (UserCollect collect : collects) {
            String fileId = collect.getFileId();
            String openIdProducer = collect.getOpenIdProducer();
            Producer producer = producerMapper.selectByProducerOpenId(openIdProducer);
            if (null != producer) {
                String accessToken = producer.getAccessToken();
                String jsonRes = ECloudUtils.getFileInfo(appId, accessToken, fileId);
                if ("" != jsonRes) {
                    Map fileInfomap = (Map) JSON.parse(jsonRes);
                    if ("0".equals(fileInfomap.get("res_code").toString())) {
                        FileModel model = JSON.parseObject(jsonRes, FileModel.class);
                        model.setOpenId(openIdProducer);
                        collectFiles.add(model);
                    }
                }
            }
        }
        return collectFiles;
    }
}
