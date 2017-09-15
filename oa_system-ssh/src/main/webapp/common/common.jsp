<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- easyui install pack -->
<%
	//清除页面缓存
	response.setHeader("Cache-Control", "no-cache"); //HTTP 1.1    
	response.setHeader("Pragma", "no-cache"); //HTTP 1.0    
	response.setDateHeader("Expires", 0); //prevents caching at the proxy server   
	String ctx = request.getContextPath();
	request.setAttribute("ctx", ctx);
%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" type="text/css" href="${ctx }/common/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${ctx }/common/js/easyui/themes/icon.css?id=2">
<script type="text/javascript" src="${ctx }/common/js/easyui/jquery.min.js"></script>
<script type="text/javascript" src="${ctx }/common/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${ctx }/common/js/easyui/easyui-lang-zh_CN.js?id=2"></script>
<script type="text/javascript" src="${ctx }/common/js/form/jquery.form.3.5.js"></script>
<script type="text/javascript">
	function closeWindow() {
		parent.closeWindow('${windowid}');
	}
	function closeWindow(id) {
		$('#' + id).window('close', true);
	}
	function open(title, url, width, hight) {
		//生成随机数
		var windowid = "windowid"
				+ Math.floor(Math.random() * Math.pow(10, 12));
		if (url.indexOf("?") != -1) {
			url = url + "&windowid=" + windowid;
		} else {
			url = url + "?windowid=" + windowid;
		}
		var html = '<div id="'+windowid+'"><iframe frameborder="0" marginwidth="100%" width="100%" height="100%" src="'
				+ url + '" ></iframe></div>';
		$("body").append(html);
		$('#' + windowid).window({
			title : title,
			width : width,
			height : hight,
			modal : true,
			//当window关闭时把这个窗口的代码清除。
			onClose : function() {
				$('#' + windowid).window('destroy');
			}
		});
	}
</script>
<style type="text/css">
body {
	margin: 1px;
	font-size: 12px;
}

#inputForm table {
	width: 100%;
}

#inputForm table tr td:first-child {
	background-color: #DCDCF1;
	text-align: right;
	width: 20%;
	font-size: 12px;
}

#inputForm table tr td {
	background-color: #F5F5F5;
	height: 30px;
	width: 80%;
}

#inputForm input,select {
	width: 250px;
	height: 18px;
	font-size: 12px;
}

#inputForm .ke-upload-file {
	width: 50px;
}

#inputForm textarea {
	width: 400px;
	height: 180px;
}
.inputForm table {
	width: 100%;
}

.inputForm table tr td:first-child {
	background-color: #DCDCF1;
	text-align: right;
	width: 20%;
	font-size: 12px;
}

.inputForm table tr td {
	background-color: #F5F5F5;
	height: 30px;
	width: 80%;
}

.inputForm input,select {
	width: 250px;
	height: 18px;
	font-size: 12px;
}

.inputForm .ke-upload-file {
	width: 50px;
}

.inputForm textarea {
	width: 400px;
	height: 180px;
}
</style>