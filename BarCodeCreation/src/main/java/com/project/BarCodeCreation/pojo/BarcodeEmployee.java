package com.project.BarCodeCreation.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="BarcodeEmployee")

public class BarcodeEmployee {

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name="id")
	private int id;
	
	@Column(name="fName")
	private String fName;
	
	@Column(name="lName")
	private String lName;
	
	@Column(name="emailId")
	private String emailId;
	
	@Column(name="team")
	private String team;
	
	@Column(name="barcodeUrl")
	private String barcodeUrl;
	
	@Column(name="creationDate")
	private Date creationDate;
	
	@Column(name="cardNo",unique=true,nullable=false)
	private int cardNo;
	
	public int getCardNo() {
		return cardNo;
	}

	public void setCardNo(int cardNo) {
		this.cardNo = cardNo;
	}

	public void setCreationDate() {
		this.creationDate = new Date();
	}
	
	public Date getCreationDate(){
		return creationDate;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getfName() {
		return fName;
	}
	public void setfName(String fName) {
		this.fName = fName;
	}
	public String getlName() {
		return lName;
	}
	public void setlName(String lName) {
		this.lName = lName;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getTeam() {
		return team;
	}
	public void setTeam(String team) {
		this.team = team;
	}
	public String getBarcodeUrl() {
		return barcodeUrl;
	}
	public void setBarcodeUrl(String barcodeUrl) {
		this.barcodeUrl = barcodeUrl;
	}
	
	
}
