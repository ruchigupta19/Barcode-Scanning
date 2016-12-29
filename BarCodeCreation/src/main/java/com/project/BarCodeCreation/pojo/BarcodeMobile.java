package com.project.BarCodeCreation.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="BarcodeMobile")

public class BarcodeMobile {
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name="id")
	private int id;
	
	@Column(name="mobileNumber",unique=true,nullable=false)
	private int mobileNumber;
	
	@Column(name="name")
	private String name;
	
	@Column(name="typeOfOS")
	private String typeOfOS;
	
	@Column(name="version")
	private String version;
	
	@Column(name="onMeraki")
	private String onMeraki;
	
	@Column(name="dateOfpurchase")
	private Date dateOfpurchase;
	
	@Column(name="barcodeImageName")
    private String barcodeImageName;

	public String getBarcodeImageName() {
		return barcodeImageName;
	}
	public void setBarcodeImageName(String barcodeImageName) {
		this.barcodeImageName = barcodeImageName;
	}
	public void setDateOfpurchase() {
		this.dateOfpurchase = new Date();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(int mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTypeOfOS() {
		return typeOfOS;
	}
	public void setTypeOfOS(String typeOfOS) {
		this.typeOfOS = typeOfOS;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getOnMeraki() {
		return onMeraki;
	}
	public void setOnMeraki(String onMeraki) {
		this.onMeraki = onMeraki;
	}
	public Date getDateOfpurchase() {
		return dateOfpurchase;
	}
	
	
	
	

}
