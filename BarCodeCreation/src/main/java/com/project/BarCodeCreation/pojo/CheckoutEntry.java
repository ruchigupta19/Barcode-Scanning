package com.project.BarCodeCreation.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="CheckoutEntry")
public class CheckoutEntry {
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name="id",unique=true,nullable=false)
	private int id;
	
	@Column(name="checkedOutDate")
	private Date checkedOutDate;
	
	@Column(name="checkedInDate")
	private Date checkedInDate;
	
	@Column(name="badgeNumber")
	private int badgeNumber;
	
	@Column(name="firstName")
	private String firstName;
	
	@Column(name="lastName")
	private String lastName;

	@Column(name="MobileNumber")
	private int MobileNumber;

	@Column(name="MobileName")
	private String MobileName;
	
	@Column(name="status")
	private String status;
	
	@Column(name="email")
	private String email;
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStatus() {
		return status;
	}

	public Date getCheckedInDate() {
		return checkedInDate;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getCheckedOutDate() {
		return checkedOutDate;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public void setCheckedOutDate() {
		this.checkedOutDate = new Date();
	}

	public int getBadgeNumber() {
		return badgeNumber;
	}

	public void setBadgeNumber(int badgeNumber) {
		this.badgeNumber = badgeNumber;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public int getMobileNumber() {
		return MobileNumber;
	}

	public void setMobileNumber(int mobileNumber) {
		MobileNumber = mobileNumber;
	}

	public String getMobileName() {
		return MobileName;
	}

	public void setMobileName(String mobileName) {
		MobileName = mobileName;
	}
	
	

}
