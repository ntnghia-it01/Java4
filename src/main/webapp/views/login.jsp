<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
		<div class="col-6 offset-3 p-3 text-center">
		<!-- action => Đường dẫn url   -->
		<!-- method => GET || POST -->
		<!-- method == GET => Func doGet trong Servlet se duoc chay -->
			<form action="${pageContext.request.contextPath}/login"
				method="POST">
				<div class="mb-3 text-start">
				  <label class="form-label">Tên tài khoản</label>
				  <input name="username" type="text" class="form-control" id="exampleFormControlInput1" placeholder="Tên tài khoản">
				  <small class="text-danger">${errUsername}</small>
				</div>
				<div class="mb-3 text-start">
				  <label class="form-label">Mật khẩu</label>
				  <input name="password" type="password" class="form-control" id="exampleFormControlInput1" placeholder="Mật khẩu">
				</div>
				
				<button type="submit" class="btn btn-primary">Đăng nhập</button>
			</form>
			
			<div class="mt-3">
				<a href="${pageContext.request.contextPath}/register">Bạn chưa có tài khoản? Đăng ký</a>
			</div>
		</div>
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
		crossorigin="anonymous"></script>
</body>
</html>