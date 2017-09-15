<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>部门列表</title>
<%@ include file="/common/common.jsp"%>
<script>
	$(function() {
       $("#grid").treegrid({
    	   onDblClickRow:function(index,row){
    		   var url = '${ctx}/system/sys-menu?method=view&id=' + row.id;
			   open('查看部门信息', url, 600, 400);
    	   }
       });
	});
	
	var toolbar = [{
		text:'增加',
		iconCls:'icon-add',
		handler:function(){
			var row = $('#grid').datagrid('getSelected');
			var id = 0;
			if(row){
				id=row.id;
			}
			var url = '${ctx}/system/sys-menu!input.action';
			open('新增部门信息', url, 600, 400);
		}
	},{
		text:'修改',
		iconCls:'icon-edit',
		handler:function(){
			var row = $('#grid').datagrid('getSelected');
			if(!row){
				$.messager.alert('系统消息', "请选择...", 'info');
			}
			var url = '${ctx}/system/sys-menu!input.action?id=' + row.id;
			open('修改部门信息', url, 600, 400);
		}
	},'-',{
		text:'删除',
		iconCls:'icon-remove',
		handler:function(){
			var row = $('#grid').datagrid('getSelected');
			if(!row){
				$.messager.alert('系统消息', "请选择...", 'info');
				return;
			}
			$.messager.confirm('确认对话框', '确认你是否要删除数据？', function(r) {
				if (r) {
					$.post("${ctx}/system/sys-menu!delete.action", {
						'id':row.id
					}, function(data) {
						if (data.status) {
							$.messager.alert('系统消息', data.msg, 'info',
									function() {
								        //重新加载
										$('#grid').treegrid('reload');
										//清除所有选择的行。	
										$('#grid').datagrid('clearSelections');
									});
						}
					}, "json");
				}
			});
		}
	},{
		text:'刷新',
		iconCls:'icon-reload',
		handler:function(){
			$('#grid').treegrid('reload');
		}
	}];
</script>
</head>
<body>
	<table id="grid" title="部门列表管理" style="width: 100%; height: 100%"
		data-options="idField:'id',treeField:'menuName',fit:true,fitColumns:true,singleSelect:false,rownumbers:true,url:'${ctx}/system/sys-menu!tree.action',pagination:false,toolbar:toolbar,checkbox:true,checkOnSelect:true">
		<thead>
			<tr>
				<th data-options="field:'id',checkbox:true"></th>
				<th data-options="field:'menuName'" width="200">部门名称</th>
				<th data-options="field:'url'" width="150">地址</th>
				<th data-options="field:'status'" width="150">状态</th>
				<th data-options="field:'createTime'" width="150">创建时间</th>
			</tr>
		</thead>
	</table>
</body>
</html>