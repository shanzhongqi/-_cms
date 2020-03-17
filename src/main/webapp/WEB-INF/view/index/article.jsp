<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="keywords" content="告诉浏览器你的网页的关键字是什么">
<meta name="description" content="告诉浏览器你的网页的主要内容是什么">
<meta name="robot" content="" >
<!-- 视窗 -->
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>${article.title }</title>
<link rel="stylesheet" type="text/css"
	href="/resource/bootstrap.min.css" />

<link rel="stylesheet" type="text/css" href="/resource/index.css" />
<script type="text/javascript" src="/resource/jquery-3.2.1.js"></script>
<!-- 先引入jquery,再引入bootstrap -->
<script type="text/javascript" src="/resource/bootstrap.min.js"></script>


</head>
<body>

	<div class="container-fluid">
		<!-- head -->
		<div class="row">
			<div class="col-md-12"
				style="background-color: #222222; height: 34px">
				<font color="white" size="2px" style="margin-left: 10px">下载APP</font>
			</div>
		</div>

		<div class="row" style="margin-top: 10px">
			<div class="col-md-1"></div>


			<div class="col-md-7">
				<h1>${article.title }</h1>
				<p>${article.user.username }
					<fmt:formatDate value="${article.created }"
						pattern="yyyy-MM-dd HH:mm:ss" />
				</p>
				
				<c:if test="${collect!=null}">
					<button type="button" onclick="deleteCollect()" class="btn btn-info">★&nbsp;  取消收藏</button>
				</c:if>
				<c:if test="${collect==null}">
					<button type="button" onclick="collect()" class="btn btn-info">☆&nbsp;  收藏</button>
				</c:if>
				
				<hr>
				${article.content }

				<hr>
				<c:if test="${sessionScope.user!=null }">
					
				<div>
					<h5>文章评论</h5>
					<textarea rows="8" cols="20" style="width: 753px" name="content"></textarea><br>
					<button type="button" onclick="addComment()" class="btn btn-info">评论</button>
				</div>
				</c:if>
				
				<div>
					<c:forEach items="${info.list }" var="comment">
						<h6>${comment.user.username }<fmt:formatDate value="${comment.created }" pattern="yyyy-MM-dd HH:mm:ss"/>    ${comment.content }   </h6>
						<hr>
					</c:forEach>
					
				</div>
			</div>


			<div class="col-md-4">
				<div class="card" style="width: 18rem; margin-top: 6px">
					<div class="card-header">评论排行榜</div>
					<div class="card-body">
						<!-- 最新文章 --10篇 -->
						<c:forEach items="${info2.list}" var="article" varStatus="i">
						 <p>  ${i.count} ${article.title }</p>
							
						
						</c:forEach>
					</div>
				</div>
			</div>
		</div>


	</div>

	<script type="text/javascript">
		function addComment(){
			var articleId = '${article.id}';
			var content = $("[name='content']").val();
			$.post("/addComment",{articleId:articleId,content:content},function(flag){
				if(flag){
					alert("评论成功");
					window.location.reload();
				}else{
					alert("评论失败，需要登录后才能评论")
				}
			})
		}
		
		
		function collect(){
			var title='${article.title}';
			
			var url = window.location.href;
			
			
			$.post("/collect",{text:title,url:url},function(flag){
				if(flag){
					alert("收藏成功！")
					window.location.reload();
				}else{
					alert("收藏失败，请登录后重试");
				}
					
			})
		}
		
		
		function deleteCollect(){
			var id = "${collect.id}";
			
			$.post("/deleteCollect",{id:id},function(flag){
				if(flag){
					alert("取消收藏成功");
					window.location.reload();
				}else{
					alert("取消收藏成功失败，需要登录后才能取消收藏")
				}
			})
		}
	</script>

</body>
</html>