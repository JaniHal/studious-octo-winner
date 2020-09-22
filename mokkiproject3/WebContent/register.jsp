<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Tilin Luonti</title>
<c:import url="header.html" charEncoding="UTF-8"></c:import>

</head>
 	    <c:if test="${not empty ilmoitus }">
        <p>${ilmoitus }</p>
    </c:if> 
<body>
Tilin tiedot:

<form action="RegisterationServlet" method="post">
          	<label for="accname">Käyttäjänimi*:</label>
            <input type="text" class="form-control" id="accname" name="accname" value="${param.accname}"> <br>
            <label for="email">Email:</label>
            <input type="text" class="form-control" id="email" name="email" value="${param.email}"><br>
            <label for="email">Etunimi*:</label>
            <input type="text" class="form-control" id="firstname" name="firstname" value="${param.firstname}"><br>
            <label for="email">Sukunmi*:</label>
            <input type="text" class="form-control" id="lastname" name="lastname" value="${param.lastname}"><br>
            <label for="email">Osoite*:</label>
            <input type="text" class="form-control" id="address" name="address" value="${param.address}"><br>
            <label for="accpw">Salasana*:</label>
            <input type="password" class="form-control" id="accpw" name="accpw" value=""><br>
            <label for="accpw2">Salasana uudestaan*:</label>
            <input type="password" class="form-control" id="accpw2" name="accpw2" value=""><br>
			<p>*Tähdellä merkityt pitää antaa</p>
        <button type="submit" class="btn btn-default">Luo</button>
</form>
</body>
</html>