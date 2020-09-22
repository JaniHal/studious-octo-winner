package mokkiproject3;

public class accounts {

	private String accountName;
	private int accountID;
	private String address;
	private String FirstName;
	private String LastName;
	private String accountPassword;
	private String accountEmail;
	
	
	public String getAccountPassword() {
		return accountPassword;
	}
	public void setAccountPassword(String accountPassword) {
		this.accountPassword = accountPassword;
	}

	
	
	public String getAccountEmail() {
		return accountEmail;
	}
	public void setAccountEmail(String accountEmail) {
		this.accountEmail = accountEmail;
	}
	public accounts() {
		
	}
	public accounts(String accountName, int accountID) {
		super();
		this.accountName = accountName;
		this.accountID = accountID;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public int getAccountID() {
		return accountID;
	}
	public void setAccountID(int accountID) {
		this.accountID = accountID;
	}

	public accounts(String accountName, String accountEmail, String accountPassword, String address, String firstName, String lastName) {
		super();
		this.accountName = accountName;
		this.address = address;
		this.FirstName = firstName;
		this.LastName = lastName;
		this.accountPassword = accountPassword;
		this.accountEmail = accountEmail;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getFirstName() {
		return FirstName;
	}
	public void setFirstName(String firstName) {
		FirstName = firstName;
	}
	public String getLastName() {
		return LastName;
	}
	public void setLastName(String lastName) {
		LastName = lastName;
	}

	
}
