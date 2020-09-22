package mokkiproject3;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder; 

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Cookie;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;
/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
	@Resource(name="jdbc/SchoolDB")
	private DataSource ds;
	BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); 
	
	
	private static final long serialVersionUID = 1L;
	 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        request.setCharacterEncoding("UTF-8");
	        String accountname = request.getParameter("accname");
	        String password1 = request.getParameter("accpw");
	        String ilmoitus = "";
	        try {
			 	if (passwordEncoder.matches(password1, tryLogin(accountname))) {
			 		HttpSession session = request.getSession();
			 		session.setAttribute("user", accountname);
			 		session.setAttribute("login", "true");
			 		session.setMaxInactiveInterval(30*60);
			 		Cookie userName= new Cookie("user", accountname);
			 		userName.setMaxAge(30*60);
			 		response.addCookie(userName);
			 		response.sendRedirect("vuokraus.jsp");
	            }
			 	else if(!passwordEncoder.matches(password1, tryLogin(accountname))) {
			 		ilmoitus="Wrong username or password";
			        request.setAttribute("ilmoitus", ilmoitus);
			 		RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
			        rd.forward(request, response);
			 		
			 		
			 	}
	        } catch (SQLException e) {
	            request.setAttribute("virhe", "Ongelmia. Yritä myöhemmin uudelleen.");
	            e.printStackTrace();
	        }

	    }
	        
	 
	 public String tryLogin (String accname) throws SQLException {
			String haettu= null;
			String sqlLause= "SELECT AccountPassword FROM Accounts WHERE AccountName=?";
			try (Connection conn = ds.getConnection()) {
				try(PreparedStatement pstm = conn.prepareStatement(sqlLause)){
					pstm.setString(1,accname);
					try(ResultSet rs = pstm.executeQuery()){
						if(rs.next()) {
							haettu= rs.getString(1);
							}
						}
					}
				}
			return haettu;
			}
}