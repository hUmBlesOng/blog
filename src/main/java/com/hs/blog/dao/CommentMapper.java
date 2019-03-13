package com.hs.blog.dao;

import com.hs.blog.entity.Comment;
import com.hs.blog.entity.CommentExample;
import java.util.List;

public interface CommentMapper {
    int deleteByPrimaryKey(Long id);

    Long insert(Comment record);

    Long insertSelective(Comment record);

    List<Comment> selectByExample(CommentExample example);

    Comment selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Comment record);

    int updateByPrimaryKey(Comment record);
}