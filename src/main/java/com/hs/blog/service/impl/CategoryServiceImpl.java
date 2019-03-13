package com.hs.blog.service.impl;

import com.hs.blog.dao.ArticleCategoryMapper;
import com.hs.blog.dao.CategoryInfoMapper;
import com.hs.blog.dto.ArticleCategoryDto;
import com.hs.blog.entity.ArticleCategory;
import com.hs.blog.entity.ArticleCategoryExample;
import com.hs.blog.entity.CategoryInfo;
import com.hs.blog.entity.CategoryInfoExample;
import com.hs.blog.mapper.ArticleCategoryDTOMapper;
import com.hs.blog.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * TODO: 分类
 *
 * @author 83998
 * @date 2019/3/5 18:35
 */
@Service
public class CategoryServiceImpl implements ICategoryService {

    @Autowired
    CategoryInfoMapper categoryInfoMapper;
    @Autowired
    ArticleCategoryMapper articleCategoryMapper;

    @Autowired
    ArticleCategoryDTOMapper articleCategoryDTOMapper;

    /**
     * 增加一条分类
     * @param categoryInfo
     */
    @Override
    @Transactional
    public void addCategory(CategoryInfo categoryInfo) {
        categoryInfo.setCreateBy(new Date());
        categoryInfoMapper.insertSelective(categoryInfo);
    }

    /**
     * 删除一条分类
     * @param id
     */
    @Override
    @Transactional
    public void deleteCategoryById(Long id) {
        // 先删除ArticleCategory表中的相关内容
        ArticleCategoryExample example = new ArticleCategoryExample();
        example.createCriteria().andCategoryIdEqualTo(id);
        List<ArticleCategory> categories = articleCategoryMapper.selectByExample(example);
        for (ArticleCategory articleCategory : categories) {
            articleCategoryMapper.deleteByPrimaryKey(articleCategory.getId());
        }
        // 再删除CategoryInfo表中的内容
        categoryInfoMapper.deleteByPrimaryKey(id);
    }

    /**
     * 修改分类内容
     * @param categoryInfo
     */
    @Override
    @Transactional
    public void updateCategory(CategoryInfo categoryInfo) {
        categoryInfoMapper.updateByPrimaryKeySelective(categoryInfo);
    }

    /**
     * 更改文章对应的分类
     *
     * TODO: 感觉无卵用的代码
     *
     * @param articleCategory
     */
    @Override
    @Transactional
    public void updateArticleCategory(ArticleCategory articleCategory) {
        articleCategoryMapper.updateByPrimaryKeySelective(articleCategory);
    }

    /**
     * 获取一条分类信息数据
     *
     * @param id
     * @return
     */
    @Override
    public CategoryInfo getOneById(Long id) {
        CategoryInfoExample example = new CategoryInfoExample();
        example.createCriteria().andIdEqualTo(id);
        return categoryInfoMapper.selectByExample(example).get(0);
    }

    /**
     * 列举返回所有的分类信息
     *
     * @return
     */
    @Override
    public List<CategoryInfo> listAllCategory() {
        CategoryInfoExample example = new CategoryInfoExample();
        example.setOrderByClause("id desc");
        return categoryInfoMapper.selectByExample(example);
    }

    /**
     * 通过文章ID获取某一篇文章对应的分类
     *
     * @param id 文章ID
     * @return
     */
    @Override
    public ArticleCategoryDto getCategoryByArticleId(Long id) {
        // 填充tbl_article_category中的基础数据
        ArticleCategoryExample example = new ArticleCategoryExample();
        example.createCriteria().andArticleIdEqualTo(id);
        ArticleCategory articleCategory = articleCategoryMapper.selectByExample(example).get(0);

        // 填充对应的分类信息
        CategoryInfo categoryInfo = getOneById(articleCategory.getCategoryId());

        return articleCategoryDTOMapper.createArticleCategoryDto(articleCategory,categoryInfo);
    }

    /**
     * 根据分类名称查找Category类
     * @param name
     * @return
     */
    @Override
    public CategoryInfo getCategoryByName(String name) {
        CategoryInfoExample example = new CategoryInfoExample();
        example.createCriteria().andNameEqualTo(name);
        List<CategoryInfo> categoryInfoList = categoryInfoMapper.selectByExample(example);

        if (categoryInfoList != null && !categoryInfoList.isEmpty()){
            return categoryInfoList.get(0);
        }

        return null;
    }
}
