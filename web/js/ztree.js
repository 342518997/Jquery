var zTreeObj;
// zTree �Ĳ������ã�����ʹ����ο� API �ĵ���setting ������⣩
var setting = {
    edit: { //��������Ӻ����ſ��Ա���ק
        enable: true,//���� zTree �Ƿ��ڱ༭״̬
        showRemoveBtn: false,//�����Ƿ���ʾɾ����ť
        showRenameBtn: true,//�����Ƿ���ʾ�༭���ư�ť
        drag: {
            isCopy: true,//��קʱ, �����Ƿ������ƽڵ�
            isMove: false,//��קʱ, �����Ƿ������ƶ��ڵ�
            prev: true,//��ק��Ŀ��ڵ�ʱ�������Ƿ������ƶ���Ŀ��ڵ�ǰ��Ĳ���
            next: true,//��ק��Ŀ��ڵ�ʱ�������Ƿ������ƶ���Ŀ��ڵ����Ĳ���
            inner: true//��ק��Ŀ��ڵ�ʱ�������Ƿ������ΪĿ��ڵ���ӽڵ�
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
            //���ڲ���ڵ㱻��ק֮ǰ���¼��ص����������Ҹ��ݷ���ֵȷ���Ƿ���������ק����
            return true;
        },
        beforeDrop: function(treeId, treeNodes, targetNode, moveType, isCopy){
            //���ڲ���ڵ���ק��������֮ǰ���¼��ص����������Ҹ��ݷ���ֵȷ���Ƿ��������ק����
            //������������ק
            if(treeNodes[0].level==0){
                alert("��������ק��ڵ�");
                return false;
            }

            /*var last=JSON.stringify(treeNodes);*///��JSON����ת��ΪJSON�ַ�

            //�����ק����Ŀ¼ �ж��Ƿ����ӽڵ�
            if(treeNodes[0].isParent){
                var s1=new Array();
                //�����ӽڵ����ݼ���
                s1.push(treeNodes[0]);
                $.each(treeNodes[0].children,function(i,treeNode){
                    /*alert("��Դ�ڵ㡿�ӽڵ�"+i+":"+treeNode.id+"  ���ڵ�id:"+treeNode.parent_id+"  ���㣺"+treeNode.level+"  ���ƣ�"+treeNode.name);*/
                    s1.push(treeNode);
                });
                ztreeisCopy("/replication",s1,targetNode);
            }else {

                ztreeisCopy("/replication",treeNodes,targetNode);
                /*alert("��Դ�ڵ㡿�ڵ�id:"+treeNodes[0].id+"  ���ڵ�id:"+treeNodes[0].parent_id+"  ���㣺"+treeNodes[0].level+"  ���ƣ�"+treeNodes[0].name);*/
            }

            /* alert("��Ŀ��ڵ㡿 �ڵ�id:"+targetNode.id+"  ���ڵ�id:"+targetNode.parent_id+"  ���㣺"+targetNode.level+"  ���ƣ�"+targetNode.name);*/

            //��������ק����ڵ���(�����״ͼ���п�Ŀ¼���ǻ�����Ҫ�ں�̨����У��ýڵ��Ƿ��Ǳ�ڵ㣩
            /*                    if(!targetNode.isParent){
                                    alert("��������ק�κνڵ㵽��ڵ���");
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
            // zTree ���������ԣ�����ʹ����ο� API �ĵ���zTreeNode �ڵ�������⣩
            var zNodes = data;
            zTreeObj = $.fn.zTree.init($("#treeDemo"), setting, zNodes);
        }
    });


});
//js����
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
                                            alert("���Ʋ˵��ɹ�.");
                //���´���ʵ��������
                var treeObj = $.fn.zTree.getZTreeObj("treeDemo");//���� treeId ��ȡ zTree ����
                treeObj.expandAll(true);//�۵�ȫ���ڵ�,����Ϊtrueʱ��ʾչ��ȫ���ڵ�
                treeObj.refresh();//ˢ��zTree��ʵ�ֲ�ѡ���κνڵ�

            }else {
                alert("����ʧ��.");
            }

        }

    });
}