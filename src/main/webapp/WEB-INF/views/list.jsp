<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
<style type="text/css">
	body{
	
	}
	div#wrap{
		width:900px; height: 500px;
		margin: 0 auto;
	}
	section{
		width:700px;
		height: 800px;
		border: 1px dotted #ccc;
		margin-top: 10px;
	}
	div#box{
		width:800px;
		height:600px;
		border: 1px dotted #ccc;
		margin-top: 10px;
		overflow: scroll;
	}
	header{
		width:700px;
		height:50px;
		
		margin-bottom: 20px;
		text-align: right;
	}
	form{
		clear: both;
	}
	#toggle{
		position: fixed;
		
		left:50px;
		top:100px;
		height:auto;
		background: #ccc;
		z-index: 100;
		display: none;
	}
	section img{
		cursor: pointer;
	}
	section div.left{
		float: left;
		text-align: center;
		margin: 10px;
	}
	section .box-footer{
		clear: both;
	}
	
	
	#imgToggle{
		
		left:20px;
		top:20px;
		width:95%;
		height:800px;
		background: #ccc;
		z-index: 100;
		display: none;
		position: fixed;
	}
	#imgToggle span{
		position: absolute;
		right: 10px;
		top:10px;
	}
</style>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>

</head>
<body>
<div id="wrap">
	<header>
		<c:if test="${Auth==null }">
			<a href="auth/login">login</a>
			<a href="auth/join">join</a>
		</c:if>
		<c:if test="${Auth!=null }">
			<h3>${Auth.userid }님 반갑습니데이</h3>
			<a href="auth/logout">logout</a>
			<button id="write">글쓰기</button>
		</c:if>
		
	</header>
	
	<section>
		<c:forEach var="attach" items="${list}">
			<div class="left">
				<p><img src="displayFile?filename=${attach.fullName }" data-src="${attach.fullName }" data-bno="${attach.bno }"></p>
				<p>${attach.userid }</p>
				<p>${attach.orgName }</p>
				<p><fmt:formatDate value="${attach.regdate }" pattern="yyyy-MM-dd"/></p>
			</div>
		</c:forEach>
		
		<div class="box-footer">
			<div class="text-center">
				<ul	class="pagination">
					<c:if test="${pageMaker.prev }">
						<li><a href="list?page=${pageMaker.startPage-1 }">&laquo;</a></li>
					</c:if>
					<c:forEach var="idx" begin="${pageMaker.startPage }" end="${pageMaker.endPage }">
						<li ${pageMaker.cri.page==idx ? 'class="active"' : ''}><a href="list?page=${idx}">${idx}</a></li>
					</c:forEach>
					<c:if test="${pageMaker.next}">
						<li><a href="list?page=${pageMaker.endPage+1 }">&raquo;</a></li>
					</c:if>
				</ul>
			</div>
		</div>
		
		
	</section>
	
	<div id="toggle">
		<form id="f1" action="outUploadForm2" method="post" enctype="multipart/form-data">
			<input type="text" name="userid" value="${Auth.userid }" readonly="readonly">
			<input type="file" name="files" id="file" multiple="multiple">
			<input type="submit">
		</form>
		
		<div id="box">
			
		</div>
		
	</div>
	
	
	<div id="imgToggle">
		
	</div>
</div>


	<script type="text/javascript">
	var formData = new FormData();//서버로 보낼 데이터를 담을 공간
			
		$("#file").change(function(){
			console.log($(this))
			$($(this)[0].files).each(function(i,obj){
				var reader = new FileReader();
				reader.onload = function(e){
					var $img = $("<img>").attr("src",e.target.result)
					$("#box").append($img);
					formData.append("files",obj);	
				}
				reader.readAsDataURL(obj);
			});
		})
		
		$("#write").click(function(){
			$("#toggle").toggle();
			var display=$("#toggle").css("display");
			if(display=='block'){
				$("body").css("background-color","rgba(0,0,0,0.5)");
			}
			else{
				$("body").css("background-color","rgba(255,255,255,1)");
			}
		})
		
		
		
		
		$("#f1").submit(function(e){
			e.preventDefault(); //ajax로 처리하므로, submit 안되게 막음
			formData.append("userid",$("input[name='userid']").val());
			$.ajax({
				url:"outUploadForm2",
				type:"post",
				data:formData,
				processData:false, //FormData 를 보낼 경우 processData:false, contentType:false처리 필요
				contentType:false,
				success:function(res){
					console.log(res);
					
					formData = new FormData();
					
					$("#box").empty();
					$("#write").click();
					/* $("#dropBox").empty();
					$(res).each(function(i,obj){
						var $div = $("<div>");
						var $img = $("<img>").attr("src","displayFile?filename="+obj);
						var $button = $("<button>").text("x").attr("data-src",obj);
						
						$div.append($img).append($button);
						$("#dropBox").append($div);
					}) */
					location.href="list?page=${pageMaker.cri.page}";
					
					
				}
			})
		})
		
		$(document).on("click","section img",function(){
			var imgsrc=$(this).attr("data-src");
			var imgbno=$(this).attr("data-bno");
			$("#imgToggle").show();
			$("body").css("background-color","rgba(0,0,0,0.5)");
			var pre=imgsrc.slice(0, 12);
			var post=imgsrc.slice(14);
			
			var $img = $("<img>").attr("src","displayFile?filename="+pre+post).attr("data-src",pre+post).attr("data-bno",imgbno);
			var $x = $("<span>").text("X");
			var $btn = $("<button>").attr("id","delete").text("삭제");
			$("#imgToggle").html($img).append($x).append($btn);
		})
		
		$(document).on("click","#delete",function(){
			var check=confirm("삭제하시겠습니까?");
			
			if(check==false) return;
			
			var src=$(this).prev().prev().attr("data-src");
			var bno=$(this).prev().prev().attr("data-bno");
			
			location.href="delteImg?imgFile="+src+"&bno="+bno+"&page=${pageMaker.startPage}";
		})
		
		
		
		$(document).on("click","#imgToggle span",function(){
			$("#imgToggle").hide();
			$("body").css("background-color","rgba(255,255,255,1)");
		})
	</script>
</body>
</html>