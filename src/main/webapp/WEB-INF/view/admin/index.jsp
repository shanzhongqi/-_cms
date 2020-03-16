<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- 视窗 -->
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>今日头头条-管理员中心</title>
<link rel="stylesheet" type="text/css"
	href="/resource/bootstrap.min.css" />
<script type="text/javascript" src="/resource/jquery-3.2.1.js"></script>
<script type="text/javascript" src="/resource/popper.min.js"></script>
<script type="text/javascript" src="/resource/bootstrap.min.js"></script>
</head>
<body>
	<div class="container-fluid">
		<!-- 头 -->
		<div class="row" style="background-color: #009FD9; height: 55px">
			<div class="col-md-12">
				<img alt="" src="/resource/images/。。.png" width="55px" height="55px">
				<font color="white">今日头条-管理员中心</font>
				
				<div class="btn-group dropleft" style="float: right">
					<button type="button" class="btn btn-link dropdown-toggle"
						data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
						<font color="white" size="2px">登录信息</font>
					</button>
					<div class="dropdown-menu">
						<a class="dropdown-item" href="#">${sessionScope.admin.username }</a>
						<a class="dropdown-item" href="/passport/logout">注销</a>
					</div>
				</div>
			</div>
		</div>

		<div class="row" style="padding-top: 10px">
			<!-- 左侧菜单 -->
			<div class="col-md-2" class="col-md-2 rounded"
				style="background-color: #ccc; text-align: center; margin-left: 10px">
				<div class="list-group">
					<a href="#"
						class="list-group-item list-group-item-action list-group-item-primary active"
						href="#" data="/admin/articles"> 文章审核</a> <a href="#"
						class="list-group-item list-group-item-action list-group-item-secondary"
						data="/admin/users" href="#">用户管理</a> <a href="#"
						class="list-group-item list-group-item-action list-group-item-success">栏目审核</a>
					<a href="#"
						class="list-group-item list-group-item-action list-group-item-danger">分类审核</a>
					<a href="#"
						class="list-group-item list-group-item-action list-group-item-warning">系统审核</a>
				</div>
			</div>
			<!-- 内容显示区域 -->
			<div class="col-md-9" id="center"></div>
		</div>
	</div>
</body>

<script type="text/javascript">
	$(function() {
		$("#center").load("/admin/articles");

		$("a").click(function() {
			var url = $(this).attr("data");
			//去除样式
			$("a").removeClass("active");
			//让当前点击的li 添加选中样式
			$(this).addClass("list-group-item active")
			//在中间区域显示url的内容
			$("#center").load(url);
		})

	})
</script>


</html>