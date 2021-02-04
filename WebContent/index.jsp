<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en" ng-app="app">
<head>
<meta charset="ISO-8859-1">
<title>Online Food Order</title>
<script>
	var contexturl = '<%=request.getContextPath()%>';
	contexturl += "/";
	console.log("contexturl = "+ contexturl);
</script>

	<!-- incuding every js file that are used in the web application -->
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.8.2/angular.min.js" ></script>
	<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.8.2/angular-messages.js" ></script>
	<script src="https://unpkg.com/@uirouter/angularjs/release/angular-ui-router.min.js"></script>
	
	<script src="<%=request.getContextPath()%>/shared/common.module.js"></script>
	<script src="<%=request.getContextPath()%>/shared/common.service.js"></script>
	
	<script src="<%=request.getContextPath()%>/pages/Admin/Admin.module.js"></script>
	<script src="<%=request.getContextPath()%>/pages/Admin/Admin.controller.js"></script>
	
	<script src="<%=request.getContextPath()%>/pages/app.module.js"></script>
    <script src="<%=request.getContextPath()%>/pages/app.config.js"></script>
    
    <script src="<%=request.getContextPath()%>/pages/Customer/Customer.module.js"></script>
    <script src="<%=request.getContextPath()%>/pages/Customer/Customer.controller.js"></script>
    <script src="<%=request.getContextPath()%>/pages/Customer/Customer.service.js"></script>
    
    <script src="<%=request.getContextPath()%>/pages/Order/Order.module.js"></script>
    <script src="<%=request.getContextPath()%>/pages/Order/Order.controller.js"></script>
    <script src="<%=request.getContextPath()%>/pages/Order/Order.service.js"></script>
    
    <script src="<%=request.getContextPath()%>/pages/Payment/Payment.module.js"></script>
    <script src="<%=request.getContextPath()%>/pages/Payment/Payment.controller.js"></script>
    <script src="<%=request.getContextPath()%>/pages/Payment/Payment.service.js"></script>
    
    <script src="<%=request.getContextPath()%>/pages/Staff/Staff.module.js"></script>
    <script src="<%=request.getContextPath()%>/pages/Staff/Staff.controller.js"></script>
    <script src="<%=request.getContextPath()%>/pages/Staff/Staff.service.js"></script>
</head>
<body>
	<!-- this div is the responsible for the UI available on the screen -->
	<div class="container-fluid" ui-view autoscroll="true"></div>
</body>
</html>