package com.hs.blog.mapper;


import com.hs.blog.dto.ArticleDto;
import com.hs.blog.entity.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * TODO: DO与DTO转换
 *
 * @author 83998
 * @date 2019/3/5 21:21
 */
@Mapper(componentModel = "spring")
public interface ArticleDTOMapper {

    @Mappings({
            @Mapping(source = "articleInfo.id", target = "id"),
            @Mapping(source = "articleInfo.title", target = "title"),
            @Mapping(source = "articleInfo.summary", target = "summary"),
            @Mapping(source = "articleInfo.isTop", target = "isTop"),
            @Mapping(source = "articleInfo.traffic", target = "traffic"),
            @Mapping(source = "articleInfo.createBy", target = "createBy"),
            @Mapping(source = "articleContent.id", target = "articleContentId"),
            @Mapping(source = "articleContent.content", target = "content"),
            @Mapping(source = "categoryInfo.id", target = "categoryId"),
            @Mapping(source = "categoryInfo.name", target = "categoryName"),
            @Mapping(source = "categoryInfo.number", target = "categoryNumber"),
            @Mapping(source = "articleCategory.id", target = "articleCategoryId"),
            @Mapping(source = "articlePicture.id", target = "articlePictureId"),
            @Mapping(source = "articlePicture.pictureUrl", target = "pictureUrl")
    })
    ArticleDto createArticleDto(ArticleInfo articleInfo,
                                ArticleContent articleContent,
                                ArticleCategory articleCategory,
                                CategoryInfo categoryInfo,
                                ArticlePicture articlePicture);
}
