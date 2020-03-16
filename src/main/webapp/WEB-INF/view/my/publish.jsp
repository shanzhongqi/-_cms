<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("UTF-8");
String htmlData = request.getParameter("content1") != null ? request.getParameter("content1") : "";
%>
<!doctype html>
<html>
<head>
	<meta charset="utf-8" />
	<title>KindEditor JSP</title>
		<link rel="stylesheet" href="/resource/kindeditor/themes/default/default.css" />
	<link rel="stylesheet" href="/resource/kindeditor/plugins/code/prettify.css" />
	<script charset="utf-8" src="/resource/kindeditor/kindeditor.js"></script>
	<script charset="utf-8" src="/resource/kindeditor/lang/zh-CN.js"></script>
	<script charset="utf-8" src="/resource/kindeditor/plugins/code/prettify.js"></script>
	<script>
		KindEditor.ready(function(K) {
			window.editor1 = K.create('textarea[name="content1"]', {
				cssPath : '/resource/kindeditor/plugins/code/prettify.css',
				uploadJson : '/resource/kindeditor/jsp/upload_json.jsp',
				fileManagerJson : '/resource/kindeditor/jsp/file_manager_json.jsp',
				allowFileManager : true,
				afterCreate : function() {
					var self = this;
					K.ctrl(document, 13, function() {
						self.sync();
						document.forms['example'].submit();
					});
					K.ctrl(self.edit.doc, 13, function() {
						self.sync();
						document.forms['example'].submit();
					});
				}
			});
			prettyPrint();
		});
	</script>
</head>
<body>
	<form id="form1">
		<div class="form-group">
			<label for="title">文章标题：</label>
			<input id="title" type="text" name="title" class="form-control form-control-sm col-sm-10">
		</div>		
		
		<div class="form-group form-inline">
			<label for="">所属栏目：</label>
			<select class="form-control form-control-sm" id="channels" name="channelId">
				<option></option>
			</select>
			
			<label for="">所属分类：</label>
			<select class="form-control form-control-sm" id="categorys" name="categoryId">
				<option></option>
			</select>
		</div>
		
		<div class="form-group">
			标题图片:<input type="file" name="file">
		</div>
	
		<textarea name="content1" cols="100" rows="8" style="width:1050.83px;height:200px;visibility:hidden;"><%=htmlspecialchars(htmlData)%></textarea>
		<br />
		<input type="button" name="button" class="btn btn-primary" onclick="publish()" value="提交内容" />
	</form>
</body>

<script type="text/javascript">
	function publish(){
	    var formData = new FormData($("#form1")[0]);
		
	    
	    formData.set("content",editor1.html());
	    
	    $.ajax({
	    	type:"post",
	    	url:"my/publish",
	    	processData:false,
	    	contentType:false,
	    	data:formData,
	    	success:function(flag){
	    		if(flag){
	    			alert("发布成功！");
	    			location.href = "/my";
	    		}
	    	}
	    })
	}
	
</script>


<script type="text/javascript">
	$(function(){
		$.get("/channel/channels",function(list){
			for(var i in list){
				$("#channels").append("<option value='"+list[i].id+"'>"+list[i].name+"</option>")
			}
		})
		
		$("#channels").change(function(){
			var channelId = $(this).val();
			
			$.get("/channel/selectsByChannelId",{channelId:channelId},function(list){
				$("#categorys").empty();
				
				
				for(var i in list){
					$("#categorys").append("<option value='"+list[i].id+"'>"+list[i].name+"</option>")
				}
			})
		})
		
	})
</script>


</html>
<%!
private String htmlspecialchars(String str) {
	str = str.replaceAll("&", "&amp;");
	str = str.replaceAll("<", "&lt;");
	str = str.replaceAll(">", "&gt;");
	str = str.replaceAll("\"", "&quot;");
	return str;
}
%>