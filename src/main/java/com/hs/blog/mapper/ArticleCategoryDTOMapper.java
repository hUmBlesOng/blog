package com.hs.blog.mapper;

import com.hs.blog.dto.ArticleCategoryDto;
import com.hs.blog.entity.ArticleCategory;
import com.hs.blog.entity.CategoryInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * TODO:
 *
 * @author 83998
 * @date 2019/3/5 21:39
 */
@Mapper(componentModel = "spring")
public interface ArticleCategoryDTOMapper {

    @Mappings({
            @Mapping(source = "articleCategory.id", target = "id"),
            @Mapping(source = "articleCategory.categoryId", target = "categoryId"),
            @Mapping(source = "articleCategory.articleId", target = "articleId"),
            @Mapping(source = "categoryInfo.name", target = "name"),
            @Mapping(source = "categoryInfo.number", target = "number")
    })
    ArticleCategoryDto createArticleCategoryDto(ArticleCategory articleCategory,
                                                CategoryInfo categoryInfo);
}
