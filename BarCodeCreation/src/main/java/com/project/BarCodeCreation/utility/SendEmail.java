package com.project.BarCodeCreation.utility;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.commons.mail.SimpleEmail;

public class SendEmail {
	

public boolean sendAMail(String toAddress,String fName,String lName,String filename){
	
	//create the attachment
	
	EmailAttachment attachment = new EmailAttachment();
	attachment.setPath("/Users/rgupta/BarcodeEmployees/"+filename);
	attachment.setDisposition(EmailAttachment.ATTACHMENT);
	attachment.setDescription("Barcode for checking out mobiles");
	attachment.setName("barcode");
	
	
	MultiPartEmail email = new MultiPartEmail();
	email.setHostName("smtp.googlemail.com");
	email.setSmtpPort(465);
	email.setAuthenticator(new DefaultAuthenticator("rgupta@brightcove.com", "Brightcove@247920"));
	email.setSSL(true);
	try {
		email.setFrom("rgupta@brightcove.com");
		email.setSubject("Automated Email : Barcode for checking out mobiles");
		email.setMsg("Hello "+fName+" "+lName+","+"\n\nPlease find your attached barcode.\nTake its print out and stick it on the back of your card.  \n\n Thank You \n\nRegards\nRuchi Gupta");
		email.addTo(toAddress);
		email.attach(attachment);
		email.send();
		return true;
	} catch (EmailException e) {
		e.printStackTrace();
		return false;
	}
	
	
}
public boolean sendCheckoutInMail5(String toAddress,String fName,String lName,String p0,String p1, String p2,String p3,String p4){
	
	Email email = new SimpleEmail();
	email.setHostName("smtp.googlemail.com");
	email.setSmtpPort(465);
	email.setAuthenticator(new DefaultAuthenticator("gupta.r1901@gmail.com", "Sweethome@01472"));
	email.setSSL(true);
	try {
		email.setFrom("gupta.r1901@gmail.com");
		email.setSubject("Your Mobile Checkout/Checkin Details");
		email.setMsg("Hello "+fName+" "+lName+","+"\nSuccessfully checked out MOBILE"+p0+"\n successfully checked out MOBILE"+p1+"\n successfully checked out MOBILE"+p2+"\n successfully checked out MOBILE"+p3+"\n successfully checked out MOBILE"+p4);
		email.addTo(toAddress);
		email.send();
		return true;
	} catch (EmailException e) {
		e.printStackTrace();
		return false;
	}
	
	
}
public boolean sendCheckoutInMail4(String toAddress,String fName,String lName,String p0,String p1, String p2,String p3, String chkin){
	
	Email email = new SimpleEmail();
	email.setHostName("smtp.googlemail.com");
	email.setSmtpPort(465);
	email.setAuthenticator(new DefaultAuthenticator("gupta.r1901@gmail.com", "Sweethome@01472"));
	email.setSSL(true);
	try {
		email.setFrom("gupta.r1901@gmail.com");
		email.setSubject("Your Mobile Checkout/Checkin Details");
		email.setMsg("Hello "+fName+" "+lName+","+"\nSuccessfully checked out MOBILE"+p0+"\n successfully checked out MOBILE"+p1+"\n successfully checked out MOBILE"+p2+"\n successfully checked out MOBILE"+p3+"\n"+chkin);
		email.addTo(toAddress);
		email.send();
		return true;
	} catch (EmailException e) {
		e.printStackTrace();
		return false;
	}
	
	
}

public boolean sendCheckoutInMail3(String toAddress,String fName,String lName,String p0,String p1, String p2,String chkin){
	
	Email email = new SimpleEmail();
	email.setHostName("smtp.googlemail.com");
	email.setSmtpPort(465);
	email.setAuthenticator(new DefaultAuthenticator("gupta.r1901@gmail.com", "Sweethome@01472"));
	email.setSSL(true);
	try {
		email.setFrom("gupta.r1901@gmail.com");
		email.setSubject("Your Mobile Checkout/Checkin Details");
		email.setMsg("Hello "+fName+" "+lName+","+"\nSuccessfully checked out MOBILE"+p0+"\n successfully checked out MOBILE"+p1+"\n successfully checked out MOBILE"+p2+"\n"+chkin);
		email.addTo(toAddress);
		email.send();
		return true;
	} catch (EmailException e) {
		e.printStackTrace();
		return false;
	}
	
	
}

public boolean sendCheckoutInMail2(String toAddress,String fName,String lName,String p0,String p1,String chkin){
	
	Email email = new SimpleEmail();
	email.setHostName("smtp.googlemail.com");
	email.setSmtpPort(465);
	email.setAuthenticator(new DefaultAuthenticator("gupta.r1901@gmail.com", "Sweethome@01472"));
	email.setSSL(true);
	try {
		email.setFrom("gupta.r1901@gmail.com");
		email.setSubject("Your Mobile Checkout/Checkin Details");
		email.setMsg("Hello "+fName+" "+lName+","+"\nSuccessfully checked out MOBILE"+p0+"\n successfully checked out MOBILE"+p1+"\n"+chkin);
		email.addTo(toAddress);
		email.send();
		return true;
	} catch (EmailException e) {
		e.printStackTrace();
		return false;
	}
	
	
}

public boolean sendCheckoutInMail1(String toAddress,String fName,String lName,String p0,String chkin){
	
	Email email = new SimpleEmail();
	email.setHostName("smtp.googlemail.com");
	email.setSmtpPort(465);
	email.setAuthenticator(new DefaultAuthenticator("gupta.r1901@gmail.com", "Sweethome@01472"));
	email.setSSL(true);
	try {
		email.setFrom("gupta.r1901@gmail.com");
		email.setSubject("Your Mobile Checkout/Checkin Details");
		email.setMsg("Hello "+fName+" "+lName+","+"\nSuccessfully checked out MOBILE"+p0+"\n"+chkin);
		email.addTo(toAddress);
		email.send();
		return true;
	} catch (EmailException e) {
		e.printStackTrace();
		return false;
	}
	
	
}
}
