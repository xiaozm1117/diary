<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<script src="/jquery/dist/jquery.min.js"></script>
<script src="/jquery-validation-1.19.2/dist/jquery.validate.js"></script>

</head>

<body> 
	
	<form id="formRegister" action="/service/user/doRegister" onsubmit="return ischeckname">
		<p>用户名：</p><input type="text" name="username" onblur="checkname()" placeholder="请输入3-10个以字母开头的字符串"/></p>
		<p>用户密码：</p><input type="text" name="password" placeholder="请输入3-7个以字母开头的字母与数字组合字符串"/></p>
		<p></P><input type="submit" value="注册"/></p>
	</form>
	
	<script type="text/javascript">
		var ischeckname=false;
	    
		function checkname(){
			ischeckname=false;
			$.post("/service/user/doCheckname",$("#formRegister").serialize(),function(data){
				alert("check uname");
				if(data.message=="ok"){$("input[name='username']").val("");}
				ischeckname=true;})}
		
		$.validator.addMethod("username", function(value, element) { 
			var tel =  /^[a-z][\w]{3,10}$/;
			var a=this.optional(element) || (tel.test(value));
			
			return a;}, "请输入3-10个以字母开头的字符串"); 
		$.validator.addMethod("password", function(value, element) { 
			var tel =  /^[a-z][a-z0-9]{0,}[0-9][0-9a-z]{1,5}$/;
			return this.optional(element) || (tel.test(value)); 
			}, "请输入3-7个以字母开头的字母与数字组合字符串");
		
			(function(){
				 $("#formRegister").validate({
		                rules: {
		                username:{required:true,username:true},
		                password:{required:true,password:true}
		                },
		                messages: {
		                username:{ required:"请输入3-10个以字母开头的字符串"},
		                password:{ required:"请输入3-7个以字母开头的字母与数字组合字符串"}}});})()
		
	</script>
	
	
</body>
	
</html>