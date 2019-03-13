package com.hs.blog.controller;

import com.hs.blog.service.IArticleService;
import com.hs.blog.service.ICategoryService;
import com.hs.blog.service.ICommentService;
import com.hs.blog.service.ISysService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * TODO: 基础抽象Controller类
 */
public abstract class BaseController {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    IArticleService iArticleService;
    @Autowired
    ICommentService iCommentService;
    @Autowired
    ICategoryService iCategoryService;
    @Autowired
    ISysService iSysService;
}
