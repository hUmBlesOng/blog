$(function() {

	// 初始化dateTables
	$('#dataTables-categoris').dataTable();
	$("#category").addClass("active-menu");

	// 新增分类
	$('#addCategoryBtn').click(function() {
		var categoryName = $('#addName').val();
		if("" === categoryName){
			alert("分类名不能为空");
			return false;
		}
		var json = {
			name: categoryName
		};
		$.ajax({
			type: "POST",
			dataType: "json",
			contentType: "application/json;charset=utf-8",
			url: "/admin/category",
			data: JSON.stringify(json),
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


	// 更新分类点击事件
	$('#updateCategoryBtn').click(function() {
		var categoryId = $('#select-category option:selected').attr("value");
		if('' === categoryId){
			alert("请选择分类");
			return false;
		}
		var categoryName = $('#updateName').val();
		if("" === categoryName){
			alert("分类名不能为空");
			return false;
		}
		var categoryJson = {
			id: categoryId,
			name: categoryName
		};
		$.ajax({
			type: "PUT",
			url: "/admin/category/" + categoryId,
			data: JSON.stringify(categoryJson),
			dataType: "json",
			contentType: "application/json;charset=utf-8",
			success: function(result) {
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
		})
	});

	// 确认删除按钮点击事件
	$('#confirmBtn').click(function() {
		var id = $(this).attr("categoryId");
		$.ajax({
			type: "DELETE",
			url: "/admin/category/" + id,
			success: function(result) {
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

// 删除按钮点击事件
function deleteCategory(id) {
	$('#confirmBtn').attr("categoryId", id);
	$('#myModal').modal();
}

