package com.hs.blog.controller;

import com.hs.blog.dto.ArticleCommentDto;
import com.hs.blog.dto.ArticleDto;
import com.hs.blog.dto.ArticleWithPictureDto;
import com.hs.blog.entity.CategoryInfo;
import com.hs.blog.entity.Comment;
import com.hs.blog.util.Markdown2HtmlUtil;
import com.hs.blog.util.RestResponseBo;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Iterator;
import java.util.List;

/**
 * TODO: 前台Controller
 *
 * @author 83998
 * @date 2019/3/5 23:20
 */
@Controller
public class ForeController extends BaseController {

    /**
     * 进入并初始化首页
     * @param request
     * @return
     */
    @GetMapping(value = {"", "/index"})
    public String index(HttpServletRequest request){
        logger.info(">>>>>>>>>>初始化了首页");

        List<ArticleWithPictureDto> articleList = iArticleService.listLastest();

        request.setAttribute("articleList", articleList);
        return "index";
    }

    /**
     * 通过文章的ID获取对应的文章信息
     *
     * @param id
     * @return 自己封装好的文章信息类
     */
    @ApiOperation("通过文章ID获取文章信息")
    @GetMapping("/article/{id}")
    public String getArticleById(@PathVariable Long id,
                                 HttpServletRequest request) {
        logger.info(">>>>>>>>>>初始化了文章页");

        ArticleDto articleDto = iArticleService.getOneById(id);
        articleDto.setContent(Markdown2HtmlUtil.markdown2html(articleDto.getContent()));

        List<ArticleCommentDto> articleCommentDtoList = iCommentService.listAllArticleCommentById(id);

        request.setAttribute("articleDto",articleDto);
        request.setAttribute("articleCommentDtoList",articleCommentDtoList);
        return "article";
    }

    /**
     * 给某一篇文章增加一条评论信息
     *
     * @return
     */
    @ApiOperation("给文章中增加一条评论信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "文章ID", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "content", value = "评论信息", required = true, dataType = "String"),
            @ApiImplicitParam(name = "email", value = "Email地址，用于回复", required = false, dataType = "String"),
            @ApiImplicitParam(name = "name", value = "用户自定义的名称", required = true, dataType = "String")
    })
    @PostMapping("/comment/article/{id}")
    @ResponseBody
    public RestResponseBo addArticleComment(@PathVariable Long id, @RequestBody ArticleCommentDto articleCommentDto,
                                            HttpServletRequest request) {

        String ip = request.getRemoteAddr();
        articleCommentDto.setIp(ip);
        articleCommentDto.setArticleId(id);
        iCommentService.addArticleComment(articleCommentDto);

        return RestResponseBo.ok();
    }

    /**
     * 进入并初始化博客页
     * @param request
     * @return
     */
    @GetMapping(value = {"/blog"})
    public String blog(HttpServletRequest request){
        logger.info(">>>>>>>>>>初始化了博客页");

        List<CategoryInfo> categoryList = iCategoryService.listAllCategory();
        List<ArticleWithPictureDto> articleList = iArticleService.listAll();

        request.setAttribute("categoryList", categoryList);
        request.setAttribute("articleList", articleList);
        return "blog";
    }

    /**
     * 根据分类显示文章
     *
     * @param id
     * @param request
     * @return
     */
    @GetMapping(value = {"/blog/{id}"})
    public String listArticleByCategory(@PathVariable Long id, HttpServletRequest request){
        logger.info("执行了listArticleByCategory方法");

        List<CategoryInfo> categoryList = iCategoryService.listAllCategory();
        List<ArticleWithPictureDto> articleList = iArticleService.listByCategoryId(id);

        request.setAttribute("categoryList", categoryList);
        request.setAttribute("articleList", articleList);
        return "blog";
    }

    /**
     * 进入并初始化关于页面
     * @param request
     * @return
     */
    @GetMapping(value = {"/about"})
    public String about(HttpServletRequest request){
        logger.info(">>>>>>>>>>初始化了关于页");

        List<Comment> commentList = iCommentService.listAllComment();

        // 移除无效评论
        Iterator it = commentList.iterator();
        while (it.hasNext()){
            Comment c = (Comment) it.next();
            if (!c.getIsEffective()){
                it.remove();
            }
        }

        request.setAttribute("commentList", commentList);
        return "about";
    }

    /**
     * 增加一条留言
     *
     * @return
     */
    @ApiOperation("增加一条留言")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "content", value = "评论信息", required = true, dataType = "String"),
            @ApiImplicitParam(name = "email", value = "Email地址，用于回复", required = false, dataType = "String"),
            @ApiImplicitParam(name = "name", value = "用户自定义的名称", required = true, dataType = "String")
    })
    @PostMapping("/comment")
    @ResponseBody
    public RestResponseBo addMessage(@RequestBody Comment comment, HttpServletRequest request) {

        String ip = request.getRemoteAddr();
        comment.setIp(ip);
        iCommentService.addComment(comment);

        return RestResponseBo.ok();
    }

    /**
     * 进入并初始化简历页面
     * @param request
     * @return
     */
    @GetMapping(value = {"/resume"})
    public String resume(HttpServletRequest request){
        logger.info(">>>>>>>>>>初始化了简历页");

        return "resume";
    }

}
