package mokkiproject3;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
 * Servlet implementation class VuokrausServlet
 */
@WebServlet("/adminosa/AdminVuokrausServlet")
public class AdminVuokrausServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public AdminVuokrausServlet(DataSource ds) {
		this.ds=ds;
	}
    @Resource(name="jdbc/SchoolDB")
    private DataSource ds;
    
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminVuokrausServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("vuokraus.jsp");
        rd.forward(request, response);
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int roomAmount = Integer.parseInt(request.getParameter("rooms"));
		String ilmoitus = "Huoneita ei löytynyt";
		try {
			List<mokki> huoneet = GetRoomInfo(roomAmount);
			HttpSession session = request.getSession();
			session.setAttribute("roomamountchoice", roomAmount);

			if (huoneet.isEmpty() != true) {
				request.setAttribute("searchedrooms", huoneet);
			}
			else {
				request.setAttribute("ilmoitus", ilmoitus);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    RequestDispatcher rd = request.getRequestDispatcher("VuokraControl.jsp");
	    rd.forward(request, response);
		
		
	}	
	public List<mokki> GetRoomInfo(int roomAmount) throws SQLException {
		List<mokki> list=new ArrayList<mokki>();
		String sqlLause = "SELECT AccountName, room_location, rooms.room_price_per_night, datediff(rent_until, rent_date)*rooms.room_price_per_night, roomRentID, roomRents.room_id, room_amount, roomRents.rent_date, roomRents.rent_until FROM roomRents INNER JOIN rooms ON roomRents.room_id=rooms.room_id WHERE room_amount=? GROUP BY roomRents.roomRentID ";
		try (Connection conn = ds.getConnection()) {
			try(PreparedStatement pstm = conn.prepareStatement(sqlLause, Statement.RETURN_GENERATED_KEYS)){
				pstm.setInt(1, roomAmount);
				try(ResultSet rs = pstm.executeQuery()){
					while (rs.next()) {
						mokki rooms = new mokki();
						rooms.setMokkiRenter(rs.getString(1));
						rooms.setMokkiLocation(rs.getString(2));
						rooms.setMokkiPricePerNight(rs.getInt(3));
						rooms.setMokkiTotalPrice(rs.getInt(4));
						rooms.setMokkiRentID(rs.getInt(5));
						rooms.setMokkiID(rs.getInt(6));
						rooms.setMokkiRoomAmount(rs.getInt(7));
						rooms.setMokkiRentDate(rs.getDate(8));
						rooms.setMokkiRentUntil(rs.getDate(9));
						list.add(rooms);
						
						}
				      rs.close();
				      conn.close();
					}
				}
				
			}
		return list;
	}
}