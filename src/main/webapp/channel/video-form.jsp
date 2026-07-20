<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Quản lý video</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
  </head>
  <body>
    <div class="container">
    	<div class="col-6 offset-3">
    		<form method="post"
    			action="${pageContext.request.contextPath}/video-form"
    			enctype="mutilpart/form-data">
    			<div class="mb-3">
				  <label class="form-label">Tiêu đề</label>
				  <input type="text" class="form-control" name="title">
				</div>
				<div class="mb-3">
				  <label class="form-label">Mô tả</label>
				  <textarea class="form-control" rows="5" cols="" name="desc"></textarea>
				</div>
				<div class="mb-3">
				  <label class="form-label">Danh mục</label>
				  <select name="category" class="form-select" aria-label="Default select example">
					  <option selected>-----------Chọn danh mục--------------</option>
					  <option value="1">One</option>
					  <option value="2">Two</option>
					  <option value="3">Three</option>
					</select>
				</div>
				<div class="mb-3">
				  <label for="formFile" class="form-label">Video file</label>
				  <input class="form-control" type="file" accept="video/*" id="formFile" name="video">
				</div>
				<div class="mb-3">
				  <label for="formFile" class="form-label">Poster file</label>
				  <input class="form-control" type="file" accept="image/*" id="formFile" name="image">
				</div>
				<div class="mb-3">
				  <label for="formFile" class="form-label">Trạng thái</label>
				  <div class="form-check">
					  <input class="form-check-input" type="radio" name="status" value="1" id="radioDefault1">
					  <label class="form-check-label" for="radioDefault1">
					    Chờ duyệt
					  </label>
					</div>
					<div class="form-check">
					  <input class="form-check-input" type="radio" name="status" value="2" id="radioDefault2" checked>
					  <label class="form-check-label" for="radioDefault2">
					    Nháp
					  </label>
					</div>
				</div>
				<input value="Thêm video" class="btn btn-primary" type="submit">
    		</form>
    	</div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>
  </body>
</html>