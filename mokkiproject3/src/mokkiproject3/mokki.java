package mokkiproject3;

import java.util.Date;

public class mokki {


	private String mokkiLocation;
	private int mokkiPricePerNight;
	private int mokkiRoomAmount;
	private int mokkiID;
	private int mokkiRentStatus;
	private int mokkiTotalPrice;
	private String mokkiRenter;
	private int mokkiRentID;
	private Date mokkiRentDate;
	private Date mokkiRentUntil;
	
	
	public Date getMokkiRentDate() {
		return mokkiRentDate;
	}





	public void setMokkiRentDate(Date mokkiRentDate) {
		this.mokkiRentDate = mokkiRentDate;
	}


	public mokki(String mokkiLocation, int mokkiPricePerNight, int mokkiRoomAmount) {
		super();
		this.mokkiLocation = mokkiLocation;
		this.mokkiPricePerNight = mokkiPricePerNight;
		this.mokkiRoomAmount = mokkiRoomAmount;
	}


	public Date getMokkiRentUntil() {
		return mokkiRentUntil;
	}





	public void setMokkiRentUntil(Date mokkiRentUntil) {
		this.mokkiRentUntil = mokkiRentUntil;
	}

	
	
	
	public int getMokkiRentID() {
		return mokkiRentID;
	}





	public void setMokkiRentID(int mokkiRentID) {
		this.mokkiRentID = mokkiRentID;
	}





	public int getMokkiTotalPrice() {
		return mokkiTotalPrice;
	}





	public void setMokkiTotalPrice(int mokkiTotalPrice) {
		this.mokkiTotalPrice = mokkiTotalPrice;
	}





	public int getMokkiRentStatus() {
		return mokkiRentStatus;
	}





	public void setMokkiRentStatus(int mokkiRentStatus) {
		this.mokkiRentStatus = mokkiRentStatus;
	}


	public String mokkiToString() {
		return this.mokkiLocation + this.mokkiPricePerNight + this.mokkiRoomAmount;
	}


	public mokki() {}
	
	
	
	
	
	public mokki(String mokkiLocation, int mokkiPricePerNight, int mokkiRoomAmount, int mokkiRentStatus, int mokkiID, int mokkiTotalPrice, String mokkiRenter, int mokkiRentID) {
		super();
		this.mokkiLocation = mokkiLocation;
		this.mokkiPricePerNight = mokkiPricePerNight;
		this.mokkiRoomAmount = mokkiRoomAmount;
		this.mokkiRentStatus = mokkiRentStatus;
		this.mokkiID = mokkiID;
		this.mokkiTotalPrice = mokkiTotalPrice;
		this.mokkiRenter = mokkiRenter;
		this.mokkiRentID = mokkiRentID;
	}
	public String getMokkiRenter() {
		return mokkiRenter;
	}





	public void setMokkiRenter(String mokkiRenter) {
		this.mokkiRenter = mokkiRenter;
	}





	public String getMokkiLocation() {
		return mokkiLocation;
	}
	public void setMokkiLocation(String mokkiLocation) {
		this.mokkiLocation = mokkiLocation;
	}
	public int getMokkiPricePerNight() {
		return mokkiPricePerNight;
	}
	public void setMokkiPricePerNight(int mokkiPricePerNight) {
		this.mokkiPricePerNight = mokkiPricePerNight;
	}
	public int getMokkiRoomAmount() {
		return mokkiRoomAmount;
	}
	public void setMokkiRoomAmount(int mokkiRoomAmount) {
		this.mokkiRoomAmount = mokkiRoomAmount;
	}
	public int getMokkiID() {
		return mokkiID;
	}
	public void setMokkiID(int mokkiID) {
		this.mokkiID = mokkiID;
	}
	
	
	
	
	
}
