package mokkiproject3;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import javax.sql.DataSource;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Servlet implementation class RegisterationServlet
 */
@WebServlet("/RegisterationServlet")
public class RegisterationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Resource(name="jdbc/SchoolDB")
	private DataSource ds;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String accountname = request.getParameter("accname");
        String accountemail = request.getParameter("email");
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String address = request.getParameter("address");
        String password1 = request.getParameter("accpw");
        String password2 = request.getParameter("accpw2");
        String ilmoitus = "";
        
        if (accountname.length()>32 || accountemail.length()>32 || password1.length()>32 || address.length()>50 || firstname.length()>50 || lastname.length()>50) {
        	ilmoitus="Tiedot ovat liian pitkiä (max 32 merkkiä)";
        }
        else if (accountname.isEmpty() || password1.isEmpty() || address.isEmpty() || firstname.isEmpty() ||lastname.isEmpty()) {
            ilmoitus = "Kaikki tiedot pitää antaa!";
        }
        else if (!password1.equals(password2)) {
        	ilmoitus = "Salasanoinen täytyy olla samat";
        }
        else if (password1.equals(password2) && !firstname.isEmpty() && !lastname.isEmpty() && !address.isEmpty()&& !password1.isEmpty() && !password2.isEmpty() ) {
        	BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        	String salattu = passwordEncoder.encode(password1);
            try {		
                
                accounts uusi = new accounts(accountname, accountemail, salattu,address, firstname,lastname);
                newAccount(uusi);
                ilmoitus="Account created";
                request.setAttribute("ilmoitus", ilmoitus);
                RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
                rd.forward(request, response);
            }
            catch(SQLException ex2) {
            	 if (ex2 instanceof SQLIntegrityConstraintViolationException) 
            		 ilmoitus="Käyttäjänimi on jo olemassa";
            	 else {
                ilmoitus = "Lisäys ei onnistunut. Yritä myöhemmin uudelleen.";
            	 }
            }

        }
        request.setAttribute("ilmoitus", ilmoitus);
        RequestDispatcher rd = request.getRequestDispatcher("register.jsp");
        rd.forward(request, response);

 }
        
 
	 public void newAccount(accounts uusi) throws SQLException {
	        String sqlLause = "INSERT INTO Accounts(AccountName, AccountEmail, AccountPassword, AccountFirstName, AccountLastName, AccountAddress) VALUES(?, ?, ?, ?, ?, ?)";
	        try (Connection conn = ds.getConnection()){
	            	conn.setAutoCommit(false);
	            	PreparedStatement pstm = conn.prepareStatement(sqlLause);
	                pstm.setString(1,  uusi.getAccountName());
	                pstm.setString(2, uusi.getAccountEmail());
	                pstm.setString(3, uusi.getAccountPassword());
	                pstm.setString(4, uusi.getFirstName());
	                pstm.setString(5, uusi.getLastName());
	                pstm.setString(6, uusi.getAddress());
	                pstm.executeUpdate();	
	                conn.commit();
				    conn.close();	
				    
	            }
	
	        }
	 }

