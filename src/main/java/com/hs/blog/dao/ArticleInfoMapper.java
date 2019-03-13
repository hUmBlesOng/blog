package com.hs.blog.dao;

import com.hs.blog.entity.ArticleInfo;
import com.hs.blog.entity.ArticleInfoExample;
import java.util.List;

public interface ArticleInfoMapper {
    int deleteByPrimaryKey(Long id);

    long insert(ArticleInfo record);

    long insertSelective(ArticleInfo record);

    List<ArticleInfo> selectByExample(ArticleInfoExample example);

    ArticleInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ArticleInfo record);

    int updateByPrimaryKey(ArticleInfo record);
}