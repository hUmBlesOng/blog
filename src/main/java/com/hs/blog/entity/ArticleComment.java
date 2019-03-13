package com.hs.blog.entity;

import java.util.Date;

/**
 *
 * TODO: 文章评论POJO类
 *
 * @author 83998
 * @date 2019/3/4 16:39
 */

public class ArticleComment {
    private Long id;

    /**
     * 文章ID
     */
    private Long articleId;

    /**
     * 对应的留言ID
     */
    private Long commentId;

    /**
     * 创建时间
     */
    private Date createBy;

    /**
     * 是否有效，默认为1有效，置0无效
     * 用来删除评论
     */
    private Boolean isEffective;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public Date getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Date createBy) {
        this.createBy = createBy;
    }

    public Boolean getIsEffective() {
        return isEffective;
    }

    public void setIsEffective(Boolean isEffective) {
        this.isEffective = isEffective;
    }
}