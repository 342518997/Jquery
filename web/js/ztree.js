var zTreeObj;
// zTree 的参数配置，深入使用请参考 API 文档（setting 配置详解）
var setting = {
    edit: { //此属性添加后，树才可以被拖拽
        enable: true,//设置 zTree 是否处于编辑状态
        showRemoveBtn: false,//设置是否显示删除按钮
        showRenameBtn: true,//设置是否显示编辑名称按钮
        drag: {
            isCopy: true,//拖拽时, 设置是否允许复制节点
            isMove: false,//拖拽时, 设置是否允许移动节点
            prev: true,//拖拽到目标节点时，设置是否允许移动到目标节点前面的操作
            next: true,//拖拽到目标节点时，设置是否允许移动到目标节点后面的操作
            inner: true//拖拽到目标节点时，设置是否允许成为目标节点的子节点
        }
    },
    data: {
        simpleData: {
            enable: true,
            idKey: "id",
            pIdKey: "parent_id"
        }
    },
    check:{
        enable: true,
        autoCheckTrigger:true,
        chkStyle:"checkbox"
    },
    callback:{
        beforeDrag: function(treeId, treeNodes){
            //用于捕获节点被拖拽之前的事件回调函数，并且根据返回值确定是否允许开启拖拽操作
            return true;
        },
        beforeDrop: function(treeId, treeNodes, targetNode, moveType, isCopy){
            //用于捕获节点拖拽操作结束之前的事件回调函数，并且根据返回值确定是否允许此拖拽操作
            //库名不允许拖拽
            if(treeNodes[0].level==0){
                alert("不允许拖拽库节点");
                return false;
            }

            /*var last=JSON.stringify(treeNodes);*///将JSON对象转化为JSON字符

            //如果拖拽的是目录 判断是否有子节点
            if(treeNodes[0].isParent){
                var s1=new Array();
                //遍历子节点数据集合
                s1.push(treeNodes[0]);
                $.each(treeNodes[0].children,function(i,treeNode){
                    /*alert("【源节点】子节点"+i+":"+treeNode.id+"  父节点id:"+treeNode.parent_id+"  级层："+treeNode.level+"  名称："+treeNode.name);*/
                    s1.push(treeNode);
                });
                ztreeisCopy("/replication",s1,targetNode);
            }else {

                ztreeisCopy("/replication",treeNodes,targetNode);
                /*alert("【源节点】节点id:"+treeNodes[0].id+"  父节点id:"+treeNodes[0].parent_id+"  级层："+treeNodes[0].level+"  名称："+treeNodes[0].name);*/
            }

            /* alert("【目标节点】 节点id:"+targetNode.id+"  父节点id:"+targetNode.parent_id+"  级层："+targetNode.level+"  名称："+targetNode.name);*/

            //不允许拖拽到表节点下(如果树状图中有空目录，那还是需要在后台进行校验该节点是否是表节点）
            /*                    if(!targetNode.isParent){
                                    alert("不允许拖拽任何节点到表节点下");
                                    return false;
                                }*/

            return true;
        },
        onCheck:test,
        onClick:on

    }

};

$(document).ready(function(){
    $.ajax({
        type:"post",
        url:"/findZtreeDemo",
        dataType:"json",
        success:function(data){
            // zTree 的数据属性，深入使用请参考 API 文档（zTreeNode 节点数据详解）
            var zNodes = data;
            zTreeObj = $.fn.zTree.init($("#treeDemo"), setting, zNodes);
        }
    });


});
//js方法
function test(event, treeId, treeNode){
    /*alert(treeNode.tId + ", " + treeNode.name + "," + treeNode.checked);*/
}
function on(event, treeId, treeNode) {
    alert(treeNode.name);
}
function ztreeisCopy(url,treeNodes,targetNode){
    $.ajax({
        url:url,
        dataType:"json",
        data:{"sourcejson":JSON.stringify(treeNodes) ,"targetjson":JSON.stringify(targetNode) },
        type:"POST",
        success:function(data){
            if(JSON.stringify(data)){
                                            alert("复制菜单成功.");
                //以下代码实现重置树
                var treeObj = $.fn.zTree.getZTreeObj("treeDemo");//根据 treeId 获取 zTree 对象
                treeObj.expandAll(true);//折叠全部节点,参数为true时表示展开全部节点
                treeObj.refresh();//刷新zTree，实现不选中任何节点

            }else {
                alert("复制失败.");
            }

        }

    });
}