package com.hs.blog.service.impl;

import com.hs.blog.dao.ArticleCommentMapper;
import com.hs.blog.dao.CommentMapper;
import com.hs.blog.dto.ArticleCommentDto;
import com.hs.blog.entity.ArticleComment;
import com.hs.blog.entity.ArticleCommentExample;
import com.hs.blog.entity.Comment;
import com.hs.blog.entity.CommentExample;
import com.hs.blog.mapper.ArticleCommentDTOMapper;
import com.hs.blog.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO: 评论
 *
 * @author 83998
 * @date 2019/3/5 18:35
 */
@Service
public class CommentServiceImpl implements ICommentService {

    @Autowired
    CommentMapper commentMapper;
    @Autowired
    ArticleCommentMapper articleCommentMapper;

    @Autowired
    ArticleCommentDTOMapper articleCommentDTOMapper;

    /**
     * 增加一条留言信息
     *
     * @param comment
     */
    @Override
    @Transactional
    public void addComment(Comment comment) {
        commentMapper.insertSelective(comment);
    }

    /**
     * 增加一条文章评论信息
     *
     * @param articleCommentDto
     */
    @Override
    @Transactional
    public void addArticleComment(ArticleCommentDto articleCommentDto) {
        // 先增加Comment留言数据
        Comment comment = new Comment();
        comment.setIp(articleCommentDto.getIp());
        comment.setName(articleCommentDto.getName());
        comment.setEmail(articleCommentDto.getEmail());
        comment.setContent(articleCommentDto.getContent());
        commentMapper.insertSelective(comment);
        Long commentId = comment.getId();

                // 再更新tbl_article_comment作关联
        ArticleComment articleComment = new ArticleComment();
        articleComment.setCommentId(commentId);
        articleComment.setArticleId(articleCommentDto.getArticleId());
        articleCommentMapper.insertSelective(articleComment);
    }

    /**
     * 通过留言ID删除一条数据
     * 说明：并不是直接删除数据库中的数据而是直接将isEffective字段置为false
     *
     * @param id
     */
    @Override
    @Transactional
    public void deleteCommentById(Long id) {
        Comment comment = commentMapper.selectByPrimaryKey(id);
        comment.setIsEffective(false);
        commentMapper.updateByPrimaryKeySelective(comment);
    }

    /**
     * 删除文章评论信息
     * 说明：说明：并不是直接删除数据库中的数据而是直接将isEffective字段置为false
     * 注意：这里需要设置两个表的字段
     *
     * @param id tbl_article_comment表主键
     */
    @Override
    @Transactional
    public void deleteArticleCommentById(Long id) {
        // 设置ArticleComment表中的字段为false
        ArticleComment articleComment = articleCommentMapper.selectByPrimaryKey(id);
        articleComment.setIsEffective(false);
        articleCommentMapper.updateByPrimaryKeySelective(articleComment);
        // 删除Comment表中对应的数据
        deleteCommentById(articleComment.getCommentId());
    }

    /**
     * 列举返回所有的留言信息
     *
     * @return
     */
    @Override
    public List<Comment> listAllComment() {
        // 无条件查询即返回所有
        CommentExample example = new CommentExample();
        return commentMapper.selectByExample(example);
    }

    /**
     * 获取对应文章下的所有评论信息
     * 说明：需要返回一个自己封装好的用来与前端交互的ArticleCommentDto集合
     *
     * @param id 文章ID
     * @return
     */
    @Override
    public List<ArticleCommentDto> listAllArticleCommentById(Long id) {
        List<ArticleCommentDto> comments = new ArrayList<>();
        ArticleCommentExample example = new ArticleCommentExample();
        example.or().andArticleIdEqualTo(id);
        List<ArticleComment> articleComments = articleCommentMapper.selectByExample(example);
        // 填充对应的评论信息
        for (ArticleComment articleComment : articleComments) {
            if (articleComment.getIsEffective()) {
                // 查询对应的评论信息
                Comment comment = commentMapper.selectByPrimaryKey(articleComment.getCommentId());
                comments.add(articleCommentDTOMapper.createArticleCommentDto(comment,articleComment));
            }
        }
        return comments;
    }
}
