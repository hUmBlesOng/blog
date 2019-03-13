package com.hs.blog.controller;

import com.hs.blog.dto.ArticleCommentDto;
import com.hs.blog.dto.ArticleDto;
import com.hs.blog.dto.ArticleWithPictureDto;
import com.hs.blog.entity.*;
import com.hs.blog.util.RestResponseBo;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * TODO: 后台Controller类
 *
 * @author 83998
 * @date 2019/3/5 23:01
 */
@Controller
@RequestMapping("admin")
public class BackController extends BaseController {

    @Autowired
    private User user;

    /**
     * 后台登录操作
     *
     * @return
     */
    @PostMapping("login")
    public String login(@RequestParam String username, @RequestParam String password,
                                HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 对用户账号进行验证,是否正确
        if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
            request.getSession().setAttribute("loginUser", user);
            return "redirect:/admin/index";
        }
        return "in";
    }

    /**
     * 进入并初始化index页面
     * @param request
     * @return
     */
    @GetMapping(value = {"", "index"})
    public String index(HttpServletRequest request){
        logger.info(">>>>>>>>>>初始化了index页面");

        List<SysLog> logList = iSysService.listAllLog();
        List<SysView> viewList = iSysService.listAllView();
        List<Comment> commentList = iCommentService.listAllComment();

        request.setAttribute("logList", logList);
        request.setAttribute("viewList", viewList);
        request.setAttribute("commentList", commentList);
        return "admin/index";
    }

    /**
     * 进入并初始化category界面
     * @param request
     * @return
     */
    @ApiOperation("进入并初始化category界面")
    @ApiImplicitParam(name = "name", value = "分类名称", required = true, dataType = "String")
    @GetMapping(value = "category")
    public String category(HttpServletRequest request){
        logger.info(">>>>>>>>>>初始化了category页面");

        List<CategoryInfo> categoryList = iCategoryService.listAllCategory();

        request.setAttribute("categoryList", categoryList);
        return "admin/category";
    }

    /**
     * 增加一条分类信息数据
     *
     * @return
     */
    @ApiOperation("增加分类信息")
    @ApiImplicitParam(name = "name", value = "分类名称", required = true, dataType = "String")
    @PostMapping("category")
    @ResponseBody
    public RestResponseBo addCategoryInfo(@RequestBody CategoryInfo categoryInfo){
        if ("".equals(categoryInfo.getName().trim())){
            return RestResponseBo.fail("分类名不能为空");
        }
        if (iCategoryService.getCategoryByName(categoryInfo.getName()) != null){
            return RestResponseBo.fail("分类名称重复");
        }

        iCategoryService.addCategory(categoryInfo);
        return RestResponseBo.ok();
    }

    /**
     * 更新/编辑一条分类信息
     *
     * @param id
     * @return
     */
    @ApiOperation("更新/编辑分类信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "文章ID", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "name", value = "分类名称", required = true, dataType = "String")
    })
    @PutMapping("category/{id}")
    @ResponseBody
    public RestResponseBo updateCategoryInfo(@PathVariable Long id, @RequestBody CategoryInfo categoryInfo) {
        if ("".equals(categoryInfo.getName().trim())){
            return RestResponseBo.fail("分类名不能为空");
        }
        if (iCategoryService.getCategoryByName(categoryInfo.getName()) != null){
            return RestResponseBo.fail("分类名称重复");
        }

        iCategoryService.updateCategory(categoryInfo);
        return RestResponseBo.ok();
    }

    /**
     * 根据ID删除分类信息
     *
     * @param id
     * @return
     */
    @ApiOperation("删除分类信息")
    @ApiImplicitParam(name = "id", value = "分类ID", required = true, dataType = "Long")
    @DeleteMapping("category/{id}")
    @ResponseBody
    public RestResponseBo deleteCategoryInfo(@PathVariable Long id) {
        CategoryInfo categoryInfo = iCategoryService.getOneById(id);
        if (categoryInfo == null){
            return RestResponseBo.fail("分类不存在");
        }

        if (categoryInfo.getNumber() > 0){
            return RestResponseBo.fail("该分类下有文章，不能删除");
        }

        iCategoryService.deleteCategoryById(id);
        return RestResponseBo.ok();
    }

    /**
     * 通过id获取一条分类信息
     *
     * @param id
     * @return
     */
    @ApiOperation("获取某一条分类信息")
    @ApiImplicitParam(name = "id", value = "分类ID", required = true, dataType = "Long")
    @GetMapping("category/{id}")
    public CategoryInfo getCategoryInfo(@PathVariable Long id) {
        return iCategoryService.getOneById(id);
    }

    /**
     * 进入并初始化article页面
     * @param request
     * @return
     */
    @ApiOperation("进入并初始化article页面")
    @ApiImplicitParam(name = "id", value = "分类ID", required = true, dataType = "Long")
    @GetMapping(value = "article")
    public String article(HttpServletRequest request){
        logger.info(">>>>>>>>>>初始化了article页面");

        List<ArticleWithPictureDto> articleList = iArticleService.listAll();
        List<CategoryInfo> categoryList = iCategoryService.listAllCategory();

        request.setAttribute("categoryList", categoryList);
        request.setAttribute("articleList", articleList);
        return "admin/article";
    }

    /**
     * 增加一篇文章
     *
     * @return
     */
    @ApiOperation("增加一篇文章")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "title", value = "文章标题", required = true, dataType = "String"),
            @ApiImplicitParam(name = "summary", value = "文章简介", required = true, dataType = "String"),
            @ApiImplicitParam(name = "isTop", value = "文章是否置顶", required = true, dataType = "Boolean"),
            @ApiImplicitParam(name = "categoryId", value = "文章分类对应ID", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "content", value = "文章md源码", required = true, dataType = "String"),
            @ApiImplicitParam(name = "pictureUrl", value = "文章题图url", required = true, dataType = "String")
    })
    @PostMapping("article")
    @ResponseBody
    public RestResponseBo addArticle(@RequestBody ArticleDto articleDto) {

        if ("".equals(articleDto.getTitle().trim())){
            return RestResponseBo.fail("请输入标题");
        }
        if (0==articleDto.getCategoryId()){
            return RestResponseBo.fail("请选择分类");
        }
        if ("".equals(articleDto.getContent())){
            return RestResponseBo.fail("请输入内容");
        }

        iArticleService.addArticle(articleDto);
        return RestResponseBo.ok();
    }

    /**
     * 通过ID获取一篇文章，内容为MD源码格式
     *
     * @param id
     * @return
     */
    @ApiOperation("获取一篇文章，内容为md源码格式")
    @ApiImplicitParam(name = "id", value = "文章ID", required = true, dataType = "Long")
    @GetMapping("article/{id}")
    @ResponseBody
    public RestResponseBo getArticleDtoById(@PathVariable Long id) {
        ArticleDto articleDto = iArticleService.getOneById(id);
        return RestResponseBo.ok(articleDto);
    }

    /**
     * 删除一篇文章
     *
     * @param id
     * @return
     */
    @ApiOperation("删除一篇文章")
    @ApiImplicitParam(name = "id", value = "文章ID", required = true, dataType = "Long")
    @DeleteMapping("article/{id}")
    @ResponseBody
    public RestResponseBo deleteArticle(@PathVariable Long id) {
        iArticleService.deleteArticleById(id);
        return RestResponseBo.ok();
    }

    /**
     * 编辑/更新一篇文章
     *
     * @return
     */
    @ApiOperation("编辑/更新一篇文章")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "title", value = "文章标题", required = true, dataType = "String"),
            @ApiImplicitParam(name = "summary", value = "文章简介", required = true, dataType = "String"),
            @ApiImplicitParam(name = "isTop", value = "文章是否置顶", required = true, dataType = "Boolean"),
            @ApiImplicitParam(name = "categoryId", value = "文章分类对应ID", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "content", value = "文章md源码", required = true, dataType = "String"),
            @ApiImplicitParam(name = "pictureUrl", value = "文章题图url", required = true, dataType = "String")
    })
    @PutMapping("article/{id}")
    @ResponseBody
    public RestResponseBo updateArticle(@PathVariable Long id, @RequestBody ArticleDto articleDto) {

        if ("".equals(articleDto.getTitle().trim())){
            return RestResponseBo.fail("请输入标题");
        }
        if ("".equals(articleDto.getContent())){
            return RestResponseBo.fail("请输入内容");
        }
        articleDto.setId(id);
        iArticleService.updateArticle(articleDto);
        return RestResponseBo.ok();
    }

    /**
     * 获取所有文章
     * @param request
     * @return
     */
    @GetMapping(value = "article/list")
    @ResponseBody
    public RestResponseBo listArticle(HttpServletRequest request){

        List<ArticleWithPictureDto> articleList = iArticleService.listAll();
        return RestResponseBo.ok(articleList);
    }

    /**
     * 根绝分类获取文章
     * @param request
     * @return
     */
    @GetMapping(value = "article/list/{id}")
    @ResponseBody
    public RestResponseBo listArticleByCategory(@PathVariable Long id, HttpServletRequest request){

        List<ArticleWithPictureDto> articleList = iArticleService.listByCategoryId(id);
        return RestResponseBo.ok(articleList);
    }

    /**
     * 进入并初始化comment页面
     * @param request
     * @return
     */
    @ApiOperation("进入并初始化comment页面")
    @ApiImplicitParam(name = "id", value = "评论ID", required = true, dataType = "Long")
    @GetMapping(value = "comment")
    public String comment(HttpServletRequest request){
        logger.info(">>>>>>>>>>初始化了comment页面");

        List<ArticleWithPictureDto> articleList = iArticleService.listAll();
        request.setAttribute("articleList", articleList);
        return "admin/comment";
    }

    /**
     * 根据文章获取评论
     * @param id
     * @return
     */
    @GetMapping(value = "comment/article/{id}")
    @ResponseBody
    public RestResponseBo listCommentByArticle(@PathVariable Long id){

        List<ArticleCommentDto> articleCommentList = iCommentService.listAllArticleCommentById(id);
        return RestResponseBo.ok(articleCommentList);
    }

    /**
     * 通过评论ID删除文章评论信息
     *
     * @param id
     * @return
     */
    @ApiOperation("删除文章评论信息")
    @ApiImplicitParam(name = "id", value = "评论ID", required = true, dataType = "Long")
    @DeleteMapping("comment/article/{id}")
    @ResponseBody
    public RestResponseBo deleteArticleComment(@PathVariable Long id) {
        iCommentService.deleteArticleCommentById(id);
        return RestResponseBo.ok();
    }

    /**
     * 通过id删除某一条留言
     *
     * @param id
     * @return
     */
    @ApiOperation("删除一条留言")
    @ApiImplicitParam(name = "id", value = "评论/留言ID", required = true, dataType = "Long")
    @DeleteMapping("comment/{id}")
    @ResponseBody
    public RestResponseBo deleteComment(@PathVariable Long id) {
        iCommentService.deleteCommentById(id);
        return RestResponseBo.ok();
    }

    /**
     * 回复留言/评论，通过id去找到对应的Email
     *
     * @param id
     * @return
     */
    @ApiOperation("回复留言/评论")
    @ApiImplicitParam(name = "id", value = "评论/留言ID", required = true, dataType = "Long")
    @GetMapping("comment/reply/{id}")
    @ResponseBody
    public String replyComment(@PathVariable Long id) {
        return null;
    }

    /**
     * 通过id获取某一条评论/留言
     *
     * @param id
     * @return
     */
    @ApiOperation("获取某一条评论/留言")
    @ApiImplicitParam(name = "id", value = "评论/留言ID", required = true, dataType = "Long")
    @GetMapping("comment/{id}")
    public Comment getCommentById(@PathVariable Long id) {
        return null;
    }

}
