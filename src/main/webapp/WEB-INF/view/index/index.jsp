<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- 视窗 -->
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>今日头条</title>
<link rel="stylesheet" type="text/css"
	href="/resource/bootstrap.min.css" />

<link rel="stylesheet" type="text/css" href="/resource/index.css" />
<script type="text/javascript" src="/resource/jquery-3.2.1.js"></script>
<!-- 先引入jquery,再引入bootstrap -->
<script type="text/javascript" src="/resource/popper.min.js"></script>
<script type="text/javascript" src="/resource/bootstrap.min.js"></script>


</head>
<body>
	<div class="container-fluid">
		<!-- head -->
		<div class="row">
			<div class="col-md-12"
				style="background-color: #222222; height: 34px">
				<font color="white" size="2px" style="margin-left: 10px">下载APP</font>
				<div style="float: right">
					<!-- 从session获取当前有没有登录，如果用户已经登录，则不显示登录和注册 -->
					<c:if test="${null==sessionScope.user}">
						<button type="button" class="btn btn-link" onclick="reg()"
							data-toggle="modal" data-target="#exampleModal"><font color="white" size="2px">注册</font></button>
						<button type="button" class="btn btn-link" onclick="login()"
							data-toggle="modal" data-target="#exampleModal"><font color="white" size="2px">登录</font></button>
					</c:if>
					
					<c:if test="${null !=sessionScope.user}">


						<div class="btn-group dropleft">
							<button type="button" class="btn btn-link dropdown-toggle"
								data-toggle="dropdown" aria-haspopup="true"
								aria-expanded="false">	<font color="white" size="2px">登录信息</font></button>
							<div class="dropdown-menu">
								<a class="dropdown-item" href="#">${sessionScope.user.username }</a> <a
									class="dropdown-item" href="/my">个人中心</a> <a
									class="dropdown-item" href="/passport/logout">注销</a>
							</div>
						</div>
					</c:if>
				</div>
			</div>



		</div>

		<div class="row">
			<div class="col-md-2" style="padding-top: 10px">
				<ul>
					<li style="margin-bottom: 10px"><a class="channel"> <img
							alt="" src="/resource/images/logo-index.png"
							style="width: 108px; height: 27px">

					</a></li>

					<li><a href="/?hot=1"
						class="channel-item ${article.channelId==null?'active':'' }">热点</a>
					</li>

					<c:forEach items="${channels}" var="channel">
						<li><a href="/?channelId=${channel.id }"
							class="channel-item ${article.channelId==channel.id?'active':'' }">${channel.name }</a></li>
					</c:forEach>
				</ul>



			</div>
			<div class="col-md-7">
				<!-- 如果 栏目ID 为null 说明点击 左侧的栏目的热点  则显示轮播图 即广告 -->
				<c:if test="${article.channelId==null}">

					<div style="margin: 5px 5px 5px 5px">
						<div id="carouselExampleCaptions" class="carousel slide"
							data-ride="carousel">
							<ol class="carousel-indicators">
								<c:forEach items="${slides}" var="slide" varStatus="i">
									<li data-target="#carouselExampleCaptions"
										data-slide-to="${i.index }" class="active"></li>
								</c:forEach>
							</ol>
							<div class="carousel-inner">
								<c:forEach items="${slides }" var="slide" varStatus="i">
									<div class="carousel-item ${i.index==0?'active':'' }">
										<img src="/pic/${slide.url }" class="d-block w-100 rounded"
											alt="..." style="width: 350px; height: 320px">
										<div class="carousel-caption d-none d-md-block">
											<h5>${slide.title }</h5>
										</div>
									</div>
								</c:forEach>
							</div>
							<a class="carousel-control-prev" href="#carouselExampleCaptions"
								role="button" data-slide="prev"> <span
								class="carousel-control-prev-icon" aria-hidden="true"></span> <span
								class="sr-only">Previous</span>
							</a> <a class="carousel-control-next" href="#carouselExampleCaptions"
								role="button" data-slide="next"> <span
								class="carousel-control-next-icon" aria-hidden="true"></span> <span
								class="sr-only">Next</span>
							</a>
						</div>

					</div>
					<hr>

				</c:if>



				<!-- 如果栏目ID 不为null 说明点击 左侧的栏目 -->
				<c:if test="${article.channelId!=null }">
					<div class="subchannel">
						<ul>
							<li
								class="sub-item  ${article.categoryId==null?'sub-selected':'' }"><a
								href="/?channelId=${article.channelId}">全部</a></li>
							<c:forEach items="${categorys }" var="category">
								<li
									class="sub-item  ${article.categoryId==category.id?'sub-selected':'' }"><a
									href="/?channelId=${article.channelId}&categoryId=${category.id}">${category.name }</a></li>
							</c:forEach>
						</ul>
					</div>
				</c:if>

				<div>
					<c:forEach items="${info.list }" var="article">
						<div class="media">
							<img src="/pic/${article.picture }" class="mr-3 rounded"
								alt="..." style="width: 100px; height: 100px">
							<div class="media-body">
								<h5 class="mt-0">
									<a href="articleDetail?id=${article.id }" target="_blank">${article.title }</a>
								</h5>
								<p>${article.user.username }·${article.hits }浏览·<fmt:formatDate
										value="${article.created }" pattern="yyyy-MM-dd HH:mm:ss" />
								</p>
							</div>
						</div>
					</c:forEach>

					<jsp:include page="/WEB-INF/view/common/pages.jsp"></jsp:include>
				</div>


			</div>
			<div class="col-md-3">
				
				<!-- 问卷调查 -->
				<div class="card" style="width: 18rem; margin-top: 10px">
					<div class="card-header">评选结果</div>
					<div class="card-body">
						<c:forEach items="${info2.list }" var="a">
							<div class="media">
								<div class="media-body">
									<p><a href="/voteDo?id=${a.id }" target="_blank">${a.title }</a></p>

								</div>
							</div>
							<hr>
						</c:forEach>

					</div>
				</div>
				
				
				
				<!-- 问卷调查 -->
				<div class="card" style="width: 18rem; margin-top: 10px">
					<div class="card-header">问卷调查</div>
					<div class="card-body">
						<c:forEach items="${info2.list }" var="a">
							<div class="media">
								<div class="media-body">
									<p><a href="/voteDo?id=${a.id }" target="_blank">${a.title }</a></p>

								</div>
							</div>
							<hr>
						</c:forEach>

					</div>
				</div>
				
				
			

				<div class="card" style="width: 18rem; margin-top: 10px">
					<div class="card-header">最新文章</div>
					<div class="card-body">
						<c:forEach items="${lastArticles.list }" var="a">
							<div class="media">
								<img src="/pic/${a.picture }" class="mr-3 rounded" alt="..."
									style="width: 60px; height: 60px">
								<div class="media-body">
									<p><a href="/articleDetail?id=${a.id }" target="_blank">${a.title }</a></p>

								</div>
							</div>
							<hr>
						</c:forEach>

					</div>
				</div>
			</div>

		</div>
<!-- 注册\登录 -->
		<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog"
			aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="exampleModalLabel">用户注册</h5>
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="modal-body" id="passport"></div>

				</div>
			</div>
		</div>
	</div>

	<script type="text/javascript">
	
	
		function goPage(page) {
			var channelId = '${article.channelId}';
			var categoryId = '${article.categoryId}';

			var url = "/?channelId=" + channelId + "&page=" + page;
			if (categoryId != "") {
				url += "&categoryId=" + categoryId;
			}

			location.href = url;
		}
		
		//注册
		function reg() {
			$("#title").append("用户注册");
			$("#passport").load("/passport/reg");
		}
		//登录
		function login() {
			$("#title").append("用户登录");
			$("#passport").load("/passport/login");
		}
		
		
	</script>


</body>
</html>