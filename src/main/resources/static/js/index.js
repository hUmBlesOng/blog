$(function () {

	$("#showArticle").click(function () {
		var articleId = $(this).attr("articleId");
		var url = "article/" + articleId;
		window.location.href = url;
	})
});

// 按钮点击进行文章详情页
$("#showArticle").click(function() {
	var articleId = $(this).attr("articleId");
	window.location.href = "article/" + articleId;
});

// 缩略图鼠标进入事件：更换大图和按钮的articleId
$(".smallPictures img").mouseenter(function() {
	var pictureUrl = $(this).attr("pictureUrl");
	var articleId = $(this).attr("articleId");
	var title = $(this).attr("title");
	var summary = $(this).attr("summary");
	if (summary == null ||summary === ''){
		summary = ' ';
	}
	$("#articlePicture img").attr("src", pictureUrl);
	$("#showArticle").attr("articleId", articleId);
	$("#articleTitle").html(title);
	$("#articleSummary").html(summary);
});