$(document).ready(function () {

    $('#dataTables-articles').dataTable();
    $("#article").addClass("active-menu");

    // 增加文章按钮点击事件
    $('#addArticleBtn').click(function () {
        var articleTitle = $('#addArticleTitle').val();
        if ("" === articleTitle) {
            alert("请输入标题");
            return false;
        }
        var articleSummary = $("#addArticleSummary").val();
        var articleTop = $("#addArticleTop").prop("checked");
        //	alert(articleTop);
        var articleCategoryId = $("#addCategories option:selected").attr("value");
        if ("" === articleCategoryId) {
            alert("请选择分类");
            return false;
        }
        var articlePicture = $('#addArticlePicture').val();
        var articleContent = $('#addArticleContent').val();
        if ("" === articleContent){
            alert("请输入内容");
            return false;
        }
        var article = {
            title: articleTitle,
            summary: articleSummary,
            isTop: articleTop,
            categoryId: articleCategoryId,
            pictureUrl: articlePicture,
            content: articleContent
        };
        $.ajax({
            type: "POST",
            url: "/admin/article/",
            dataType: "json",
            contentType: "application/json;charset=utf-8",
            data: JSON.stringify(article),
            success: function (result) {
                if(result != null && result.success){
                    location.reload();
                }else{
                    alert(result.msg);
                }
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                // 通常 textStatus 和 errorThrown 之中
                // 只有一个会包含信息
                console.error("XMLHttpRequest"+XMLHttpRequest);
                console.error("textStatus"+textStatus);
                console.error("errorThrown"+errorThrown);
                this; // 调用本次AJAX请求时传递的options参数
            }
        });
    });


    // 更新文章按钮点击事件
    $('#updateBtn').click(function () {
        var articleId = $('#updateBtn').attr("articleId");
        var articleTitle = $('#articleTitle').val();
        var articleSummary = $("#articleSummary").val();
        var articleTop = $("#articleTop").prop("checked");
        //	alert(articleTop);
        var articleCategoryId = $("#updateCategories option:selected").attr("categoryId");
        var articlePicture = $('#articlePicture').val();
        var articleContent = $('#articleContent').val();
        var article = {
            id: articleId,
            title: articleTitle,
            summary: articleSummary,
            isTop: articleTop,
            categoryId: articleCategoryId,
            pictureUrl: articlePicture,
            content: articleContent
        };
        $.ajax({
            type: "PUT",
            url: "/admin/article/" + articleId,
            dataType: "json",
            contentType: "application/json;charset=utf-8",
            data: JSON.stringify(article),
            success: function (result) {
                if(result != null && result.success){
                    location.reload();
                }else{
                    alert(result.msg);
                }
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                // 通常 textStatus 和 errorThrown 之中
                // 只有一个会包含信息
                console.error("XMLHttpRequest"+XMLHttpRequest);
                console.error("textStatus"+textStatus);
                console.error("errorThrown"+errorThrown);
                this; // 调用本次AJAX请求时传递的options参数
            }
        });
    });

    // 确认删除按钮点击
    $('#confirmBtn').click(function () {
        var id = $(this).attr("articleId");
        $.ajax({
            type: "DELETE",
            url: "/admin/article/" + id,
            success: function (result) {
                if(result != null && result.success){
                    location.reload();
                }else{
                    alert(result.msg);
                }
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                // 通常 textStatus 和 errorThrown 之中
                // 只有一个会包含信息
                console.error("XMLHttpRequest"+XMLHttpRequest);
                console.error("textStatus"+textStatus);
                console.error("errorThrown"+errorThrown);
                this; // 调用本次AJAX请求时传递的options参数
            }
        });
    });

});

// 监听博文分类Select改变，改变注入相应的博文
document.getElementById("articleCategories").onchange = function () {
    var categoryId = $('#articleCategories option:selected').attr("value");
    if (categoryId === "") {
        var url = "/admin/article/list";
    } else {
        var url = "/admin/article/list/" + categoryId;
    }
    // 填充博文分类信息
    $.ajax({
        url: url,
        type: "GET",
        dataType: "json",
        success: function (json) {
            // 先清空博文数据
            $('#tbody-articles').html("");
            $.each(json.payload, function (i, item) {
                var top = '否';
                if (item.isTop){
                    top = '是';
                }
                $('#tbody-articles').append(
                    '<tr><td>' + +item.id +
                    '</td><td>' + item.title +
                    '</td><td>' + top +
                    '</td><td>' + item.traffic +
                    '</td><td><a href="' + item.pictureUrl + '">点击这里</a></td>' +
                    '<td><button class="btn btn-success" onclick="updateArticle(' + item.id + ')"><i class="fa fa-edit"></i> 编辑</button> ' +
                    '<button class="btn btn-danger" onclick="deleteArticle(' + item.id + ')"><i class="fa fa-trash-o"> 删除</i></button></td></tr>');
            });
            $('#dataTables-articles').dataTable();

        }
    });
};

// 删除按钮点击
function deleteArticle(id) {
    $('#confirmBtn').attr("articleId", id);
    $('#myModal').modal();
};

// 编辑文章按钮点击
function updateArticle(id) {
    // 往模态框填充数据
    $('#updateBtn').attr("articleId", id);
    $.ajax({
        type: "get",
        url: "/admin/article/" + id,
        dataType: "json",
        success: function (result) {
            if(result != null && result.success){
                var articleDto = result.payload;
                $('#articleTitle').val(articleDto.title);
                $('#articleSummary').val(articleDto.summary);
                $("#articleTop").attr("checked", articleDto.isTop);
                // 填充分类数据
                $("#updateCategories").children("option[value='"+articleDto.categoryId+"']").attr("selected",true);
                $('#articlePicture').val(articleDto.pictureUrl);
                $('#articleContent').val(articleDto.content);
            }

        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            // 通常 textStatus 和 errorThrown 之中
            // 只有一个会包含信息
            console.error("XMLHttpRequest"+XMLHttpRequest);
            console.error("textStatus"+textStatus);
            console.error("errorThrown"+errorThrown);
            this; // 调用本次AJAX请求时传递的options参数
        }
    });

    // 显示模态框
    $('#updateModal').modal();
}