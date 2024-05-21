<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
	<title>上传课件</title>

	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<!-- 引入bootstrap -->
	<link rel="stylesheet" type="text/css" href="/css/bootstrap.min.css">
	<!-- 引入JQuery  bootstrap.js-->
	<script src="/js/jquery-3.2.1.min.js"></script>
	<script src="/js/bootstrap.min.js"></script>
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
					    	<h1 style="text-align: center;">上传课件</h1>
						</div>
				    </div>
				    <div class="panel-body">
						<form class="form-horizontal" action="/teacher/addVedio" enctype="multipart/form-data" method="post">
							  <input type="hidden" name="id" value="${id}">
							  <div class="form-group">
							    <label for="inputEmail3" class="col-sm-2 control-label">视频名称</label>
							    <div class="col-sm-10">
							      <input type="text" class="form-control" name="name" id="name" >
							    </div>
							  </div>
							  <div class="form-group">
							    <label for="inputPassword3" class="col-sm-2 control-label">视频文件</label>
							    <div class="col-sm-10">
							      <input type="file" name="fileUrl">
							    </div>
							  </div>
							  <div class="form-group" style="text-align: center">
								<button class="btn btn-default" type="submit">提交</button>
								<button class="btn btn-default" type="button" onclick="back()">返回</button>
							  </div>
						</form>
				    </div>
				    
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
<script>
    $("#nav li:nth-child(3)").addClass("active");
    function back(){
    	window.history.go(-1);  //返回上一页
    }
</script>
</html>