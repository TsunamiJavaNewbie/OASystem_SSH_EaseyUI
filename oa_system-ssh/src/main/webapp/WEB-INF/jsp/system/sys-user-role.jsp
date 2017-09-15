<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新增用户</title>
<%@ include file="/common/common.jsp"%>
<script>
$(function() {
	$("#inputForm").submit(function() {
		var flag = $(this).form('enableValidation').form('validate');
		if (flag) {
			var json = $("#inputForm").serializeArray();
			$.post("${ctx}/system/sys-role!roleUser.action", json, function(data) {
				if (data.status) {
					$.messager.alert('系统消息', data.msg, 'info', function() {
						parent.$('#grid').datagrid('reload');
						//ok后的回调方法，去关闭父页面的窗口元素
						parent.$('#${windowid}').window('close');
					});
				}
			}, "json");
		}
		//返回flase ，否则会提交表单
		return false;
	});
	
	<c:forEach var="v" items="${model.roles }">
    $("#role_${v.id }").prop("checked",true);
  	</c:forEach> 
});
</script>
</head>
<body>
<div class="easyui-layout" data-options="fit:true">
<div data-options="region:'center',border:false" style="padding: 10px;">
 <p>${model }</p>  <p>${model.roles }</p>  
<form action="" id="inputForm" method="post" >
 <input type="hidden" name="id" value="${model.id }"> 
<table>
    <c:forEach var="v" items="${roles}">
        <tr>
			<td width="10%" align="right"><input  id="role_${v.id }" type="checkbox" name="roleId" value="${v.id }"></td>
			<td>${v.roleName }</td>
		</tr>
    </c:forEach>
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