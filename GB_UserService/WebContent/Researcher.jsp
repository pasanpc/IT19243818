<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
     <%@page import = "model.User" %>
      
     
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Researcher handling</title>


<link rel = "stylesheet" href = "Views/bootstrap.min.css">
<script src = "Components/jquery-3.6.0.min.js"></script>
<script src = "Components/Researchers.js"></script>



</head>
<body>

<div class = "container"> 
	<div class="row">
		<div class="col">

		<h1>Researchers Management</h1>
		
	<form id="formItem" name="formItem"  >
		User Name:
		<input id="userName" name="userName" type="text" class="form-control form-control-sm"><br>
		Email:
		<input id="email" name="email" type="text" class="form-control form-control-sm"><br> 
		First Name:
		<input id="firstName" name="firstName" type="text" class="form-control form-control-sm"><br>
		Last Name:
		<input id="lastName" name="lastName" type="text" class="form-control form-control-sm"><br>
		Card Number:
		<input id="cardNumber" name="cardNumber" type="text" class="form-control form-control-sm"><br>
		cvv:
		<input id="cvv" name="cvv" type="text" class="form-control form-control-sm"><br>
		Exp Date:
		<input id="expDate" name="expDate" type="text" class="form-control form-control-sm"><br> 
		Password:
		<input id="password" name="password" type="text" class="form-control form-control-sm"><br>
		<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary">
		<input type="hidden" id="hidItemIDSave" name="hidItemIDSave" value="">
	</form>
    
    <div id="alertSuccess" class="alert alert-success"></div>
     <div id="alertError" class="alert alert-danger"></div>
    
    <br>
	<div id="divItemsGrid">
	<%
	User itemObj = new User();
	out.print(itemObj.readResearchers());
	%>
	</div>

<br>


</div>
</div>
</div>


</body>
</html>