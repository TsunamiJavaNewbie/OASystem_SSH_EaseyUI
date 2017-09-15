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
					var url = '${ctx}/system/sys-job!input.action';
					open('新增职位信息', url, 600, 480);
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
					var url = '${ctx}/system/sys-job!input.action?id=' + row.id;
					open('修改职位信息', url, 600, 480);
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
							$.post("${ctx}/system/sys-job!delete.action", {
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
			}]
		});
       
        //条件查询
       $("#search").click(function(){
    	    $('#grid').datagrid('reload',{
    	    	jobname: $("#jobname").val()
    		});
       });
	
});
</script>
</head>
<body>
	<table id="grid" title="职位管理" class="easyui-datagrid" style="width: 100%; height: 100%"
		data-options="fit:true,fitColumns:true,singleSelect:false,rownumbers:true,url:'${ctx}/system/sys-job!list.action',pagination:true,toolbar:'#tb',checkbox:true,checkOnSelect:true">
		<thead>
			<tr>
				<th data-options="field:'id',checkbox:true"></th>
				<th data-options="field:'job_name',width:100">职位名称</th>
				<th data-options="field:'job_code',width:100">职位编码</th>
				<th data-options="field:'status',width:100">状态</th>
				<th data-options="field:'create_time',width:100">创建时间</th>
			</tr>
		</thead>
	</table>
	<div id="tb" style="padding: 2px 5px;">
		职位名称： <input id="jobName" name="jobname" class="easyui-textbox"></input><a id="search" href="#" class="easyui-linkbutton" iconCls="icon-search">查询</a>
	</div>
</body>
</html>