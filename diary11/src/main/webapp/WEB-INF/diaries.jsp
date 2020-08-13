<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">


<html>
<head>
<script src="/jquery/dist/jquery.min.js"></script>
</head>
<body>
	<textarea rows="20" cols="20" id="text" style="resize:none;" readonly="readonly"></textarea>
	<div id="index">
		
		
	</div>
	
	<button onclick="back()">返回</button>
</body>
<script type="text/javascript">
		
  $(function(){
		 $("textarea[id='text']").val("${requestScope.content}");
		 
		 doIndex();
		 
		 
		 
  })
  
  
  function back(){
	  location.href="/diary/home.html";
	  
	  
  }
  
  function doIndex(){
	  var pag=$("#index");
		var a=${requestScope.currentPage};
		var total=${requestScope.total};
		
		//前段
		
			if(total>5&&a!=1){
			var b11="<a href=/service/diary/load?page=1>1</a>";
			pag.append(b11);}
			if(total>6&&a!=2&&a!=1){
				var c11="<a href=/service/diary/load?page="+(a-1)+">..</a>";
				pag.append(c11);}
		
		//中间段
		
          if(total-a>=5){		
		  for(let i=0;i<5;i++){
			  var d11="<a href=/service/diary/load?page="+(a+i)+">"+(a+i)+"</a>";
			  pag.append(d11);}}else if(total>=5){
				  for(let j=4;j>=0;j--){
					  var d12="<a href=/service/diary/load?page="+(total-j)+">"+(total-j)+"</a>";
					  pag.append(d12);}}else {
						  for(let a=total-1;a>=0;a--){
							  var d13="<a href=/service/diary/load?page="+(total-a)+">"+(total-a)+"</a>";
							  pag.append(d13);}} 
		
		//后段
		 if(total-a==5){
			e11="<a href=/service/diary/load?page="+total+">"+total+"</a>";
			pag.append(e11);}
				
		if (total-a>5) {
				f11="<a href=/service/diary/load?page="+(a+5)+">..</a>";
				pag.append(f11);
				g11="<a href=/service/diary/load?page="+total+">"+total+"</a>";
				pag.append(g11);}
	  
  	}
  
</script>
	
</html>
