package com.hs.blog.entity;

import java.util.Date;

/**
 *
 * TODO: 文章内容POJO类
 *
 * @author 83998
 * @date 2019/3/4 16:39
 */

public class ArticleInfo {
    private Long id;

    /**
     * 文章标题
     */
    private String title;

    /**
     * 文章简介，默认100个汉字以内
     */
    private String summary;

    /**
     * 文章是否置顶，0为否，1为是
     */
    private Boolean isTop;

    /**
     * 文章访问量
     */
    private Integer traffic;

    /**
     * 创建时间
     */
    private Date createBy;

    /**
     * 修改时间
     */
    private Date modifiedBy;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary == null ? null : summary.trim();
    }

    public Boolean getIsTop() {
        return isTop;
    }

    public void setIsTop(Boolean isTop) {
        this.isTop = isTop;
    }

    public Integer getTraffic() {
        return traffic;
    }

    public void setTraffic(Integer traffic) {
        this.traffic = traffic;
    }

    public Date getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Date createBy) {
        this.createBy = createBy;
    }

    public Date getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(Date modifiedBy) {
        this.modifiedBy = modifiedBy;
    }
}