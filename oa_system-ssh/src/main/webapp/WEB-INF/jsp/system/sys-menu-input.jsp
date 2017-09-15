<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新增角色</title>
<%@ include file="/common/common.jsp"%>
<script>
 

$(function() {
	$("#inputForm").submit(function() {
		var flag = $(this).form('enableValidation').form('validate');
		if (flag) {
			var json = $("#inputForm").serializeArray();
			$.post("${ctx}/system/sys-menu!saveOrUpdate.action", json, function(data) {
				if (data.status) {
					$.messager.alert('系统消息', data.msg, 'info', function() {
						parent.$('#grid').treegrid('reload');
						//ok后的回调方法，去关闭父页面的窗口元素
						parent.$('#${windowid}').window('close');
					});
				}
			}, "json");
		}
		//返回flase ，否则会提交表单
		return false;
	});
	 //初始化对象
	$('#status').combobox('setValues', ['${model.status}']);

	$('#pid').combotree({    
	    url: '${ctx}/system/sys-menu!tree.action',    
	    required: true   
	});  
});

</script>
</head>
<body>
<div class="easyui-layout" data-options="fit:true">
<div data-options="region:'center',border:false" style="padding: 10px;">
<form action="" id="inputForm" method="post" class="easyui-form" data-options="novalidate:true" >
<input type="hidden" name="id" value="${model.id }">
<table>
	<tr>
		<td>部门名称</td>
		<td><input type="text" value="${model.menuName }" name="menuName" class="easyui-textbox" data-options="required:true"></input></td>
	</tr>
	<tr>
		<td>地址</td>
		<td><input type="text" value="${model.url }" name="url" class="easyui-textbox"></input></td>
	</tr>
	<tr>
		<td>上级节点${model.parent.id }1</td>
		<td><input id="pid" type="text" value="${model.parent.id }" name="parent.id" class="easyui-textbox" data-options="required:true"></input></td>
	</tr>
	<tr>
		<td>状态</td>
		<td>
		  <select id="status" name="status" class="easyui-combobox" data-options="required:true">
		    <option></option>
		    <option value="1">有效</option>
		    <option value="0">无效</option>
		  </select>
		</td>
	</tr>
</table>
</form>
</div>
<div data-options="region:'south',border:false" style="text-align: right; margin-bottom:0px; padding: 5px; background-color: #D3D3D3">
<a id="submit" href="#" onclick="$('#inputForm').submit()" class="easyui-linkbutton" data-options="iconCls:'icon-ok'">提交</a>  
<a id="close" href="#" onclick="parent.$('#${windowid}').window('close')" class="easyui-linkbutton" data-options="iconCls:'icon-no'">关闭</a>  
</div>
</div>
</body>
</html>