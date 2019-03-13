$(function () {

    $("#data").addClass("active-menu");

    $('#dataTables-visits').dataTable();
    $('#dataTables-logs').dataTable();
    $('#dataTables-comments').dataTable();

});

// 删除评论
function deleteComment(id) {
    $('#confirmBtn').attr("commentId", id);
    $('#myModal').modal();
}

// 确认删除留言点击事件
$('#confirmBtn').click(function () {
    var id = $(this).attr("commentId");
    $.ajax({
        type: "DELETE",
        url: "admin/comment/" + id,
        success: function (result) {
            if(result!=null && result.success){
                location.reload();
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