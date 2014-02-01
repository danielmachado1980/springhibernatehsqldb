<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/css/bootstrap.min.css"/>">
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/css/bootstrap-responsive.min.css"/>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Employee List</title>
</head>
<body>
	<h1>Hello World!</h1>
	<fieldset><legend>Resultados</legend>
	<br>
	<table class="table table-hover">
			<thead>
			<tr>
			<th>#</th>
			<th>Nome</th>
			<th>Endereço</th>
			</tr>
			</thead>
			<tbody>
			<c:forEach items="${employee}" var="employee">
				<tr>
					<td><c:out value="${employee.empid}"></c:out></td>
					<td><c:out value="${employee.empname}"></c:out></td>
					<td><c:out value="${employee.address}"></c:out></td>
				</tr>
			</c:forEach>
			</tbody>
	</table>
	</fieldset>
	<div class="right">${serverTime}</div>
</body>
</html>