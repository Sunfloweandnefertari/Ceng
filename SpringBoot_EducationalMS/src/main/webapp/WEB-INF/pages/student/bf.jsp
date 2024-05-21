<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>

</head>
<body>

<video width="320" height="240" controls>
  <source src="http://localhost:8080/vedio/${url}" type="video/mp4">
  <source src="${url}" type="video/ogg">
  <source src="${url}" type="video/webm">
  <object data="movie.mp4" width="320" height="240">
	<embed src="http://localhost:8080/vedio/${url}" width="320" height="240">
  </object>
</video>
</body>
</html>


