window.onload = function () {
    // 增加留言
    $('#addComment').click(function () {
        var name = $('#commentName').val();
        var email = $('#commentEmail').val();
        var content = $('#commentContent').val();

        // 判断是否为空
        if ("" === name || "" === content) {
            $('#modal').modal();
            return;
        }

        // 不为空才能增加
        var comment = {
            name: name,
            email: email,
            content: content
        };
        // 提交AJAX请求
        $.ajax({
            url: "/comment",
            type: "POST",
            dataType: "json",
            contentType: "application/json;charset=utf-8",
            data: JSON.stringify(comment),
            success: function (result) {
                if(result != null && result.success){
                    $('#addModal').modal();
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

                $('#addModal').modal();

                this; // 调用本次AJAX请求时传递的options参数
            }
        })
    });

    // 模态框确认按钮点击事件
    $('#addConfirmBtn').click(function () {
        // 刷新页面
        location.reload();
    });
};