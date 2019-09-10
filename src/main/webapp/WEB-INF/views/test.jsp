<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>


<style>
	li.replyLi div.item{
		border-bottom: 1px solid #ddd;
		padding : 5px;
		width : 400px;
		position : relative;
		
	}
	
	li.replyLi div.item div.btnWrap{
		position:absolute;
		right: 10px;
		top:5px;
	}
	
	#modWrap{
		width:300px;
		height: 100px;
		background-color: lightgray;
		position: absolute;
		top:35%;
		left:35%;
		padding:10px;
		display:none;
	}
	.pagination{
		list-style: none;
		overflow: hidden;
	}
	.pagination li{
		float:left;
		padding:3px;
		border: 1px solid gray;
	}
	.pagination li a{
		color:black;
		text-decoration: none;
	}
	
	.pagination li.active{
		background-color: blue;
	}
	.pagination li.active a{
		color:white
	}
</style>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>

<script type="text/javascript">
	var bno = 8202;
	var currentPage = 1;

	function getListAll(page){
			
		$.ajax({
			url:"replies/"+bno+"/"+page,
			type:"get",
			dataType:"json",
			success:function(res){
				console.log(res);
				
				$("#replies").empty();
				
				$(res.list).each(function(i,obj) {
					var $li = $("<li>").addClass("replyLi");
					var $divItem = $("<div>").addClass("item");
					var $spanRno = $("<span>").addClass("rno").text(obj.rno);
					var $spanWriter = $("<span>").addClass("writer").text(obj.replyer);
					var $spanContent = $("<span>").addClass("content").text(obj.replytext);
					var $divWrap = $("<div>").addClass("btnWrap");
					var $btnMod = $("<button>").addClass("modify").text("수정").attr("data-rno",obj.rno);
					var $btnDel = $("<button>").addClass("delete").text("삭제").attr("data-rno",obj.rno);
					
					$divWrap.append($btnMod).append($btnDel);
					$divItem.append($spanRno).append(" : ").append($spanWriter).append("<br>").append($spanContent).append($divWrap);
					$li.append($divItem);
					$("#replies").append($li);
					
				})
				
				printPaging(res);
			}
		})
	}

	function printPaging(res){
		$(".pagination").empty();
		for(var i=res.pageMaker.startPage; i<=res.pageMaker.endPage; i++){
			var $li = $("<li>");
			var $a = $("<a>").text(i).attr("href","#");
			
			if(res.pageMaker.cri.page == i){
				$li.addClass("active");
			}
			
			$li.append($a);
			$(".pagination").append($li);
		}
	}

	$(function() {
		
		$("#btnList").click(function() {
			getListAll(1);
		});
		
		$(document).on("click",".pagination a",function(e){
			e.preventDefault();
			var page = $(this).text();
			currentPage=page;
			
			getListAll(page);
		})
		
		$("#btnAdd").click(function() {
			
			var replyer = $("#replyer").val();
			var replytext = $("#replytext").val();
			
			//@RequestBody
			//var str = "{bno:bno,replyer:replyer,replytext:replytext}";
			var json = {bno:bno,replyer:replyer,replytext:replytext};
			var data = JSON.stringify(json)
			
			$.ajax({
				url:"replies",
				type:"post",
				headers:{
					"Content-Type":"application/json"
				},
				//get방식일때 쓴거
				data:data,
				dataType:"text",
				success:function(res){
					console.log(res);
				}
				
			})
			
		})
		
		
		$(document).on("click","button.delete",function(){
			var rno = $(this).attr("data-rno");
			
			$.ajax({
				url:"replies/"+rno,
				type:"delete",
				dataType:"text",
				success:function(res){
					console.log(res);
					if(res=='success'){
						alert("삭제되었습니다.");
						getListAll(1);
					}
				}
				
			})
		})
		
		
		$(document).on("click","button.modify",function(){
			var rno = $(this).attr("data-rno");
			
			$("#modWrap").show();
			$("#modWrap .title").text($(this).attr("data-rno"));
			var content = $(this).parents(".item").find(".content").text();
			$("#modText").val(content);
			
			
			
			
		})
		
		$("#btnClose").click(function() {
			$("#modWrap").hide();
		})
		
		$("#btnSaveMod").click(function() {
			
			var replytext=$("#modText").val();
			var rno=$(this).parents("#modWrap").find(".title").text();
			
			var json = {replytext:replytext};
			var data = JSON.stringify(json)
			
			$.ajax({
				url:"replies/"+rno,
				type:"put",
				data:data,
				dataType:"text",
				headers:{
					"Content-Type":"application/json"
				},
				success:function(res){
					console.log(res);
					if(res=='success'){
						alert("수정되었습니당.");
						getListAll(currentPage);
						$("#modWrap").hide();
					}
				}
				
			})
		})
		
	})
</script>
</head>
<body>
	<h1>Replies</h1>
	<div>
		<div>
			REPLYER
			<input type="text" name="replyer" id="replyer">
		</div>
		<div>
			REPLY TEXT
			<input type="text" name="replytext" id="replytext">
		</div>
		<button id="btnAdd">ADD REPLY</button>
	</div>
	
	<button id="btnList">get List</button>
	<ul id="replies">
		<!-- <li class="replyLi">
			<div class="item">
				<span class="rno">1</span> : <span class="writer">작성자 이름</span><br>
				<span class="content">댓글 내용</span>
				<div class="btnWrap">
					<button class="modify">수정</button>
					<button class="delete" data-rno="1">삭제</button>
				</div>
			</div>
		</li> -->
	</ul>
	<ul class="pagination">
		<!-- <li><a href="#">1</a></li>
		<li><a href="#">2</a></li> -->
	</ul>
	<div id="modWrap">
		<div class="title"></div><!-- rno번호가 들어가도록 할것임 -->
		<div>
			<input type="text" id="modText"> <!-- 내용이 들어가도록 할것임 -->
		</div>
		<div>
			<button id="btnSaveMod">수정</button>		
			<button id="btnClose">닫기</button>		
		</div>
	</div>
	
</body>
</html>