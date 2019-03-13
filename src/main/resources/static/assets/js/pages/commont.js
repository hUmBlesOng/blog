$(document).ready(function() {
	$("#comment").addClass("active-menu");
});

// 根据ID填充评论列表的信息
function addCommentList(id) {
	$.ajax({
		type: "get",
		url: "admin/comment/article/" + id,
		dataType: "json",
		contentType: "application/json;charset=utf-8",
		success: function(json) {
			if(json != null && json.success) {
				// 先要清空原来的数据
				$('#tbody-comments').html("");
				$.each(json.payload, function (i, item) {
					var createBy = new Date(item.createBy);
					$('#tbody-comments').append(
						'<tr><td>' + +item.id +
						'</td><td>' + item.content +
						'</td><td>' + item.name +
						'</td><td>' + item.email +
						'</td><td>' + item.ip +
						'</td><td>' + createBy.toLocaleString() +
						'</td><td><button class="btn btn-danger deleteBtn" onclick="deleteArticleComment(\'' + item.articleCommentId + '\')"><i class="fa fa-trash-o"></i>删除</button></td></tr>');
				});
				$('#dataTables-comments').dataTable();
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
}

// 监听文章Select改变，改变注入相应的评论
document.getElementById("articleList").onchange = function() {
	var articleId = $('#articleList option:selected').attr("value");
	//	alert(categoryId);
	addCommentList(articleId);
};

// 删除按钮点击事件
function deleteArticleComment(id) {
	$('#confirmBtn').attr("articleCommentId", id);
	$('#myModal').modal();
};

// 确认删除按钮点击事件
$('#confirmBtn').click(function() {
	var id = $(this).attr("articleCommentId");
	//	alert(id);
	$.ajax({
		type: "DELETE",
		url: "admin/comment/article/" + id,
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