package com.hs.blog.service;

import com.hs.blog.entity.SysLog;
import com.hs.blog.entity.SysView;

import java.util.List;

/**
 * TODO: 日志/访问统计等系统相关Service
 *
 * @author 83998
 * @date 2019/3/4 22:34
 */
public interface ISysService {

    /**
     * 增加一条日志信息
     * @param sysLog
     */
    void addLog(SysLog sysLog);

    /**
     * 增加一条访问量
     * @param sysView
     */
    void addView(SysView sysView);

    /**
     * 获取日志的总数量
     * @return
     */
    int getLogCount();

    /**
     * 返回当前网站的访问量
     * @return
     */
    int getViewCount();

    /**
     * 返回所有的日志信息
     * @return
     */
    List<SysLog> listAllLog();

    /**
     * 返回所有的访问信息
     * @return
     */
    List<SysView> listAllView();

    void ceshi();
}
