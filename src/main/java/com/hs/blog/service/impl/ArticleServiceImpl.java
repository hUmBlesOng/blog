package com.hs.blog.service.impl;

import com.hs.blog.dao.*;
import com.hs.blog.dto.ArticleDto;
import com.hs.blog.dto.ArticleWithPictureDto;
import com.hs.blog.entity.*;
import com.hs.blog.mapper.ArticleCategoryDTOMapper;
import com.hs.blog.mapper.ArticleDTOMapper;
import com.hs.blog.mapper.ArticleWithPictureDTOMapper;
import com.hs.blog.service.IArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * TODO: 文章
 *
 * @author 83998
 * @date 2019/3/5 18:34
 */
@Service
public class ArticleServiceImpl implements IArticleService {

    @Autowired
    ArticleInfoMapper articleInfoMapper;
    @Autowired
    ArticlePictureMapper articlePictureMapper;
    @Autowired
    ArticleCategoryMapper articleCategoryMapper;
    @Autowired
    ArticleContentMapper articleContentMapper;
    @Autowired
    CategoryInfoMapper categoryInfoMapper;

    @Autowired
    ArticleWithPictureDTOMapper articleWithPictureDTOMapper;
    @Autowired
    ArticleCategoryDTOMapper articleCategoryDTOMapper;
    @Autowired
    ArticleDTOMapper articleDTOMapper;

    private static byte MAX_LASTEST_ARTICLE_COUNT = 5;

    /**
     * 增加一篇文章信息
     * 说明：需要对应的写入tbl_article_picture/tbl_article_content/tbl_article_category表
     * 注意：使用的是Article封装类
     *
     * @param articleDto 文章封装类
     */
    @Override
    @Transactional
    public void addArticle(ArticleDto articleDto) {
        Date nowDate = new Date();

        // 插入文章信息表
        ArticleInfo articleInfo = new ArticleInfo();
        articleInfo.setTitle(articleDto.getTitle());
        articleInfo.setSummary(articleDto.getSummary());
        articleInfo.setCreateBy(nowDate);
        articleInfo.setIsTop(articleDto.getIsTop());
        articleInfoMapper.insertSelective(articleInfo);
        Long articleId = articleInfo.getId();

                // 增加文章题图信息 - pictureUrl/articleId
        ArticlePicture articlePicture = new ArticlePicture();
        articlePicture.setPictureUrl(articleDto.getPictureUrl());
        articlePicture.setArticleId(articleId);
        articlePicture.setCreateBy(nowDate);
        articlePictureMapper.insertSelective(articlePicture);

        // 增加文章内容信息表 - content/articleId
        ArticleContent articleContent = new ArticleContent();
        articleContent.setContent(articleDto.getContent());
        articleContent.setArticleId(articleId);
        articleContent.setCreateBy(nowDate);
        articleContentMapper.insertSelective(articleContent);

        // 增加文章分类表 - articleId/categoryId
        ArticleCategory articleCategory = new ArticleCategory();
        articleCategory.setCategoryId(articleDto.getCategoryId());
        articleCategory.setArticleId(articleId);
        articleCategory.setCreateBy(nowDate);
        articleCategoryMapper.insertSelective(articleCategory);

        // 该分类的对应文章的数量要加1
        CategoryInfo categoryInfo = categoryInfoMapper.selectByPrimaryKey(articleDto.getCategoryId());
        categoryInfo.setNumber((byte) (categoryInfo.getNumber() + 1));
        categoryInfoMapper.updateByPrimaryKeySelective(categoryInfo);
    }

    /**
     * 删除一篇文章
     * 说明：需要对应删除tbl_article_picture/tbl_article_content/tbl_article_category表中的内容
     *
     * @param id
     */
    @Override
    @Transactional
    public void deleteArticleById(Long id) {
        // 获取对应的文章信息
        ArticleDto articleDto = getOneById(id);
        // 删除文章信息中的数据
        articleInfoMapper.deleteByPrimaryKey(articleDto.getId());
        // 删除文章题图信息数据
        articlePictureMapper.deleteByPrimaryKey(articleDto.getArticlePictureId());
        // 删除文章内容信息表
        articleContentMapper.deleteByPrimaryKey(articleDto.getArticleContentId());
        // 删除文章分类信息表
        articleCategoryMapper.deleteByPrimaryKey(articleDto.getArticleCategoryId());
    }

