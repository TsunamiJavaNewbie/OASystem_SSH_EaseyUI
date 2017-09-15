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
			var formData = new FormData($("#inputForm")[0]);
			$.ajax({
				url : '${ctx}/system/sys-user!saveOrUpdate.action',
				type : 'POST',
				data : formData,
				async : false,
				cache : false,
				contentType : false,//以文件流形式提交
				processData : false,//以文件流形式提交
				dataType:'json',
				success : function(data) {
					if (data.status) {
						$.messager.alert('系统消息', data.msg, 'info', function() {
							parent.$('#grid').datagrid('reload');
							//ok后的回调方法，去关闭父页面的窗口元素
							parent.$('#${windowid}').window('close');
						});
					}
				},
				error : function(returndata) {
					alert(returndata);
				}
			});
		}
		//返回flase ，否则会提交表单
		return false;
	});
	$('#sex').combobox('setValues', ['${model.sex}']);
	$('#status').combobox('setValues', ['${model.status}']);
	
	$('#pid').combotree({    
	    url: '${ctx}/system/sys-department!list.action',    
	    required: true
	}); 
});
</script>
</head>
<body>
<body>
	<div class="easyui-layout" data-options="fit:true">
		<div data-options="region:'center',border:false" style="padding: 10px;">
			<form action="${ctx}/system/sys-user!saveOrUpdate.action" id="inputForm" method="post" enctype="multipart/form-data">
			    <input type="hidden" name="id" value="${model.id }">
				<table>
					<tr>
						<td>用户名称</td>
						<td><input type="text" name="username" class="easyui-textbox" data-options="required:true" value="${model.username }"></input></td>
					</tr>
					<tr>
						<td>用户密码</td>
						<td><input type="password" name="password" class="easyui-textbox" data-options="required:true" value="${model.password }"></input></td>
					</tr>
					<tr>
						<td>用户部门</td>
						<td><input id="sysDepartment.id" name="sysDepartment.id" class="easyui-combotree" value="${model.sysDepartment.id }"
							data-options="url:'${ctx}/system/sys-department!tree.action',method:'get'" ></td>
					</tr>
					<tr>
						<td>性别</td>
						<td><select id="sex" name="sex" class="easyui-combobox" data-options="required:true">
								<option></option>
								<option value="男">男</option>
								<option value="女">女</option>
						</select></td>
					</tr>
					<tr>
						<td>手机号码</td>
						<td><input type="text" name="mobile" class="easyui-textbox" data-options="required:true" value="${model.mobile }" ></input></td>
					</tr>
					<tr>
						<td>照片</td>
						<td><input type="text" name="upload" class="easyui-filebox" data-options="required:true" value="${model.urlPhoto }" ></input></td>
					</tr>
					<tr>
						<td>状态</td>
						<td><select id="status" name="status" class="easyui-combobox" data-options="required:true">
								<option></option>
								<option value="1">有效</option>
								<option value="0">无效</option>
						</select></td>
					</tr>
				</table>
			</form>

		</div>
		<div data-options="region:'south',border:false" style="text-align: right; margin-bottom: 0px; padding: 5px; background-color: #D3D3D3">
			<a id="submit" href="#" onclick="$('#inputForm').submit()" class="easyui-linkbutton" data-options="iconCls:'icon-ok'">提交</a> <a id="close"
				href="#" onclick="parent.$('#${windowid}').window('close')" class="easyui-linkbutton" data-options="iconCls:'icon-no'">关闭</a>
		</div>
	</div>
</body>
</html>