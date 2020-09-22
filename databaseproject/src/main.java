
public class main {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		MySQLAccess mysql = new MySQLAccess();
		try {
			System.out.println(mysql.searchEventsByType("Konsertti"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
	//		mysql.addToDatabase("Jani", "32342429", "Jani@gmail.com");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	//	mysql.addPersonToEvent(3, 2);
		mysql.changePaymentStatus(5, false);
		System.out.println("All info:");
		System.out.println(mysql.getAllInfo());
		System.out.println("Payment info:");
		System.out.println(mysql.getPayments(false)); //true/false, paid or not
	}

}
