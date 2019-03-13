package com.hs.blog.entity;

import java.util.Date;

/**
 *
 * TODO: 文章内容POJO类
 *
 * @author 83998
 * @date 2019/3/4 16:39
 */

public class ArticleContent {
    private Long id;

    /**
     * 文章ID
     */
    private Long articleId;

    /**
     * 创建时间
     */
    private Date createBy;

    /**
     * 修改时间
     */
    private Date modifieldBy;

    /**
     * 文章内容
     */
    private String content;

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

    public Date getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Date createBy) {
        this.createBy = createBy;
    }

    public Date getModifieldBy() {
        return modifieldBy;
    }

    public void setModifieldBy(Date modifieldBy) {
        this.modifieldBy = modifieldBy;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}