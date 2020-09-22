<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<link href="../style.css" rel="stylesheet" type="text/css">
<head>


<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="ISO-8859-1">

<title>Mökin vuokraus</title>
</head>
<body>
<a href="AddRoom.jsp">Lisää mökki</a>
${pageContext.request.remoteUser}
<form action="AdminLogoutServlet" method="post">
<button type="submit">Logout</button>
</form>

<form action="AdminVuokrausServlet" method="post">
<label for="rooms">Huoneet</label>
<div class="slidercontainer">
<input type="range" min="1" max="15" id="rooms" name="rooms" value="3" class="slider" onchange="slidervalue()"> <p><span id="asd">3</span> huonetta</p>
<script>
function slidervalue() {
var slider = document.getElementById("rooms");
var current = document.getElementById("asd");
current.innerHTML = slider.value;
slider.oninput = function() {
	  output.innerHTML = this.value;
	}
}
</script>
</div>
<input type="submit">
</form>

<br><br><br>
	<c:if test="${not empty ilmoitus }">
	<p> ${ilmoitus }</p>
	</c:if>
	<form action="AdminMokkiValintaServlet" method="post">
    <c:if test="${not empty searchedrooms }">
    <h1> Löydetyt vuokraukset</h1>
    <table>
    <tr>
    <th>Huoneiden määrä</th><th>Mökin sijainti</th><th>Mökin hinta (euroa per yö)</th><th>Kokonaishinta</th><th>Vuokraaja</th><th>Alkaa</th><th>Loppuu</th>
    </tr>
	<c:forEach var="mokki" items="${searchedrooms }">
	<tr><td>
	<input type="checkbox" value="${mokki.mokkiRentID }" name="roomRentID" id="roomRentID">
	<c:out value="${mokki.mokkiRoomAmount } " ></c:out> 
	</td><td>
	<c:out value="${mokki.mokkiLocation } "></c:out> 
	</td>
	<td>
	<c:out value="${mokki.mokkiPricePerNight } "></c:out>
	</td>
	<td>
	<c:out value="${mokki.mokkiTotalPrice}"></c:out>
	</td>
	<td>
	<c:out value ="${mokki.mokkiRenter }"></c:out>
	</td>
	<td>
	<c:out value ="${mokki.mokkiRentDate }"></c:out>
	</td>
	<td>
	<c:out value="${mokki.mokkiRentUntil }"></c:out>
	</td>
	</tr>
	</c:forEach>
	</table>
	<button type="submit" name="RemoveReservation" value="RemoveReservation" >Vapauta huoneet</button><button type="submit" name="GetUserInfo" value="GetUserInfo">Hae valittujen käyttäjien tiedot</button>
	</c:if>
	</form>

	<c:if test="${not empty AccountInfo }">
	<h1> Löydetyt tiedot</h1>
	<div class="infotable">
	<table>
	<tr>
	<th>KäyttäjäID</th><th> Username</th><th>Sähköposti</th><th> Osoite </th><th> Etunimi</th><th> Sukunimi</th>
	<c:forEach var="FoundInfo" items="${AccountInfo }">
	<tr><td>
	<c:out value="${FoundInfo.accountID } " ></c:out> 
	</td><td>
	<c:out value="${FoundInfo.accountName } "></c:out> 
	</td>
	<td>
	<c:out value="${FoundInfo.accountEmail } "></c:out>
	</td>
	<td>
	<c:out value="${FoundInfo.address }"></c:out>
	</td>
	<td>
	<c:out value="${FoundInfo.firstName }"></c:out>
	</td>
	<td>
	<c:out value="${FoundInfo.lastName }"></c:out>
	</td>
	</tr>
	</c:forEach>
	</table>
	</div>
    </c:if>



</body>
</html>