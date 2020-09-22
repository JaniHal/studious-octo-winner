<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page session= "true" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Login</title>
</head>
<body>
<%
boolean logged = session.getAttribute("login") != null;
if (logged==true)
	response.sendRedirect("vuokraus.jsp");


%>

<c:import url="header.html" charEncoding="UTF-8"></c:import>
  <form action="Login" method="post">
        <div class="form-group">
            <label for="accname">Nimi:</label>
            <input type="text" class="form-control" id="accname" name="accname" value="${param.accname}">
        </div>
        <div class="form-group">
            <label for="accpw">Salasana:</label>
            <input type="password" class="form-control" id="accpw" name="accpw" value="">
        </div>
        <button type="submit" class="btn btn-default">Login</button>
        </form>
 	    <c:if test="${not empty ilmoitus }">
        <p class="text-danger">${ilmoitus }</p>
    </c:if> 
</body>
</html>