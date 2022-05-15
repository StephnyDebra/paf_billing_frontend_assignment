<%@page import="com.Item"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Items Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.6.0.min.js"></script>
<script src="Components/items.js"></script>
</head>
<body> 
<div class="container"><div class="row"><div class="col-6"> 
<h1>Billing Management V10.1</h1>
<form id="formItem" name="formItem" method="post" action="items.jsp">
 Account No: 
 <input id="AccountNo" name="AccountNo" type="text" 
 class="form-control form-control-sm">
 <br> Month: 
 <input id="Month" name="Month" type="text" 
 class="form-control form-control-sm">
 <br> Unit Consumed: 
 <input id="UnitConsumed" name="UnitConsumed" type="text" 
 class="form-control form-control-sm">
 <br> Unit Price: 
 <input id="UnitPrice" name="UnitPrice" type="text" 
 class="form-control form-control-sm">
 <br> TotalAmount: 
 <input id="TotalAmount" name="TotalAmount" type="text" 
 class="form-control form-control-sm">
 <br>
 <input id="btnSave" name="btnSave" type="button" value="Save" 
 class="btn btn-primary">
 <input type="hidden" id="hidItemIDSave" 
 name="hidItemIDSave" value="">
</form>
<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>
<br>
<div id="divItemsGrid">
 <%
 Item itemObj = new Item(); 
 out.print(itemObj.readItems()); 
 %>
</div>
</div> </div> </div> 
</body>
</html>