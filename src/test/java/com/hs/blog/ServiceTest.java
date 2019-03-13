package com.hs.blog;

import com.hs.blog.entity.ArticleInfo;
import com.hs.blog.service.IArticleService;
import com.hs.blog.service.ISysService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * TODO: service单元测试
 *
 * @author 83998
 * @date 2019/3/4 17:11
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceTest {

    @Autowired
    ISysService iSysService;
    @Autowired
    IArticleService iArticleService;

    @Test
    public void sys(){
        ArticleInfo articleInfo = new ArticleInfo();
        articleInfo.setTitle("1");
        articleInfo.setIsTop(true);
        iArticleService.ceshi(articleInfo);
        System.out.println(articleInfo.getId());
    }
}
