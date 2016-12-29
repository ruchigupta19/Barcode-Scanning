package com.project.BarCodeCreation.DAO;

import java.io.File;
import java.util.Date;

import org.hibernate.Query;

import com.project.BarCodeCreation.pojo.BarcodeEmployee;
import com.project.BarCodeCreation.pojo.BarcodeMobile;
import com.project.BarCodeCreation.pojo.CheckoutEntry;

import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.BarcodeFactory;
import net.sourceforge.barbecue.BarcodeImageHandler;

public class BarcodeGenerationDAO extends DAO{
	
	public BarcodeEmployee createBarcodeForEmployee(String fName,String lName,String eMailID,String team,int cardNo) throws Exception{
		
		Barcode barcode = BarcodeFactory.createCode128B(String.valueOf(cardNo));
		barcode.setBarHeight(60);
		barcode.setBarWidth(2);
	    String fileName = "Employee"+cardNo+".jpg";
		File imgFile = new File("/Users/rgupta/BarcodeEmployees/"+fileName);
		BarcodeImageHandler.savePNG(barcode, imgFile);	
		
		begin();
		
			BarcodeEmployee bare=new BarcodeEmployee();
			bare.setfName(fName);
			bare.setlName(lName);
			bare.setEmailId(eMailID); 
			bare.setTeam(team);
			bare.setCardNo(cardNo);
            bare.setCreationDate();
            bare.setBarcodeUrl(fileName);
            getSession().save(bare);
            commit();
            return bare;
	}
	
	public BarcodeMobile createBarcodeForMobile(String name,String typeOfOS, String version,String onMeraki,int mobileNumber) throws Exception{
		System.out.println("-------------In DAO");
		Barcode barcode = BarcodeFactory.createCode128B(String.valueOf(mobileNumber));
		barcode.setBarHeight(60);
		barcode.setBarWidth(2);
	    String fileName = "mobile"+mobileNumber+".jpg";;
		File imgFile = new File("/Users/rgupta/BarcodeMobiles/"+fileName);
		BarcodeImageHandler.savePNG(barcode, imgFile);	
		
//		String mobileNo = String.valueOf(mobileNumber);
//		String fileName = "mobile"+mobileNo+".jpg";
//		ByteArrayOutputStream out= QRCode.from(mobileNo).to(ImageType.JPG).stream();
//		File f = new File("/Users/rgupta/BarcodesMobiles"+fileName);
//		FileOutputStream fos = new FileOutputStream(f);
//		fos.write(out.toByteArray());
//		fos.flush();
		
		begin();
		System.out.println("-------------Started Transaction");
		BarcodeMobile barcodeMobile=new BarcodeMobile();
		barcodeMobile.setDateOfpurchase();
		barcodeMobile.setName(name);
		barcodeMobile.setTypeOfOS(typeOfOS);
		barcodeMobile.setOnMeraki(onMeraki);
		barcodeMobile.setVersion(version);
		barcodeMobile.setMobileNumber(mobileNumber);
		barcodeMobile.setBarcodeImageName(fileName);
        getSession().save(barcodeMobile);
        commit();
        return barcodeMobile;
	}

	public BarcodeEmployee checkBadge(String badgeNumber) {
		begin();
		Query query = getSession().createQuery("from BarcodeEmployee where cardNo = :cno");
		query.setString("cno",badgeNumber);
		BarcodeEmployee barcodeEmployee=(BarcodeEmployee) query.uniqueResult();
		commit();
		return barcodeEmployee;
	}
	
	public CheckoutEntry createCheckOutInEntry(String badgeNumber,String p0){
		begin();
		CheckoutEntry checkoutEntry = new CheckoutEntry();
		Query query = getSession().createQuery("from BarcodeEmployee where cardNo = :cno");
		query.setString("cno",badgeNumber);
		BarcodeEmployee barcodeEmployee=(BarcodeEmployee) query.uniqueResult();
		if(p0!=null){
			Query query1 = getSession().createQuery("from BarcodeMobile where mobileNumber = :mno");
			query1.setString("mno",p0);
			BarcodeMobile barcodeMobile=(BarcodeMobile) query1.uniqueResult();
			if(barcodeMobile!=null){
			checkoutEntry.setBadgeNumber(barcodeEmployee.getCardNo());
			checkoutEntry.setCheckedOutDate();
			checkoutEntry.setFirstName(barcodeEmployee.getfName());
			checkoutEntry.setLastName(barcodeEmployee.getlName());
			checkoutEntry.setMobileName(barcodeMobile.getName());
			checkoutEntry.setMobileNumber(barcodeMobile.getMobileNumber());
			checkoutEntry.setEmail(barcodeEmployee.getEmailId());
			checkoutEntry.setStatus("Booked");
			getSession().save(checkoutEntry);
			commit();}
		}
		System.out.println("The entry is--------------"+checkoutEntry.getBadgeNumber()+checkoutEntry.getFirstName());
		return checkoutEntry;
	}

	public boolean checkIfMobileExists(String deviceNumber) {
		begin();
		Query query = getSession().createQuery("from BarcodeMobile where mobileNumber = :mno");
		query.setString("mno",deviceNumber);
		BarcodeMobile barcodeMobile=(BarcodeMobile) query.uniqueResult();
		commit();
		if(barcodeMobile!=null)
			return true;
		else
			return false;
	}

	public BarcodeMobile checkMobile(String deviceNumber) {
		begin();
		Query query = getSession().createQuery("from BarcodeMobile where mobileNumber = :mno");
		query.setString("mno",deviceNumber);
		BarcodeMobile barcodeMobile=(BarcodeMobile) query.uniqueResult();
		commit();
		return barcodeMobile;
	}

	public CheckoutEntry checkBookedEntry(String badgeNumber, String p0) {
		Date d=new Date();
		begin();
		Query query1 = getSession().createQuery("from CheckoutEntry where badgeNumber = :badgeNumber and MobileNumber = :p0 and status = :chkStatus");
		query1.setString("badgeNumber",badgeNumber);
		query1.setString("p0",p0);
		query1.setString("chkStatus","Booked");
		CheckoutEntry checkoutEntry=(CheckoutEntry) query1.uniqueResult();
		if(checkoutEntry!=null){
		Query query = getSession().createQuery("update CheckoutEntry set status = :newStatus, checkedInDate =:dateOfcheckin where badgeNumber = :badgeNumber and MobileNumber = :p0 and status = :chkStatus");
		query.setString("badgeNumber",badgeNumber);
		query.setDate("dateOfcheckin", d);
		query.setString("p0",p0);
		query.setString("chkStatus","Booked");
		query.setString("newStatus","Returned");
		int result=query.executeUpdate();}
		commit();
		return checkoutEntry;
	}
	
	
}
 