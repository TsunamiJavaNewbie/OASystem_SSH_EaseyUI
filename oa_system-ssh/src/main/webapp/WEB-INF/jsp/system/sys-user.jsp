<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>员工列表</title>
<%@ include file="/common/common.jsp"%>
<script type="text/javascript">
$(function(){
	  var pager = $('#grid').datagrid('getPager');
      pager.pagination({
			buttons:[{
				text : '新增',
				iconCls:'icon-add',
				handler:function(){
					var url = '${ctx}/system/sys-user!input.action';
					open('新增员工信息', url, 600, 480);
				}
			},'-',{
				text : '修改',
				iconCls:'icon-edit',
				handler:function(){
					var row = $('#grid').datagrid('getSelected');
					if(!row){
						$.messager.alert('系统消息', '请选择一条记录进行修改！', 'info');
						return;
					}
					var url = '${ctx}/system/sys-user!input.action?id=' + row.id;
					open('修改员工信息', url, 600, 480);
				}
			},'-',{
				text : '删除',
				iconCls:'icon-remove',
				handler:function(){
					$.messager.confirm('确认对话框', '确认你是否要删除数据？', function(r) {
						if (r) {
							var row = $('#grid').datagrid('getSelections');
							var ids = "";
							for(var i=0;i<row.length;i++){
								ids += row[i].id+",";
							}
							ids = ids.substring(0,ids.length-1);
							$.post("${ctx}/system/sys-user!delete.action", {
								'ids':ids
							}, function(data) {
								if (data.status) {
									$.messager.alert('系统消息', data.msg, 'info',
											function() {
												$('#grid').datagrid('reload');
											});
								}
							}, "json");
						}
					});
				}
			},'-',{
				text : '角色分配',
				iconCls:'icon-add',
				handler:function(){
					var row = $('#grid').datagrid('getSelected');
					if(!row){
						$.messager.alert('系统消息', '请选择一条记录进行修改！', 'info');
						return;
					}
					var url = '${ctx}/system/sys-user!role.action?id=' + row.id;
					open('角色分配', url, 600, 480);
				}
			},{
				text : '导出',
				iconCls:'icon-filter',
				handler:function(){
					$.messager.confirm('确认对话框', '确认你是否要导出当前的数据？', function(r) {
						if (r) {
							window.location.href='${ctx}/system/sys-user!export.action?username='+$("#username").val();
						}
					});
				}
			}]
		});
       
        //条件查询
       $("#search").click(function(){
    	    $('#grid').datagrid('reload',{
    	    	username: $("#username").val(),
    	    	departmentId: $('#departmentId').combo('getValue')
    		});
       });
	
});
</script>
</head>
<body>
	<table id="grid" title="员工列表管理" class="easyui-datagrid" style="width: 100%; height: 100%"
		data-options="fit:true,fitColumns:true,singleSelect:false,rownumbers:true,url:'${ctx}/system/sys-user!list.action',pagination:true,toolbar:'#tb',checkbox:true,checkOnSelect:true">
		<thead>
			<tr>
				<th data-options="field:'id',checkbox:true"></th>
				<th data-options="field:'username',width:100">用户名称</th>
				<th data-options="field:'department_name',width:100">部门名称</th>
				<th data-options="field:'mobile',width:100">手机号码</th>
				<th data-options="field:'sex',width:100">性别</th>
				<th data-options="field:'status',width:100">状态</th>
				<th data-options="field:'create_time',width:100">创建时间</th>
			</tr>
		</thead>
	</table>
	<div id="tb" style="padding: 2px 5px;">
		用户名称： <input id="username" name="username" class="easyui-textbox"></input> 部门: <input id="departmentId" name="departmentId"
			class="easyui-combotree" data-options="url:'${ctx}/system/department!tree.action',method:'get'" style="width: 200px;"> <a id="search"
			href="#" class="easyui-linkbutton" iconCls="icon-search">查询</a>
	</div>
</body>
</html>