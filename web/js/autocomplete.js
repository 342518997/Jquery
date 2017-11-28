
$(function () {
    $("#in1").autocomplete({
        // 静态的数据源
        source: function (request,response) {
            $.ajax({
                url:"/test",
                type:"POST",
                data:{"name" : $("#in1").val()},
                dataType:"json",
                success : function(json) {
                    response($.map(json,function(item){
                        var name = item.name;
                        var id = item.id;
                        return {
                            label:item.name,//下拉框显示值
                            value:item.name,//选中后，填充到下拉框的值
                            id:item.id//选中后，填充到id里面的值
                        }
                    }));
                }
            });
        },
        autoFocus : true,
        minLength : 2,
        delay : 500

    });
});