package mokkiproject3;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;  
import java.util.Date;  
import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;


/**
 * Servlet implementation class MokkiValintaServlet
 */
@WebServlet("/adminosa/AdminMokkiValintaServlet")
public class AdminMokkiValintaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    @Resource(name="jdbc/SchoolDB")
    private DataSource ds;
    accounts acc = new accounts();
    public AdminMokkiValintaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] checked = request.getParameterValues("roomRentID");
		String ilmoitus = "";
		String sqlLause1 = "DELETE FROM roomRents WHERE roomRentID=?";
		
		if (request.getParameter("RemoveReservation")!= null ) {
			try (Connection conn = ds.getConnection()) {
					conn.setAutoCommit(false);
					if (checked!=null) {
						for (String i : checked) {
							PreparedStatement pstm1 = conn.prepareStatement(sqlLause1);
							pstm1.setInt(1, Integer.parseInt(i));
							pstm1.execute();
							}
						conn.commit();
						conn.close();
					}
					else {
						ilmoitus="Valitse jokin huone";
					}
				}
	
				catch(SQLException e) {
					}
		}
		else if (request.getParameter("GetUserInfo") != null)
			{
			if (checked!=null) {
				try {
					List<accounts> foundInfo = GetAccountInfo(request.getParameterValues("roomRentID"));
					request.setAttribute("AccountInfo", foundInfo);	
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else {
				ilmoitus="Valitse jokin ihminen";
			}
			}
			
			request.setAttribute("ilmoitus", ilmoitus);
		    RequestDispatcher rd = request.getRequestDispatcher("VuokraControl.jsp");
		    rd.forward(request, response);
		}  

	
	
	
public List<accounts> GetAccountInfo(String[] rentID) throws SQLException {
	List<accounts> accountinfo = new ArrayList<accounts>();

	String sqlLause = "SELECT Accounts.AccountName, AccountEmail, AccountAddress, AccountFirstName, AccountLastName, Accounts.AccId FROM Accounts INNER JOIN roomRents ON roomRents.AccountName=Accounts.AccountName WHERE roomRents.roomRentID=? GROUP BY Accounts.AccountName";
		try (Connection conn = ds.getConnection()) {
				conn.setAutoCommit(false);;
				for (String i : rentID) {
					PreparedStatement pstm = conn.prepareStatement(sqlLause, Statement.RETURN_GENERATED_KEYS);
					pstm.setInt(1, Integer.parseInt(i));
					try(ResultSet rs = pstm.executeQuery()){
						while (rs.next()) {
							accounts accinfo = new accounts();
							accinfo.setAccountName(rs.getString(1));
							accinfo.setAccountEmail(rs.getString(2));
							accinfo.setAddress(rs.getString(3));
							accinfo.setFirstName(rs.getString(4));
							accinfo.setLastName(rs.getString(5));
							accinfo.setAccountID(rs.getInt(6));;
							accountinfo.add(accinfo);
							
							}
					}
				}
				      
				      conn.close();
		}
		return accountinfo;
	}
}


