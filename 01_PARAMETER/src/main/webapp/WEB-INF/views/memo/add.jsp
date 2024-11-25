<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>MEMO ADD</h1>
	
	<form action="${pageContext.request.contextPath}/memo/add" method="post">
		<div>
			<label>id : </label>
			<input name="id" />
		</div>
		<div>
			<label>text : </label>
			<textarea name="text" /></textarea>
		</div>
		<div>
			<label>writer : </label>
			<input name="writer" />
		</div>
		
		<div>
			<label>createAt : </label>
			<input type="datetime-local" name="createAt" />
		</div>
		
		<div>

			<input type="submit" value="메모쓰기" />
		</div>
	</form>
</body>
</html>