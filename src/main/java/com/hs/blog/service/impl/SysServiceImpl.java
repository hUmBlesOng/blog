package com.hs.blog.service.impl;

import com.hs.blog.dao.SysLogMapper;
import com.hs.blog.dao.SysViewMapper;
import com.hs.blog.entity.*;
import com.hs.blog.service.ISysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * TODO: 日志/访问统计等系统相关Service实现类
 *
 * @author 83998
 * @date 2019/3/4 23:32
 */
@Service
public class SysServiceImpl implements ISysService {

    @Autowired
    SysLogMapper sysLogMapper;
    @Autowired
    SysViewMapper sysViewMapper;

    @Autowired
    User user;

    @Override
    @Transactional
    public void addLog(SysLog sysLog) {
        sysLogMapper.insertSelective(sysLog);
    }

    @Override
    @Transactional
    public void addView(SysView sysView) {
        sysViewMapper.insertSelective(sysView);
    }

    @Override
    public int getLogCount() {
        SysLogExample example = new SysLogExample();
        return sysLogMapper.selectByExample(example).size();
    }

    @Override
    public int getViewCount() {
        SysViewExample example = new SysViewExample();
        return sysViewMapper.selectByExample(example).size();
    }

    @Override
    public List<SysLog> listAllLog() {
        SysLogExample example = new SysLogExample();
        return sysLogMapper.selectByExample(example);
    }

    @Override
    public List<SysView> listAllView() {
        SysViewExample example = new SysViewExample();
        return sysViewMapper.selectByExample(example);
    }

    @Override
    public void ceshi() {
        System.out.println(user);
    }

}
