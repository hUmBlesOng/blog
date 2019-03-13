package com.hs.blog.mapper;

import com.hs.blog.dto.ArticleWithPictureDto;
import com.hs.blog.entity.ArticleInfo;
import com.hs.blog.entity.ArticlePicture;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * TODO: DO与DTO转换
 *
 * @author 83998
 * @date 2019/3/5 21:39
 */
@Mapper(componentModel = "spring")
public interface ArticleWithPictureDTOMapper {

    @Mappings({
            @Mapping(source = "articleInfo.id", target = "id"),
            @Mapping(source = "articleInfo.title", target = "title"),
            @Mapping(source = "articleInfo.summary", target = "summary"),
            @Mapping(source = "articleInfo.isTop", target = "isTop"),
            @Mapping(source = "articleInfo.traffic", target = "traffic"),
            @Mapping(source = "articlePicture.id", target = "articlePictureId"),
            @Mapping(source = "articlePicture.pictureUrl", target = "pictureUrl")
    })
    ArticleWithPictureDto createArticleWithPictureDto(ArticleInfo articleInfo,
                                                      ArticlePicture articlePicture);
}
