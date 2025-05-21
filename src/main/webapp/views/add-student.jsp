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
			<form action="${pageContext.request.contextPath}/add-student"
				method="POST">
				<div class="mb-3 text-start">
					<label class="form-label">MSSV</label>
					<input type="text"
						class="form-control"
						placeholder="MSSV"
						name="code"
						value="${code}">
				</div>
				<div class="mb-3 text-start">
					<label class="form-label">Họ và tên</label>
					<input type="text"
						class="form-control"
						placeholder="Họ và tên"
						name="name">
				</div>
				<div class="mb-3 text-start">
					<label class="form-label">Số điện thoại</label>
					<input type="text"
						class="form-control"
						placeholder="Số điện thoại"
						name="phone">
				</div>
				
				<div class="mb-3 text-start">
					<label class="form-label">Giới tính </label>
					<div class="form-check">
					  <input name="gender" value="0" class="form-check-input" type="radio" id="flexRadioDefault1">
					  <label class="form-check-label" for="flexRadioDefault1">
					  	Nam 
					  </label>
					</div>
					<div class="form-check">
					  <input name="gender" value="1" class="form-check-input" type="radio" id="flexRadioDefault2" checked>
					  <label class="form-check-label" for="flexRadioDefault2">
					    Nữ 
					  </label>
					</div>
				</div>
				
				<div class="mb-3 text-start">
					<label class="form-label">Ngành học </label>
					<select name="major" class="form-select" aria-label="Default select example">
					  <option value="-1" selected>------Chọn ngành học--------</option>
					  <option value="1">PTPM - Java </option>
					  <option value="2">PTPM - C#</option>
					  <option value="3">LTW</option>
					  <option value="4">LTG</option>
					</select>
				</div>

				<button type="submit" class="btn btn-primary">Thêm sinh viên</button>
			</form>
		</div>
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
		crossorigin="anonymous"></script>
</body>
</html>