<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<!-- Bootstrap 3.3.4 -->
	<link href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
	<!-- Font Awesome Icons -->
	<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
	<!-- Ionicons -->
	<link href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css" rel="stylesheet" type="text/css" />
	<!-- Theme style -->
	<link href="${pageContext.request.contextPath}/resources/dist/css/AdminLTE.min.css" rel="stylesheet" type="text/css" />
	<!-- AdminLTE Skins. Choose a skin from the css/skins 
	     folder instead of downloading all of them to reduce the load. -->
	<link href="${pageContext.request.contextPath}/resources/dist/css/skins/_all-skins.min.css" rel="stylesheet" type="text/css" /> 
	
	<!-- jQuery 2.1.4 -->
    <script src="${pageContext.request.contextPath}/resources/plugins/jQuery/jQuery-2.1.4.min.js"></script> 
    
    <!-- Bootstrap 3.3.2 JS -->  
    <script src="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
</head>
<body class="login-page">
	<div class="login-box">
		<div class="login-logo">
			<a href="#"><b>K-HRD</b> Project</a>
		</div>
		<div class="login-box-body">
			<p class="login-box-msg">Congratulations! You FAILED to Login!!</p>
			<p class="login-box-msg"><a href="login">retry</a></p>
		</div>
	</div>
</body>
</html>