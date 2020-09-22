<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ page session= "true" %>

<!DOCTYPE html>
<html>
<link href="style.css" rel="stylesheet" type="text/css">
<head>


<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="ISO-8859-1">
<c:import url="header.html" charEncoding="UTF-8"></c:import>

<title>Mökin vuokraus</title>


</head>

<body>
Welcome<input type="text" value="${user }" readonly style="border:none"></input>

<form action="VuokrausServlet" method="post">
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
<input type="submit" value="Etsi huoneet">
</form>

<br><br><br>
	<c:if test="${not empty ilmoitus }">
	<p> ${ilmoitus }</p>
	</c:if>
	<c:if test="${not empty TakenTimes }">
	<h1>Varatut ajat</h1>
	<table>
	<tr>
	<th>Aloitus päivä</th><th>Lopetus päivä</th>
	</tr>
	<c:set var="counter" value="0" scope="page"/>
	<c:forEach var="dates" step="2" varStatus="loop" items="${TakenTimes }">
	<tr><td>
	<c:out value="${dates }"></c:out>
	</td><td>
	<c:out value="${TakenTimes.get(counter+1) }"></c:out>
	<c:set var="counter" value="${counter+2 }" scope="page"/>
	</td></tr>
	</c:forEach>


	</table>
	</c:if>
	<form action="MokkiValintaServlet" method="post">

    <c:if test="${not empty searchedrooms }">
    <h1> Löydetyt mökit</h1>
    <table>
    <tr>
    <th>Huoneiden määrä</th><th>Mökin sijainti</th><th>Mökin hinta (euroa per yö)</th>
    </tr>
	<c:forEach var="mokki" items="${searchedrooms }">
	<tr><td>
	<input type="radio" value="${mokki.mokkiID }" name="roomselection" id="roomselection">
	<c:out value="${mokki.mokkiRoomAmount } " ></c:out> 
	</td><td>
	<c:out value="${mokki.mokkiLocation } "></c:out> 
	</td>
	<td>
	<c:out value="${mokki.mokkiPricePerNight } "></c:out>
	</td>
	</tr>
	</c:forEach>
	</table>
  	<p>Starting date: <input type="date" name="startdate">(YYYY-MM)</p> 
	<p>Ending date:   <input type="date" name="enddate">(YYYY-MM)</p>
	<button type= "submit" value="CheckAvailability" name="CheckAvailability">Katso saatavuus</button> <button type="submit" value="RentRoom" name="RentRoom">Vuokraa</button>
	
    </c:if>

	</form>


</body>
</html>