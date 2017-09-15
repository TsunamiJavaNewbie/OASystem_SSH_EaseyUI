<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>角色与菜单</title>
<%@ include file="/common/common.jsp"%>
<script>
$(function() {
	//查找并选中节点
	//$('#tree').tree('check', 5);
	//$('#tree').tree('check', node.target);
 
	$('#tree').tree({
		url : '${ctx}/system/sys-menu!tree.action',
		lines:true,
		onLoadSuccess:function(node,data){
			<c:forEach var="v" items="${model.menus }">
				var t_${v.id} = $(this).tree('find', ${v.id});
				$(this).tree('check',t_${v.id}.target);
		    </c:forEach>
		}
	});

	
	$("#inputForm").submit(function() {
			//选中树的节点
			var nodes = $('#tree').tree('getChecked');
			//
            for(var i=0;i<nodes.length;i++){
                 $(this).append('<input type="hidden" name="menuId" value="'+nodes[i].id+'">');	
            }
			var json = $(this).serializeArray();
		  	var url = $(this).attr("action");
		  	
			$.post(url, json, function(data) {
				if (data.status) {
					$.messager.alert('系统消息', data.msg, 'info', function() {
						//ok后的回调方法，去关闭父页面的窗口元素
						parent.$('#grid').datagrid('reload');
						parent.$('#${windowid}').window('close');
					});
				}
			}, "json");
			//返回flase ，否则会提交表单
			return false;
	});
	 
});
</script>
</head>
<body>

<div class="easyui-layout" data-options="fit:true">
<div data-options="region:'center',border:false" style="padding: 10px;">

<form action="${ctx }/system/sys-role!roleMenu.action" id="inputForm" onsubmit="return false;" method="post" class="easyui-form"   >
<input type="hidden" name="id" value="${model.id }">
<ul id="tree"  data-options="lines:true,checkbox:true"></ul>
</form>

</div>
<div data-options="region:'south',border:false" style="text-align: right; margin-bottom:0px; padding: 5px; background-color: #D3D3D3">
<a id="submit" href="#" onclick="$('#inputForm').submit()" class="easyui-linkbutton" data-options="iconCls:'icon-ok'">提交</a>  
<a id="close" href="#" onclick="parent.$('#${windowid}').window('close')" class="easyui-linkbutton" data-options="iconCls:'icon-no'">关闭</a>  
</div>
</div>
</body>
</html>