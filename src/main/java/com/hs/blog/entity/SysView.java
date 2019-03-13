package com.hs.blog.entity;

import java.util.Date;

/**
 *
 * TODO: 浏览量POJO类
 *
 * @author 83998
 * @date 2019/3/4 16:39
 */

public class SysView {
    private Long id;

    /**
     * 访问IP
     */
    private String ip;

    /**
     * 访问时间
     */
    private Date createBy;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public Date getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Date createBy) {
        this.createBy = createBy;
    }
}