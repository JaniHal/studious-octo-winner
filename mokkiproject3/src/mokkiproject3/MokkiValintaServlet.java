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
@WebServlet("/MokkiValintaServlet")
public class MokkiValintaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    @Resource(name="jdbc/SchoolDB")
    private DataSource ds;
    public MokkiValintaServlet() {
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
		HttpSession session = request.getSession();
		String account = (String) session.getAttribute("user");
		String ilmoitus = "";
		String startdate=request.getParameter("startdate");
		String enddate=request.getParameter("enddate");	
		String errormsg = "P‰iv‰ys on v‰‰rin";
		int selectedRoom;
		int roomcheck=0;
		String sqlLause3 = "INSERT INTO roomRents(AccountName, room_id, rent_date, rent_until) VALUES(?, ?, ?, ?)";
		
								//to have 2 buttons in one form
		if (request.getParameter("RentRoom")!= null )
			if (account != null && !request.getParameter("startdate").isEmpty() && !request.getParameter("enddate").isEmpty() && !request.getParameter("roomselection").isEmpty()) {
				selectedRoom = Integer.parseInt(request.getParameter("roomselection"));
				List<Date> dateList = new ArrayList<Date>();
				try {
					dateList=CheckAvailability(selectedRoom);
				} catch (SQLException | ParseException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				try {
					roomcheck = checkDates(dateList, startdate, enddate);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if (roomcheck==0) {
					try (Connection conn = ds.getConnection()) {
						DateFormat format = new SimpleDateFormat("yyyy-MM");
						Date startdate1 = format.parse(startdate);
						Date enddate1 = format.parse(enddate);
						java.sql.Date sqlStartDate = new java.sql.Date(startdate1.getTime());
						java.sql.Date sqlEndDate = new java.sql.Date(enddate1.getTime());
						if (sqlStartDate.compareTo(sqlEndDate)<=0) {
							conn.setAutoCommit(false);
							PreparedStatement pstm3 = conn.prepareStatement(sqlLause3);
							pstm3.setString(1, account);
							pstm3.setInt(2, selectedRoom);;
							pstm3.setDate(3, sqlStartDate);
							pstm3.setDate(4, sqlEndDate);
							pstm3.execute();
							conn.commit();
							conn.close();
							ilmoitus="Vuokrattu onnistuneesti";
							request.setAttribute("ilmoitus", ilmoitus);
							}
						else {
							request.setAttribute("ilmoitus", errormsg);
						}
					}
		
						catch(SQLException | ParseException e) {
							request.setAttribute("ilmoitus", errormsg);
							}
				}
				else if (roomcheck!=0) {
					ilmoitus="Huone ei vapaana valittuna aikana";
					System.out.println("testt");
					request.setAttribute("ilmoitus", ilmoitus);
					}
			}
			else if (account==null) {
				ilmoitus="Et ole kirjautunut sis‰‰n";
				request.setAttribute("ilmoitus", ilmoitus);
			}
			else {
				ilmoitus="T‰yt‰ kaikki tiedot";
				request.setAttribute("ilmoitus", ilmoitus);
			}
				
				
				
				
		else if (request.getParameter("CheckAvailability")!=null) {
			List<Date> dateList = new ArrayList<Date>();
			if (!request.getParameter("startdate").isEmpty() && !request.getParameter("enddate").isEmpty() && !request.getParameter("roomselection").isEmpty()) {
			selectedRoom = Integer.parseInt(request.getParameter("roomselection"));
			try {
				dateList=CheckAvailability(selectedRoom);
				roomcheck = checkDates(dateList, startdate, enddate);
				System.out.println(dateList);
				if (roomcheck==0) {
					ilmoitus="Huone on vapaana";
					request.setAttribute("ilmoitus", ilmoitus);
				}
				else {
					ilmoitus="Huone ei vapaana";
					request.setAttribute("ilmoitus", ilmoitus);
					request.setAttribute("TakenTimes", dateList);
					System.out.println(dateList);
				}
			} catch (SQLException | ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			else {
				ilmoitus="T‰yt‰ kaikki tiedot";
				request.setAttribute("ilmoitus", ilmoitus);
			}
		}
        RequestDispatcher rd = request.getRequestDispatcher("vuokraus.jsp");
        rd.forward(request, response);
	}
	
	
	public List<Date> CheckAvailability(int RoomID) throws SQLException, ParseException {
		List<Date> dateList = new ArrayList<Date>();

		String sqlLause = "SELECT rent_date, rent_until FROM roomRents WHERE room_id=? GROUP BY roomRentID";
		
		
		
		try (Connection conn2=ds.getConnection()) {
			PreparedStatement pstm= conn2.prepareStatement(sqlLause, Statement.RETURN_GENERATED_KEYS);
			pstm.setInt(1, RoomID);;
			try(ResultSet rs=pstm.executeQuery()) {
				while(rs.next()) {
					dateList.add(rs.getDate(1));
					dateList.add(rs.getDate(2));
				}
			}
		}
		
			return dateList;
	}
	
	
	public int checkDates(List<Date> List, String DateA, String DateB ) throws ParseException {
	int check=0;
	DateFormat format = new SimpleDateFormat("yyyy-MM");
	Date startdate1 = format.parse(DateA);
	Date enddate1 = format.parse(DateB);  /////list dates are in sets of 2, 1=start date 2=end date
	for (int i=0; i<List.size(); i=i+2) {
		boolean dates1 = (startdate1.after(List.get(i)) && startdate1.before(List.get(i+1)));
		boolean dates2 = (enddate1.after(List.get(i)) && enddate1.before(List.get(i+1)));
		if (dates1==true || dates2==true) {
			check++;
		}	
	}


	
	
	
	return check;
	}
	
}
	


