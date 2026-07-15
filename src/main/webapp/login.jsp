<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
</head>
  <body>
    <div class="container">
    	<div class="col-6 offset-3">
    		<form method="post"
    			action="${pageContext.request.contextPath}/login">
    			<div class="mb-3">
				  <label class="form-label">Email</label>
				  <!-- bean.email => bean.getEmail(); -->
				  <input value="${bean.email}" type="text" class="form-control" name="email">
				  <small>${bean.errors.errEmail}</small>
				</div>
				<div class="mb-3">
				  <label class="form-label">Mật khẩu</label>
				  <input value="${bean.password}" type="password" class="form-control" name="password">
				  <small>${bean.errors.errPassword}</small>
				</div>
				
				<input value="Đăng nhập" class="btn btn-danger" type="submit">
    		</form>
    	</div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>
</body>
</html>