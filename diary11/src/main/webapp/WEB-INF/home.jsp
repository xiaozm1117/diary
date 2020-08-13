<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
<head>
<script src="/jquery/dist/jquery.min.js"></script>
<style type="text/css">
	.tt {
		
		
		resize: none
	}
</style>
</head>
<body>
<h2>Hello World!</h2>
	
     <textarea rows="20" cols="20" id="comment" class="tt" placeholder="这里bai输入文字"></textarea>
		
	 <button onclick="read()">翻看日记</button>
	 <button onclick="submit()">提交</button>
	 
	
<script type=text/javascript>
	function submit(){
		$.post("/service/diary/submit",{content:($("textarea[id='comment']").val())},function(data){
			$("textarea[id='comment']").val("");
			if(data.message="ok"){
			alert("homesubmit---success!");}else{
				alert("homesubmit---fail!")
			} 
		})
		
	}
	function read(){
		return window.location.href="/diary/load.html?page=1";
	}
</script>
</body>
</html>
