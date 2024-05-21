<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
	<title>留言信息显示</title>

	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<!-- 引入bootstrap -->
	<link rel="stylesheet" type="text/css" href="/css/bootstrap.min.css">
	<!-- 引入JQuery  bootstrap.js-->
	<script src="/js/jquery-3.2.1.min.js"></script>
	<script src="/js/bootstrap.min.js"></script>

	<%--<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">--%>

</head>
<body>
	<!-- 顶栏 -->
	<jsp:include page="top.jsp"></jsp:include>
	<!-- 中间主体 -->
	<div class="container" id="content">
		<div class="row">
			<jsp:include page="menu.jsp"></jsp:include>
			<div class="col-md-10">
				<div class="panel panel-default">
				    <div class="panel-heading">
						<div class="row">
					    	<h1 class="col-md-5">留言列表</h1>
							<%--<form class="bs-example bs-example-form col-md-5" role="form" style="margin: 20px 0 10px 0;" action="/teacher/selectCourse" id="form1" method="post">--%>
								<%--<div class="input-group">--%>
									<%--<input type="text" class="form-control" placeholder="请输入课程名" name="findByName">--%>
									<%--<span class="input-group-addon btn" onclick="document.getElementById('form1').submit" id="sub">搜索</span>--%>
								<%--</div>--%>
							<%--</form>--%>
						</div>
				    </div>
				    
				    <table class="table table-bordered">
					        <thead>
					            <tr>
									<th>编号</th>
									<th>视频ID</th>
									<th>学生姓名</th>
									<th>留言</th>
					            </tr>
					        </thead>
					        <tbody>
							<c:forEach  items="${msgList}" var="item">
								<tr>
									<td>${item.id}</td>
									<td>${item.vid}</td>
									<td>${item.sName}</td>
									<td>${item.content}</td>
								</tr>
							</c:forEach>
					        </tbody>
				    </table>

				</div>

			</div>
		</div>
	</div>
	<div class="container" id="footer">
		<div class="row">
			<div class="col-md-12"></div>
		</div>
	</div>
</body>
	<script type="text/javascript">
		<%--设置菜单中--%>
		$("#nav li:nth-child(3)").addClass("active");

	</script>
</html>