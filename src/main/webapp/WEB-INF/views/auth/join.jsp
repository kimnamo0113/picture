<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
 <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>SB Admin 2 - Register</title>

  <!-- Custom fonts for this template-->
  <link href="${pageContext.request.contextPath }/resources/bootTemplate/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
  <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

  <!-- Custom styles for this template-->
  <link href="${pageContext.request.contextPath }/resources/bootTemplate/css/sb-admin-2.min.css" rel="stylesheet">
  
<style>
	.regExpMsg{
		color:red;
		display: none;
	}
	.repeatMsg{
		color:red;
		display: none;
	}
	
	.redBorder{
		border:2px solid red !important;
	}
	.noRepeat{
		color:red;
		display: none;
	}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script>

$(function() {
    $('#name').focus();
	
	$('input').blur(function(){
		
		
	    if($(this).attr('id') == 'email'){
    	   let strEmail = $('#email').val() ;
    	   if(strEmail=='')
    		   return;
           let regExp = /^[0-9a-zA-Z]*@[_0-9a-zA-Z-]+(\.[_0-9a-zA-Z-]+){1,2}$/;
           
           
           if (!strEmail.match(regExp)) {
        	   $("#email").next().show();
        	   $("#email").next().next().hide();
        	   $('#email').addClass("redBorder");
               return false
           }else{
        	   $("#email").next().hide();
        	   $("#email").next().next().hide();
        	   $('#email').removeClass("redBorder");
           }
           $.post('join/email',{email:strEmail},function(result){
        	   if(result) {
            	   $("#email").next().next().show();
            	   $('#email').addClass("redBorder");
                   return false 
               }else{
            	   $("#email").next().next().hide();
            	   $('#email').removeClass("redBorder");
            	   return false
               }
           })
	    }
	    
	    if($(this).attr('id') == 'id'){
    	   let strId = $('#id').val();
    	   if(strId=='')
    		   return;
    	   
           let regExp = /^[a-z0-9~!@#$%^&*()-_=+]{4,15}$/i;
           if (!strId.match(regExp)) {
        	   $('#id').addClass("redBorder");
        	   $("#id").next().next().hide();
        	   $("#id").next().show();
               return false
           }else{
        	   $("#id").next().hide();
        	   $("#id").next().next().hide();
        	   $('#id').removeClass("redBorder");
           }
           $.post('join/id',{userid:strId},function(result){
               if(result) {
            	   $("#id").next().next().show();
            	   $('#id').addClass("redBorder");
                   return false 
               }else{
            	   $("#id").next().next().hide();
            	   $('#id').removeClass("redBorder");
            	   return false
               }
           })
	    }
	    
	    if($(this).attr('id') == 'password1'){
    	   let strpassword1 = $('#password1').val() ;
    	   
    	   if(strpassword1=='')
    		   return;
    	   
           let regExp = /^[a-z0-9~!@#$%^&*()-_=+]{4,15}$/i;
           
           if (!strpassword1.match(regExp)) {
        	   $('#password1').addClass("redBorder");
        	   $("#password1").next().show();
               return false
           }else{
        	   $("#password1").next().hide();
        	   $('#password1').removeClass("redBorder");
           }
	    }
	    if($(this).attr('id') == 'password2'){
    	   let strpassword2 = $('#password2').val() ;
    	   
    	   if(strpassword2=='')
    		   return;
    	   
           let regExp = /^[a-z0-9~!@#$%^&*()-_=+]{4,15}$/i;
           
           if (!strpassword2.match(regExp)) {
        	   $('#password2').addClass("redBorder");
        	   $("#password2").next().show();
               return false
           }else{
        	   $("#password2").next().hide();
        	   $('#password2').removeClass("redBorder");
           }
	    }
	    
	    if($(this).attr('id') == 'password2' || $(this).attr('id') == 'password1'){
	    	
	    	var pass1=$("#password1").val();
	    	var pass2=$("#password2").val();
	    	
	    	if(pass1!=''&&pass2!=''){
		    	if(pass1!=pass2){
		    		$(".noRepeat").show();
		    	}else{
		    		$(".noRepeat").hide();
		    	}
	    	}
	    }
	    
	    if($(this).attr('id') == 'name'){
    	   let strname = $('#name').val() ;
    	   
    	   if(strname=='')
    		   return;
    	   
           let regExp = /^[a-z0-9가-힣]{2,15}$/i;
           
           if (!strname.match(regExp)) {
        	   $('#name').addClass("redBorder");
        	   $("#name").next().show();
               return false
           }else{
        	   $("#name").next().hide();
        	   $('#name').removeClass("redBorder");
           }
	    }
    	
	  })
	  
	$("#f1").submit(function() {
		var check=true;
		$("#f1 input").each(function(i,obj) {
			if($(obj).val()==''){
				
				$(this).next().show();
				alert($(this).attr("placeholder")+"를 입력해 주세요.")
				$(this).focus();
				check=false;
				return false;
			}
		})
		
		if(check==false)
			return false;

		if($("input").is(".redBorder")==true){
			
			alert("형식을 제대로 입력해주세요.");
			return false;
		};

		return true;

	})
	  
})
</script>


</head>

<body class="bg-gradient-primary">

  <div class="container">

    <div class="card o-hidden border-0 shadow-lg my-5">
      <div class="card-body p-0">
        <!-- Nested Row within Card Body -->
        <div class="row">
          <div class="col-lg-5 d-none d-lg-block bg-register-image"></div>
          <div class="col-lg-7">
            <div class="p-5">
              <div class="text-center">
                <h1 class="h4 text-gray-900 mb-4">Create an Account!</h1>
              </div>
              <form class="user" id="f1" action="join" method="POST">
                <div class="form-group">
                    <input type="text" class="form-control form-control-user" name="username" id="name" placeholder="Name" autocomplete=”off”>
                    <p class="regExpMsg">형식이 올바르지 않습니다.(2~15)</p>
                </div>
                <div class="form-group">
                    <input type="email" class="form-control form-control-user" name="email" id="email" placeholder="Email" autocomplete=”off”>
                    <p class="regExpMsg">형식이 올바르지 않습니다.(abcd@abcd.com)</p>
                    <p class="repeatMsg">중복된 Email입니다.</p>
                </div>
                
                <div class="form-group">
                  <input type="id" class="form-control form-control-user" name="userid" id="id" placeholder="Id" autocomplete=”off”>
                  <p class="regExpMsg">형식이 올바르지 않습니다.(4~15)</p>
                   <p class="repeatMsg">중복된 Id입니다.</p>
                </div>
                <div class="form-group">
                  <input type="id" class="form-control form-control-user" name="userphone" id="phone" placeholder="phone" autocomplete=”off”>
                  <p class="regExpMsg">형식이 올바르지 않습니다.(ㅇㅅㅇ)</p>
                  
                </div>
                <div class="form-group row">
                  <div class="col-sm-6 mb-3 mb-sm-0">
                    <input type="password" class="form-control form-control-user" name="userpw" id="password1" placeholder="Password" autocomplete=”off”>
                    <p class="regExpMsg">형식이 올바르지 않습니다.(4~15)</p>
                  </div>
                  <div class="col-sm-6">
                    <input type="password" class="form-control form-control-user" id="password2" placeholder="Repeat Password" autocomplete=”off”>
                    <p class="regExpMsg">형식이 올바르지 않습니다.(4~15)</p>
                  </div>
                </div>
                <p class="noRepeat">비밀번호가 일치하지 않습니다.</p> 
                <button type="submit" value="Next" class="btn btn-primary btn-user btn-block">Next</button>
                
                <hr>
                <a href="index.html" class="btn btn-google btn-user btn-block">
                  <i class="fab fa-google fa-fw"></i> Register with Google
                </a>
                <a href="index.html" class="btn btn-facebook btn-user btn-block">
                  <i class="fab fa-facebook-f fa-fw"></i> Register with Facebook
                </a>
              </form>
              <hr>
              <div class="text-center">
                <a class="small" href="forgotPassWord">Forgot Password?</a>
              </div>
              <div class="text-center">
                <a class="small" href="${pageContext.request.contextPath }/auth/login">Already have an account? Login!</a>
              </div>
              <div class="text-center">
                <a class="small" href="${pageContext.request.contextPath }">Go to Main!</a>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>

  </div>

  <!-- Bootstrap core JavaScript-->
  <script src="${pageContext.request.contextPath }/resources/bootTemplate/vendor/jquery/jquery.min.js"></script>
  <script src="${pageContext.request.contextPath }/resources/bootTemplate/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

  <!-- Core plugin JavaScript-->
  <script src="${pageContext.request.contextPath }/resources/bootTemplate/vendor/jquery-easing/jquery.easing.min.js"></script>

  <!-- Custom scripts for all pages-->
  <script src="${pageContext.request.contextPath }/resources/bootTemplate/js/sb-admin-2.min.js"></script>

</body>

</html>

