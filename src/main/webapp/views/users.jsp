<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65"
	crossorigin="anonymous">
</head>
<body>
	<div class="container">
		<a href="${pageContext.request.contextPath}/register"
			class="btn btn-primary mt-5">Đăng ký</a>

		<div class="mt-5">
			<table class="table">
				<thead>
					<tr>
						<th scope="col">ID</th>
						<th scope="col">Tên tài khoản</th>
						<th scope="col">Họ và tên</th>
						<th scope="col">Email</th>
						<th scope="col">Vai trò</th>
						<th scope="col">Trạng thái</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${users}" var="item">
					<!-- biến abc ở mỗi vòng lập tưng ứng với 1 đối tượng UserEntity  -->
						<tr>
							<th scope="row">${item.id}</th>
							<td>${item.username}</td>
							<td>${item.name}</td>
							<td>${item.email}</td>
							<%-- <c:if test="${item.role == 0}">
								<td>Admin</td>
							</c:if>
							<c:if test="${item.role == 1}">
								<td>User</td>
							</c:if> --%>
							
							<td>${item.role == 0 ? "Admin" : "User"}</td>
							<!-- 0 == inactive, 1 == active  -->
							<td>${item.status == 0 ? "Inactive" : "Active"}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
		crossorigin="anonymous"></script>
</body>
</html>