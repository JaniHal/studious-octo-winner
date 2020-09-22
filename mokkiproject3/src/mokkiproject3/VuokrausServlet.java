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
@WebServlet("/VuokrausServlet")
public class VuokrausServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public VuokrausServlet(DataSource ds) {
		this.ds=ds;
	}
    @Resource(name="jdbc/SchoolDB")
    private DataSource ds;
    
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VuokrausServlet() {
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
        RequestDispatcher rd = request.getRequestDispatcher("vuokraus.jsp");
        rd.forward(request, response);
		
		
	}

	
	public List<mokki> GetRoomInfo(int roomAmount) throws SQLException {
		List<mokki> list=new ArrayList<mokki>();

		String sqlLause = " SELECT room_amount, room_location, room_price_per_night, room_id FROM rooms WHERE room_amount=? GROUP BY room_id";
		try (Connection conn = ds.getConnection()) {
			try(PreparedStatement pstm = conn.prepareStatement(sqlLause, Statement.RETURN_GENERATED_KEYS)){
				pstm.setInt(1, roomAmount);
				try(ResultSet rs = pstm.executeQuery()){
					while (rs.next()) {
						mokki rooms = new mokki();
						rooms.setMokkiRoomAmount(rs.getInt(1));
						rooms.setMokkiLocation(rs.getString(2));
						rooms.setMokkiPricePerNight(rs.getInt(3));
						rooms.setMokkiID(rs.getInt(4));
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