    /**
     * 更新文章信息
     * 说明：需要对应更改tbl_article_picture/tbl_article_content/tbl_article_category表中的内容
     * 注意：我们使用的是封装好的Article文章信息类
     *
     * @param articleDto 自己封装的Article信息类
     */
    @Override
    @Transactional
    public void updateArticle(ArticleDto articleDto) {
        // 获取文章Id
        Long articleId = articleDto.getId();

        // 更新文章信息中的数据
        ArticleInfo articleInfo = new ArticleInfo();
        articleInfo.setId(articleDto.getId());
        articleInfo.setTitle(articleDto.getTitle());
        articleInfo.setSummary(articleDto.getSummary());
        articleInfo.setIsTop(articleDto.getIsTop());
        articleInfo.setTraffic(articleDto.getTraffic());
        articleInfoMapper.updateByPrimaryKeySelective(articleInfo);

        // 更新文章题图信息数据
        ArticlePictureExample pictureExample = new ArticlePictureExample();
        pictureExample.createCriteria().andArticleIdEqualTo(articleId);
        ArticlePicture articlePicture = articlePictureMapper.selectByExample(pictureExample).get(0);
        articlePicture.setPictureUrl(articleDto.getPictureUrl());
        articlePictureMapper.updateByPrimaryKeySelective(articlePicture);

        // 更新文章内容信息数据
        ArticleContentExample contentExample = new ArticleContentExample();
        contentExample.createCriteria().andArticleIdEqualTo(articleId);
        ArticleContent articleContent = articleContentMapper.selectByExample(contentExample).get(0);
        articleContent.setContent(articleDto.getContent());
        articleContentMapper.updateByPrimaryKeySelective(articleContent);

        // 更新文章分类信息表
        ArticleCategoryExample categoryExample = new ArticleCategoryExample();
        categoryExample.createCriteria().andArticleIdEqualTo(articleId);
        ArticleCategory articleCategory = articleCategoryMapper.selectByExample(categoryExample).get(0);
        articleCategory.setCategoryId(articleDto.getCategoryId());
        articleCategoryMapper.updateByPrimaryKeySelective(articleCategory);
    }

    /**
     * 更改文章的分类信息
     * @param articleId
     * @param categoryId
     */
    @Override
    @Transactional
    public void updateArticleCategory(Long articleId, Long categoryId) {
        ArticleCategoryExample example = new ArticleCategoryExample();
        example.createCriteria().andArticleIdEqualTo(articleId);
        ArticleCategory articleCategory = articleCategoryMapper.selectByExample(example).get(0);

        // 对应改变分类下的文章数目
        CategoryInfo categoryInfo = categoryInfoMapper.selectByPrimaryKey(articleCategory.getCategoryId());
        categoryInfo.setNumber((byte) (categoryInfo.getNumber() - 1));
        categoryInfoMapper.updateByPrimaryKeySelective(categoryInfo);
        categoryInfo = categoryInfoMapper.selectByPrimaryKey(categoryId);
        categoryInfo.setNumber((byte) (categoryInfo.getNumber() + 1));
        categoryInfoMapper.updateByPrimaryKeySelective(categoryInfo);

        // 更新tbl_article_category表字段
        articleCategory.setCategoryId(categoryId);
        articleCategoryMapper.updateByPrimaryKeySelective(articleCategory);
    }

    @Override
    public ArticleDto getOneById(Long articleId) {

        // 填充文章基础信息
        ArticleInfo articleInfo = articleInfoMapper.selectByPrimaryKey(articleId);

        // 文章访问量要加1
        articleInfo.setTraffic(articleInfo.getTraffic() + 1);
        articleInfoMapper.updateByPrimaryKey(articleInfo);

        // 填充文章内容信息
        ArticleContentExample example = new ArticleContentExample();
        example.createCriteria().andArticleIdEqualTo(articleId);
        ArticleContent articleContent = articleContentMapper.selectByExampleWithBLOBs(example).get(0);

        // 填充文章题图信息
        ArticlePictureExample example1 = new ArticlePictureExample();
        example1.createCriteria().andArticleIdEqualTo(articleId);
        ArticlePicture articlePicture = articlePictureMapper.selectByExample(example1).get(0);

        // 填充文章分类信息
        ArticleCategoryExample example2 = new ArticleCategoryExample();
        example2.createCriteria().andArticleIdEqualTo(articleId);
        ArticleCategory articleCategory = articleCategoryMapper.selectByExample(example2).get(0);

        // 填充文章分类基础信息
        CategoryInfoExample example3 = new CategoryInfoExample();
        example3.createCriteria().andIdEqualTo(articleCategory.getCategoryId());
        CategoryInfo categoryInfo = categoryInfoMapper.selectByExample(example3).get(0);

        ArticleDto articleDto = articleDTOMapper.createArticleDto(articleInfo, articleContent, articleCategory, categoryInfo, articlePicture);
        articleDto.setTraffic(articleInfo.getTraffic() + 1);

        return articleDto;
    }

    /**
     * 通过文章ID获取对应的文章题图信息
     *
     * @param articleId 文章ID
     * @return 文章ID对应的文章题图信息
     */
    @Override
    public ArticlePicture getPictureByArticleId(Long articleId) {
        ArticlePictureExample example = new ArticlePictureExample();
        example.createCriteria().andArticleIdEqualTo(articleId);
        return articlePictureMapper.selectByExample(example).get(0);
    }

    /**
     * 获取所有的文章内容
     *
     * @return 封装好的Article集合
     */
    @Override
    public List<ArticleWithPictureDto> listAll() {
        // 1.先获取所有的数据
        List<ArticleWithPictureDto> articles = listAllArticleWithPicture();

        // 2.然后再对集合进行重排，置顶的文章在前
        LinkedList<ArticleWithPictureDto> list = new LinkedList<>();
        for (int i = 0; i < articles.size(); i++) {
            if (articles.get(i).getIsTop()) {
                list.addFirst(articles.get(i));
            } else {
                list.addLast(articles.get(i));
            }
        }
        articles = new ArrayList<>(list);

        return articles;
    }

