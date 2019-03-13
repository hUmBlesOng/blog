// 跳转到指定文章
function showArticle(_this) {
    var articleId = $(_this).children("h6").text();
    var url = "/article/" + articleId;
    window.location.href = url;
}