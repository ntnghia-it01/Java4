<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
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
				  <input value="${bean.title}" type="text" class="form-control" name="title">
				  <small class="text-danger">${bean.errors.errTitle}</small>
				</div>
				<div class="mb-3">
				  <label class="form-label">Mô tả</label>
				  <textarea class="form-control" rows="5" cols="" name="desc">${bean.desc}</textarea>
				  <small class="text-danger">${bean.errors.errDesc}</small>
				</div>
				<div class="mb-3">
				  <label class="form-label">Danh mục</label>
				  <select name="category" class="form-select" aria-label="Default select example">
					  <option value="0" ${bean.category < 1 ? 'selected' : ''}>-----------Chọn danh mục--------------</option>
					  
					  <c:forEach items="${categories}" var="cat">
					  	<option ${bean.category < cat.id ? 'selected' : ''} value="${cat.id}">${cat.name}</option>
					  </c:forEach>
					  
					</select>
					<small class="text-danger">${bean.errors.errCat}</small>
				</div>
				<div class="mb-3">
				  <label for="formFile" class="form-label">Video file</label>
				  <input class="form-control" type="file" accept="video/*" id="formFile" name="video">
				  <small class="text-danger">${bean.errors.errVideo}</small>
				</div>
				<div class="mb-3">
				  <label for="formFile" class="form-label">Poster file</label>
				  <input class="form-control" type="file" accept="image/*" id="formFile" name="image">
				  <small class="text-danger">${bean.errors.errImage}</small>
				</div>
				<div class="mb-3">
				  <label for="formFile" class="form-label">Trạng thái</label>
				  <div class="form-check">
					  <input ${bean.status == 1 ? 'checked' : ''} class="form-check-input" type="radio" name="status" value="1" id="radioDefault1">
					  <label class="form-check-label" for="radioDefault1">
					    Chờ duyệt
					  </label>
					</div>
					<div class="form-check">
					  <input ${bean.status == 2 ? 'checked' : ''} class="form-check-input" type="radio" name="status" value="2" id="radioDefault2">
					  <label class="form-check-label" for="radioDefault2">
					    Nháp
					  </label>
					</div>
					<small class="text-danger">${bean.errors.errStatus}</small>
				</div>
				<input value="Thêm video" class="btn btn-primary" type="submit">
    		</form>
    	</div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>
  </body>
</html>