package com.hs.blog.mapper;

import com.hs.blog.dto.ArticleCommentDto;
import com.hs.blog.entity.*;
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
public interface ArticleCommentDTOMapper {

    @Mappings({
            @Mapping(source = "comment.id", target = "id"),
            @Mapping(source = "comment.content", target = "content"),
            @Mapping(source = "comment.name", target = "name"),
            @Mapping(source = "comment.email", target = "email"),
            @Mapping(source = "comment.ip", target = "ip"),
            @Mapping(source = "comment.createBy", target = "createBy"),
            @Mapping(source = "articleComment.id", target = "articleCommentId"),
            @Mapping(source = "articleComment.articleId", target = "articleId")
    })
    ArticleCommentDto createArticleCommentDto(Comment comment,
                                       ArticleComment articleComment);
}
