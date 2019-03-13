package com.hs.blog.service;

import com.hs.blog.dto.ArticleCommentDto;
import com.hs.blog.entity.Comment;

import java.util.List;

/**
 * TODO:
 *
 * @author 83998
 * @date 2019/3/5 18:34
 */
public interface ICommentService {

    void addComment(Comment comment);

    void addArticleComment(ArticleCommentDto articleCommentDto);

    void deleteCommentById(Long id);

    void deleteArticleCommentById(Long id);

    List<Comment> listAllComment();

    List<ArticleCommentDto> listAllArticleCommentById(Long id);
}
