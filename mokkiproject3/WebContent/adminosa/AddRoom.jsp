<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Mökin lisääminen</title>
</head>
<a href="VuokraControl.jsp">Takaisin</a>
<body>
  
  <form action="AdminAddRoomServlet" method="post">
          	<label for="roomamount">Huoneiden määrä:</label>
            <input type="text" class="form-control" id="roomamount" name="roomamount"> <br>
            <label for="location">Mökin sijainti:</label>
            <input type="text" class="form-control" id="location" name="location" ><br>
            <label for="priceperday">Mökin hinta per päivä:</label>
            <input type="text" class="form-control" id="priceperday" name="priceperday"><br>
			<p>Kaikki tiedot tarvii täyttää</p>
        <button type="submit" class="btn btn-default">Lisää mökki</button>
		
    </form>
</body>
</html>