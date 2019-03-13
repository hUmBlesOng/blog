package com.hs.blog.entity;

import java.util.Date;

/**
 *
 * TODO: 留言评论POJO类
 *
 * @author 83998
 * @date 2019/3/4 16:39
 */

public class Comment {
    private Long id;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 创建时间
     */
    private Date createBy;

    /**
     * 邮箱，用于回复消息
     */
    private String email;

    /**
     * 用户自己定义的名称
     */
    private String name;

    /**
     * 评论人ip地址
     */
    private String ip;

    /**
     * 是否有效，默认为1有效，置0无效
     * 可删除用户
     */
    private Boolean isEffective;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Date getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Date createBy) {
        this.createBy = createBy;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public Boolean getIsEffective() {
        return isEffective;
    }

    public void setIsEffective(Boolean isEffective) {
        this.isEffective = isEffective;
    }
}