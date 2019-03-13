package com.hs.blog.service;

import com.hs.blog.dto.ArticleDto;
import com.hs.blog.dto.ArticleWithPictureDto;
import com.hs.blog.entity.ArticleInfo;
import com.hs.blog.entity.ArticlePicture;

import java.util.List;

/**
 * TODO:
 *
 * @author 83998
 * @date 2019/3/5 18:33
 */
public interface IArticleService {

    void addArticle(ArticleDto articleDto);

    void deleteArticleById(Long id);

    void updateArticle(ArticleDto articleDto);

    void updateArticleCategory(Long articleId, Long categoryId);

    ArticleDto getOneById(Long id);

    ArticlePicture getPictureByArticleId(Long id);

    List<ArticleWithPictureDto> listAll();

    List<ArticleWithPictureDto> listByCategoryId(Long id);

    List<ArticleWithPictureDto> listLastest();

    void ceshi(ArticleInfo articleInfo);

}