    /**
     * 通过分类id返回该分类下的所有文章
     *
     * @param id 分类ID
     * @return 对应分类ID下的所有文章(带题图)
     */
    @Override
    public List<ArticleWithPictureDto> listByCategoryId(Long id) {
        ArticleCategoryExample example = new ArticleCategoryExample();
        example.or().andCategoryIdEqualTo(id);
        List<ArticleCategory> articleCategories = articleCategoryMapper.selectByExample(example);
        List<ArticleWithPictureDto> articles = new ArrayList<>();
        for (ArticleCategory articleCategory : articleCategories) {
            Long articleId = articleCategory.getArticleId();
//            ArticleWithPictureDto articleWithPictureDto = new ArticleWithPictureDto();
//            // 填充文章基础信息
            ArticleInfoExample example1 = new ArticleInfoExample();
            example1.createCriteria().andIdEqualTo(articleId);
            ArticleInfo articleInfo = articleInfoMapper.selectByExample(example1).get(0);
//            articleWithPictureDto.setId(articleInfo.getId());
//            articleWithPictureDto.setTitle(articleInfo.getTitle());
//            articleWithPictureDto.setSummary(articleInfo.getSummary());
//            articleWithPictureDto.setTop(articleInfo.getIsTop());
//            articleWithPictureDto.setTraffic(articleInfo.getTraffic());
//            // 填充文章图片信息
            ArticlePictureExample example2 = new ArticlePictureExample();
            example2.createCriteria().andArticleIdEqualTo(articleInfo.getId());
            ArticlePicture articlePicture = articlePictureMapper.selectByExample(example2).get(0);
//            articleWithPictureDto.setArticlePictureId(articlePicture.getId());
//            articleWithPictureDto.setPictureUrl(articlePicture.getPictureUrl());
            articles.add(articleWithPictureDTOMapper.createArticleWithPictureDto(articleInfo, articlePicture));
        }


        // 对集合进行重排，置顶的文章在前
        LinkedList<ArticleWithPictureDto> list = new LinkedList<>();
        for (int i = 0; i < articles.size(); i++) {
            if (articles.get(i).getIsTop()) {
                list.add(articles.get(i));
            } else {
                list.addLast(articles.get(i));
            }
        }
        articles = new ArrayList<>(list);

        return articles;
    }

    /**
     * 获取最新的文章信息，默认为5篇
     * 说明：listAll默认已经按照id排序了，所以我们只需要返回前五篇就可以了
     * 注意：需要判断当前的文章是否大于5篇
     *
     * @return 返回五篇最新的文章数据
     */
    @Override
    public List<ArticleWithPictureDto> listLastest() {
        // 1.先获取所有的数据
        List<ArticleWithPictureDto> articles = listAllArticleWithPicture();
        // 2.判断是否满足5个的条件
        if (articles.size() >= MAX_LASTEST_ARTICLE_COUNT) {
            // 3.大于5个则返回前五个数据
            List<ArticleWithPictureDto> tempArticles = new ArrayList<>();
            for (int i = 0; i < 5; i++) {
                tempArticles.add(articles.get(i));
            }
            return tempArticles;
        }
        // 4.不大于五个则直接返回
        return articles;
    }

    /**
     * 获取所有的文章信息（带题图）
     *
     * @return
     */
    private List<ArticleWithPictureDto> listAllArticleWithPicture() {
        ArticleInfoExample example = new ArticleInfoExample();
        example.setOrderByClause("id desc");
        // 无添加查询即返回所有
        List<ArticleInfo> articleInfos = articleInfoMapper.selectByExample(example);
        List<ArticleWithPictureDto> articles = new ArrayList<>();
        for (ArticleInfo articleInfo : articleInfos) {
//            ArticleWithPictureDto articleWithPictureDto = new ArticleWithPictureDto();
//            // 填充文章基础信息
//            articleWithPictureDto.setId(articleInfo.getId());
//            articleWithPictureDto.setTitle(articleInfo.getTitle());
//            articleWithPictureDto.setSummary(articleInfo.getSummary());
//            articleWithPictureDto.setTop(articleInfo.getIsTop());
//            articleWithPictureDto.setTraffic(articleInfo.getTraffic());
//            // 填充文章题图信息
            ArticlePictureExample example1 = new ArticlePictureExample();
            example1.or().andArticleIdEqualTo(articleInfo.getId());
            ArticlePicture articlePicture = articlePictureMapper.selectByExample(example1).get(0);
//            articleWithPictureDto.setArticlePictureId(articlePicture.getId());
//            articleWithPictureDto.setPictureUrl(articlePicture.getPictureUrl());
            articles.add(articleWithPictureDTOMapper.createArticleWithPictureDto(articleInfo, articlePicture));
        }
        return articles;
    }

    @Override
    public void ceshi(ArticleInfo articleInfo){
        long l = articleInfoMapper.insertSelective(articleInfo);
        System.out.println(l);
    }

}
