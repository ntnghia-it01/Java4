<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
</head>
<body>
	<div class="container">
		<a href="${pageContext.request.contextPath}/channel/video-form" class="btn btn-primary mt-3 mb-3">Thêm video</a>
		<table class="table">
		  <thead>
		    <tr>
		      <th scope="col">ID</th>
		      <th scope="col">Tên</th>
		      <th scope="col">Danh mục</th>
		      <th scope="col">Video</th>
		      <th scope="col">Ảnh</th>
		      <th scope="col">Trạng thái</th>
		      <th scope="col">Hành động</th>
		    </tr>
		  </thead>
		  <tbody>
		  	<c:forEach items="${videos}" var="video">
		  		<tr>
			      <th scope="row">${video.id}</th>
			      <td>${video.title}</td>
			      <td>${video.categoryEntity.name}</td>
			      <td>
			      	<video 
			      		width="300" 
			      		height="200" 
			      		controls 
			      		poster="${pageContext.request.contextPath}${video.thumnailURL}">
					  <source 
					  	src="${pageContext.request.contextPath}${video.videoURL}" 
					  	type="video/mp4">
					</video>
			      </td>
			      <td>
			      	<img 
			      		src="${pageContext.request.contextPath}${video.thumnailURL}" 
			      		width="100" 
			      		height="70"/>
			      </td>
			      <td>${video.statusString}</td>
			      <td>@mdo</td>
			    </tr>
		  	</c:forEach>
		  </tbody>
		</table>
	</div>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>
</body>
</html>