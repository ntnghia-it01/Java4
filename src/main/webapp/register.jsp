<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Đăng ký</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
  </head>
  <body>
    <div class="container">
    	<div class="col-6 offset-3">
    		<form method="post"
    			action="${pageContext.request.contextPath}/register">
    			<div class="mb-3">
				  <label class="form-label">Email</label>
				  <input type="text" class="form-control" name="email">
				</div>
				<div class="mb-3">
				  <label class="form-label">Mật khẩu</label>
				  <input type="password" class="form-control" name="password">
				</div>
				<div class="mb-3">
				  <label class="form-label">Họ và tên</label>
				  <input type="text" class="form-control" name="name">
				</div>
				
				<input value="Đăng ký" class="btn btn-primary" type="submit">
    		</form>
    	</div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>
  </body>
</html>