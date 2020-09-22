
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class MySQLAccess {
	  private Connection connect = null;
	  private Statement statement = null;
	  private PreparedStatement preparedStatement = null;
	  private ResultSet resultSet = null;

	  final private String host = "jdbc:mysql://mysql.cc.puv.fi:3306/e1401190_databasesproject?serverTimezone=UTC";
	  final private String user = "e1401190";
	  final private String passwd = "5CedHtdmk7Yy";
	  
	  
	  
	  public void addToDatabase(String memberName, String memberPhone, String memberEmail) throws Exception {
		  try {
		  Class.forName("com.mysql.cj.jdbc.Driver");
		  Connection conn = DriverManager.getConnection(host, user, passwd);
		  preparedStatement = conn.prepareStatement("INSERT INTO Members VALUES (?,?,?,?)");
		  preparedStatement.setInt(1, 0); //auto increment mysql side
		  preparedStatement.setString(2, memberName);
		  preparedStatement.setString(3, memberPhone);
		  preparedStatement.setString(4, memberEmail);
		  preparedStatement.execute();
		  }
		  catch (Exception e) {
			  throw e;
		  } finally {
			close();
		  }
	  }
	  
	  
	  public String searchEventsByType (String eventName) throws Exception {
		  String searchResult = "";
		  try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(host,user,passwd);
			preparedStatement = conn.prepareStatement("SELECT * FROM Events WHERE eventName=?");
			preparedStatement.setString(1, eventName);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				searchResult=resultSet.getString(2)+" "+resultSet.getString(3)+" "+resultSet.getString(4)+" "+resultSet.getString(5)+" "+resultSet.getInt(6);
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return searchResult;
		  
	  }
	  
	  public String getAllInfo() throws Exception {
		  String searchResult ="";
		  try {
			  Class.forName("com.mysql.cj.jdbc.Driver");
			  Connection conn = DriverManager.getConnection(host,user,passwd);
			  preparedStatement = conn.prepareStatement("SELECT * FROM Payments p1 INNER JOIN Members m1 ON p1.memberID=m1.memberID INNER JOIN Events e1 ON "
			  		+ "p1.eventID=e1.eventID ORDER BY p1.memberID");
			  resultSet = preparedStatement.executeQuery();
			  searchResult = writeResultSet(resultSet);
		  }
		  catch(Exception e) {
			  throw e; 
		  } finally {
			  close();
		  }
		  return searchResult;
	  }
	  private String writeResultSet(ResultSet resultSet) throws SQLException {
			 String result = "";
			  while(resultSet.next()) {
				  int paymentID = resultSet.getInt(1);
				  int memberID = resultSet.getInt(2);
				  int eventID = resultSet.getInt(3);
				  int eventPrice = resultSet.getInt(4);
				  String eventName = resultSet.getString(5);
				  boolean paymentPaid = resultSet.getBoolean(6);
				  String memberName = resultSet.getString(8);
				  String memberPhone = resultSet.getString(9);
				  String memberEmail = resultSet.getString(10);
				  String eventDate = resultSet.getString(13);
				  String eventPlace = resultSet.getString(14);
				  String eventHolder = resultSet.getString(15);
				  result += "Member id:"+ memberID + " name: " + memberName + " phone: " + memberPhone + " Email: " +memberEmail +" "
				  		+ "Payment id:"+paymentID+ " status:"+paymentPaid+" amount:"+ eventPrice +" Event id:"+eventID+" name:"+eventName+ 
				  		" location:"+ eventPlace+ " holder:"+eventHolder+ " date:"+eventDate+ System.lineSeparator();
				  
			  }
			return result;

		  }
	  
	  
	  
	  
	  public String getPayments(boolean paymentStatus) throws Exception {
		  String searchResult = "";
		  
		  try {
			  Class.forName("com.mysql.cj.jdbc.Driver");
			  Connection conn = DriverManager.getConnection(host,user,passwd);
			  preparedStatement = conn.prepareStatement("SELECT * FROM Payments p1 INNER JOIN Members m1 ON p1.memberID=m1.memberID WHERE paymentPaid=?");
			  preparedStatement.setBoolean(1, paymentStatus);
			  resultSet = preparedStatement.executeQuery();
			  searchResult = writeResultSet2(resultSet);
		  }
		  catch(Exception e) {
			  throw e; 
		  } finally {
			  close();
		  }
		  return searchResult;
	  }
	  
	  private String writeResultSet2(ResultSet resultSet) throws SQLException {
		 String result = "";
		  while(resultSet.next()) {
			  int paymentID = resultSet.getInt(1);
			  int memberID = resultSet.getInt(2);
			  int eventID = resultSet.getInt(3);
			  int eventPrice = resultSet.getInt(4);
			  String eventName = resultSet.getString(5);
			  boolean paymentPaid = resultSet.getBoolean(6);
			  String memberName = resultSet.getString(8);
			  String memberPhone = resultSet.getString(9);
			  String memberEmail = resultSet.getString(10);
			  result += "Member id:"+ memberID + " name: " + memberName + " phone: " + memberPhone + " Email: " +memberEmail +
					  " Payment id:"+paymentID+ " status:"+paymentPaid+" amount:"+ eventPrice +" Event id:"+eventID+" name:"+eventName+ " location:"+System.lineSeparator();
			  
		  }
		return result;

	  }
	  
	  
	  
	  public void addPersonToEvent(int memberID, int eventID) throws Exception {
		  try {
		  Class.forName("com.mysql.cj.jdbc.Driver");
		  Connection conn = DriverManager.getConnection(host, user, passwd);
		  preparedStatement = conn.prepareStatement("INSERT INTO Payments(paymentsID, memberID, eventID, eventPrice, eventName, paymentPaid) SELECT 0, ?, ?, eventPrice, "
		  		+ "eventName, 0 FROM `Events` WHERE eventID=?");
		  preparedStatement.setInt(1, memberID);
		  preparedStatement.setInt(2, eventID);
		  preparedStatement.setInt(3, eventID);
		  preparedStatement.execute();
		  }
		  catch (Exception e) {
			  throw e;
		  } finally {
			close();
		  }
	  }
	  
	  public void changePaymentStatus(int paymentID, boolean paymentStatus) throws Exception {
		  try {
		  Class.forName("com.mysql.cj.jdbc.Driver");
		  Connection conn = DriverManager.getConnection(host, user, passwd);
		  preparedStatement = conn.prepareStatement("UPDATE Payments SET paymentPaid=? WHERE paymentsID=?");
		  preparedStatement.setBoolean(1, paymentStatus);
		  preparedStatement.setInt(2, paymentID);
		  preparedStatement.execute();
		  }
		  catch (Exception e) {
			  throw e;
		  } finally {
			close();
		  }
	  }
	  
	  
	  
	  
	  
	  private void close() {
		    try {
		      if (resultSet != null) {
		        resultSet.close();
		      }

		      if (statement != null) {
		        statement.close();
		      }

		      if (connect != null) {
		        connect.close();
		      }
		    } catch (Exception e) {

		    }
		  }
	  
	  
	  

	  
	  
}
