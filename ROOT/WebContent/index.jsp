<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>


<head>
<%    
String path = request.getContextPath();    
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";    
%>    
<base href="<%=basePath%>"/>   
<script src="js/jquery-1.10.2.min.js" type="text/javascript"></script>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<div id='test'> in index text</div>
</body>
<script>
$('#test').click(function(){
	$.ajax({
	    url: '/ConnectToWeixin',
		type : 'post',
		dataType : 'html',
		data : {
			"type":""
		},
		error : function(XMLHttpRequest, textStatus, errorThrow) {
			console.log('Error occurs at server.' +
	                XMLHttpRequest.responseText);
			console.log("textStatus:"+textStatus);
			console.log('errorThrow'+errorThrow);
		},

		success : function(data) {
			console.log("upload data sucess");
			$('#test')[0].innerHTML = data
			console.log('>>>>>>>>>>>>>>>>'+ data);
		}
	});
});

</script>
</html>