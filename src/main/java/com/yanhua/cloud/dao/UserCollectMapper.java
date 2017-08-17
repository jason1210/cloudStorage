package com.yanhua.cloud.dao;

import com.yanhua.cloud.model.UserCollect;

import java.util.List;

public interface UserCollectMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserCollect record);

    int insertSelective(UserCollect record);

    UserCollect selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserCollect record);

    int updateByPrimaryKey(UserCollect record);

    int count(UserCollect userCollect);

    /**
     * 通过收藏者openId获取该用户的收藏列表
     *
     * @param collectOpenId
     * @return
     */
    List<UserCollect> selectByCollectOpenId(String collectOpenId);

    /**
     * 通过文件id删除相关记录
     */
    int delete(UserCollect record);
}