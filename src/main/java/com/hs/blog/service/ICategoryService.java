package com.hs.blog.service;

import com.hs.blog.dto.ArticleCategoryDto;
import com.hs.blog.entity.ArticleCategory;
import com.hs.blog.entity.CategoryInfo;

import java.util.List;

/**
 * TODO:
 *
 * @author 83998
 * @date 2019/3/5 18:33
 */
public interface ICategoryService {

    void addCategory(CategoryInfo categoryInfo);

    void deleteCategoryById(Long id);

    void updateCategory(CategoryInfo categoryInfo);

    void updateArticleCategory(ArticleCategory articleCategory);

    CategoryInfo getOneById(Long id);

    List<CategoryInfo> listAllCategory();

    ArticleCategoryDto getCategoryByArticleId(Long id);

    CategoryInfo getCategoryByName(String name);
}
