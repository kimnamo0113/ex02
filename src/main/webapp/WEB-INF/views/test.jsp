<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>

<script type="text/javascript">
	$(function() {
		var bno = 8202;
		
		$("#btnList").click(function() {
			$.ajax({
				url:"replies/all/"+bno,
				type:"get",
				dataType:"json",
				success:function(res){
					console.log(res);
				}
			})
		});
	})
</script>

</head>
<body>
	<h1>Replies</h1>
	<button id="btnList">get List</button>
</body>
</html>