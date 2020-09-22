package mokkiproject3;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class AdminAddRoomServlet
 */
@WebServlet("/adminosa/AdminAddRoomServlet")
public class AdminAddRoomServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Resource(name="jdbc/SchoolDB")
	private DataSource ds;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminAddRoomServlet() {
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
		String ilmoitus="";
		int mokkiRoomAmount=Integer.parseInt(request.getParameter("roomamount"));
		String mokkiLocation=request.getParameter("location");
		int mokkiPricePerNight = Integer.parseInt(request.getParameter("priceperday"));
        if (mokkiRoomAmount==0 || mokkiLocation.isEmpty() || mokkiPricePerNight==0) {
            ilmoitus = "Kaikki tiedot pit‰‰ antaa!";
        }
        else {
			mokki uusi = new mokki(mokkiLocation, mokkiPricePerNight, mokkiRoomAmount);
	        try {
				addRoom(uusi);
			} catch (SQLException e) {
				ilmoitus="lis‰ys ei onnistunut";
				e.printStackTrace();
			}
        }
	    ilmoitus="Account created";
        request.setAttribute("ilmoitus", ilmoitus);
        response.sendRedirect("VuokraControl.jsp");	 
      
	}

	
	
	public void addRoom (mokki uusi) throws SQLException {
        String sqlLause = "INSERT INTO rooms(room_amount, room_location, room_price_per_night) VALUES(?, ?, ?)";
        try (Connection conn = ds.getConnection()){
            	conn.setAutoCommit(false);
            	PreparedStatement pstm = conn.prepareStatement(sqlLause);
                pstm.setInt(1,  uusi.getMokkiRoomAmount());
                pstm.setString(2, uusi.getMokkiLocation());
                pstm.setInt(3, uusi.getMokkiPricePerNight());
                pstm.executeUpdate();	
                conn.commit();
			    conn.close();	
			    
            }

        }
	
	
	
}
