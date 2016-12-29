package com.project.BarCodeCreation.controllers;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.project.BarCodeCreation.DAO.BarcodeGenerationDAO;
import com.project.BarCodeCreation.pojo.BarcodeEmployee;
import com.project.BarCodeCreation.pojo.BarcodeMobile;
import com.project.BarCodeCreation.pojo.CheckoutEntry;
import com.project.BarCodeCreation.utility.SendEmail;



@Controller
public class BarcodeController {
	
	
	@RequestMapping(value="/checkEmployeeInDb.htm", method=RequestMethod.POST)
	public void checkEmployeeInDb(HttpServletRequest request,HttpServletResponse response) throws NumberFormatException, Exception{
		
		PrintWriter out1=response.getWriter();
		String cardNoOfUser=request.getParameter("cardNoOfUser");	
		cardNoOfUser=cardNoOfUser.replaceAll("[^\\dA-Za-z]","").replaceAll("[\\s\\s+]","").trim();
		BarcodeGenerationDAO barcodeGenerationDAO=new BarcodeGenerationDAO(); 
		
		BarcodeEmployee barcodeEmployee=barcodeGenerationDAO.checkBadge(cardNoOfUser);
		if(barcodeEmployee!=null){
			out1.println("true");
		}
		else 
			out1.println("false");
	}
	
	@RequestMapping(value="/login.htm", method=RequestMethod.POST)
	public void login(HttpServletRequest request,HttpServletResponse response) throws NumberFormatException, Exception{
		
		PrintWriter out1=response.getWriter();
		String uname=request.getParameter("Username");
		String pword=request.getParameter("password");
		if((uname.trim().equalsIgnoreCase("admin"))&&(pword.trim().equalsIgnoreCase("admin"))){
			out1.println("adminIsHere");
		}
		else if((uname.trim().equalsIgnoreCase("user"))&&(pword.trim().equalsIgnoreCase("user"))){
			out1.println("userIsHere");
		}
		else{
			out1.println("notFound");
		}
	}
	
	
	@RequestMapping(value="/adminIsHere.htm", method=RequestMethod.GET)
	public String adminsWorkArea(HttpServletRequest request,HttpServletResponse response) 
	{
			return "admin";
	}
	
	@RequestMapping(value="/userIsHere.htm", method=RequestMethod.GET)
	public String usersWorkArea(HttpServletRequest request,HttpServletResponse response) 
	{
			return "user";
	}
	
	
	@RequestMapping(value="/checkMobileInDb.htm", method=RequestMethod.POST)
	public void checkMobileInDb(HttpServletRequest request,HttpServletResponse response) throws NumberFormatException, Exception{
		
		PrintWriter out1=response.getWriter();
		String deviceNumber=request.getParameter("deviceNumber");	
		deviceNumber=deviceNumber.replaceAll("[^\\dA-Za-z]","").replaceAll("[\\s\\s+]","").trim();
		BarcodeGenerationDAO barcodeGenerationDAO=new BarcodeGenerationDAO(); 
		
		BarcodeMobile barcodeMobile=barcodeGenerationDAO.checkMobile(deviceNumber);
		if(barcodeMobile==null)
			out1.println("false");
	}
	
	
	@RequestMapping(value="/addBarcodeForEmployee.htm", method=RequestMethod.POST)
	public void createBarCodeForEmployees(HttpServletRequest request,HttpServletResponse response) throws NumberFormatException, Exception{
		
		SendEmail sendEmail= new SendEmail();
		PrintWriter out1=response.getWriter();
		String fName=request.getParameter("fName");
		String lName=request.getParameter("lName");
		String emailId=request.getParameter("mail");
		String team=request.getParameter("team");
		String cardNo=request.getParameter("cardNo");
		
		fName=fName.replaceAll("[^\\dA-Za-z]","").replaceAll("[\\s\\s+]","").trim();
		lName=lName.replaceAll("[^\\dA-Za-z]","").replaceAll("[\\s\\s+]","").trim();
		emailId=emailId.replaceAll("[^\\dA-Za-z@.]","").replaceAll("[\\s\\s+]","").trim();
		team=team.replaceAll("[^\\dA-Za-z]","").replaceAll("[\\s\\s+]","").trim();
		cardNo=cardNo.replaceAll("[^\\dA-Za-z]","").replaceAll("[\\s\\s+]","").trim();
		
		BarcodeGenerationDAO barcodeGenerationDAO=new BarcodeGenerationDAO(); 
	
		BarcodeEmployee barcodeEmployee=barcodeGenerationDAO.createBarcodeForEmployee(fName, lName, emailId, team, Integer.parseInt(cardNo));
		if(barcodeEmployee!=null){
			sendEmail.sendAMail(barcodeEmployee.getEmailId(),barcodeEmployee.getfName(),barcodeEmployee.getlName(),barcodeEmployee.getBarcodeUrl());
			out1.println("true");
		}
		else 
			out1.println("false");
	}
	
	@RequestMapping(value="/addBarcodeForMobile.htm", method=RequestMethod.POST)
	public void createQRCodeForMobiles(HttpServletRequest request,HttpServletResponse response) throws NumberFormatException, Exception{
		System.out.println("-------------In Controller");
		PrintWriter out1=response.getWriter();
		String mobileNumber=request.getParameter("mobileNumber");
		String name=request.getParameter("name");
		String typeOfOS=request.getParameter("typeOfOS");
		String version=request.getParameter("version");
		String onMeraki=request.getParameter("onMeraki");
		
		mobileNumber=mobileNumber.replaceAll("[^\\dA-Za-z]","").replaceAll("[\\s\\s+]","").trim();
		name=name.replaceAll("[^\\dA-Za-z]","").replaceAll("[\\s\\s+]","").trim();
		typeOfOS=typeOfOS.replaceAll("[^\\dA-Za-z@.]","").replaceAll("[\\s\\s+]","").trim();
		version=version.replaceAll("[^\\dA-Za-z]","").replaceAll("[\\s\\s+]","").trim();
		onMeraki=onMeraki.replaceAll("[^\\dA-Za-z]","").replaceAll("[\\s\\s+]","").trim();
		
		BarcodeGenerationDAO barcodeGenerationDAO=new BarcodeGenerationDAO(); 
	
		BarcodeMobile barcodeMobile=barcodeGenerationDAO.createBarcodeForMobile(name, typeOfOS, version, onMeraki, Integer.parseInt(mobileNumber));
		if(barcodeMobile!=null){
			System.out.println("-------------Returning");
			out1.println("true");
		}
		else
			out1.println("false");
	}
	
	@RequestMapping(value="/scanning.htm", method=RequestMethod.POST)
	public void checkoutMobiles(HttpServletRequest request,HttpServletResponse response) throws NumberFormatException, Exception{
		PrintWriter out1=response.getWriter();
		String badgeNumber=request.getParameter("badgeNumber");
		String numberOfDevices=request.getParameter("numberOfDevices");
		String p0=request.getParameter("p0");
		String p1=request.getParameter("p1");
		String p2=request.getParameter("p2");
		String p3=request.getParameter("p3");
		String p4=request.getParameter("p4");
		
		badgeNumber=badgeNumber.replaceAll("[^\\dA-Za-z]","").replaceAll("[\\s\\s+]","").trim();
		numberOfDevices=numberOfDevices.replaceAll("[^\\dA-Za-z]","").replaceAll("[\\s\\s+]","").trim();
		p0=p0.replaceAll("[^\\dA-Za-z@.]","").replaceAll("[\\s\\s+]","").trim();
		p1=p1.replaceAll("[^\\dA-Za-z]","").replaceAll("[\\s\\s+]","").trim();
		p2=p2.replaceAll("[^\\dA-Za-z]","").replaceAll("[\\s\\s+]","").trim();
		p3=p3.replaceAll("[^\\dA-Za-z]","").replaceAll("[\\s\\s+]","").trim();
		p4=p4.replaceAll("[^\\dA-Za-z]","").replaceAll("[\\s\\s+]","").trim();

		
		BarcodeGenerationDAO barcodeGenerationDAO=new BarcodeGenerationDAO(); 
		boolean check0,check1,check2,check3,check4;
		String finalString="",finalReturnedString="";
		int flag=0;
		CheckoutEntry checkoutEntry0=new CheckoutEntry();
		CheckoutEntry checkoutEntry1=new CheckoutEntry();
		CheckoutEntry checkoutEntry2=new CheckoutEntry();
		CheckoutEntry checkoutEntry3=new CheckoutEntry();
		CheckoutEntry checkoutEntry4=new CheckoutEntry();
		int flag0=0,flag1=0,flag2=0,flag3=0,flag4=0;
		
		if(!p0.toString().equals("undefined") && p0!=null){
			check0 = barcodeGenerationDAO.checkIfMobileExists(p0);
			if(check0==true){
				checkoutEntry0 = barcodeGenerationDAO.checkBookedEntry(badgeNumber,p0);
				if(checkoutEntry0==null){
					checkoutEntry0 = barcodeGenerationDAO.createCheckOutInEntry(badgeNumber,p0);
				}else{
					flag0=1;
					finalReturnedString+="Successfully returned MOBILE"+p0+"\n";
				}
				}
			else{
				finalString=p0+" ";
				flag=1;}
		}
		if(!p1.toString().equals("undefined") && p1!=null){
			check1 = barcodeGenerationDAO.checkIfMobileExists(p1);
			if(check1==true){
				checkoutEntry1 = barcodeGenerationDAO.checkBookedEntry(badgeNumber,p1);
				if(checkoutEntry1==null){
					checkoutEntry1=barcodeGenerationDAO.createCheckOutInEntry(badgeNumber,p1);
				}else{
					flag1=1;
					finalReturnedString+="Successfully returned MOBILE"+p1+"\n";
				}
				}
			else{
				if(finalString==""){
				finalString+=p1+" ";
				flag=1;
				}else{
					finalString+=" and "+p1+" ";
					flag=1;
				}
				}
		}
		if(!p2.toString().equals("undefined") && p2!=null){
			check2 = barcodeGenerationDAO.checkIfMobileExists(p2);
			if(check2==true){
				checkoutEntry2 = barcodeGenerationDAO.checkBookedEntry(badgeNumber,p2);
				if(checkoutEntry2==null){
					checkoutEntry2=barcodeGenerationDAO.createCheckOutInEntry(badgeNumber,p2);
				}else{
					flag2=1;
					finalReturnedString+="Successfully returned MOBILE"+p2+"\n";
				}
				}
			else{
				if(finalString==""){
				finalString+=p2+" ";
				flag=1;
				}else{
					finalString+=" and "+p2+" ";
					flag=1;
				}
				
			}
		}
		if(!p3.toString().equals("undefined") && p3!=null){
			System.out.println(p3 + ' ' + p3.toString());
			check3 = barcodeGenerationDAO.checkIfMobileExists(p3);
			if(check3==true){
				checkoutEntry3 = barcodeGenerationDAO.checkBookedEntry(badgeNumber,p3);
				if(checkoutEntry3==null){
					checkoutEntry3=barcodeGenerationDAO.createCheckOutInEntry(badgeNumber,p3);
				}else{
					flag3=1;
					finalReturnedString+="Successfully returned MOBILE"+p3+"\n";
				}	
			}
			else{
				if(finalString==""){
				finalString+=p3+" ";
				flag=1;
				}else
				{
					finalString+=" and "+p3+" ";
					flag=1;
				}
				}
		}
		if(!p4.toString().equals("undefined") && p4!=null){
			check4 = barcodeGenerationDAO.checkIfMobileExists(p4);
			if(check4==true){
				checkoutEntry4 = barcodeGenerationDAO.checkBookedEntry(badgeNumber,p4);
				if(checkoutEntry4==null){
					checkoutEntry4=barcodeGenerationDAO.createCheckOutInEntry(badgeNumber,p4);;
				}else{
					flag4=1;
					finalReturnedString+="Successfully returned MOBILE"+p4+"\n";
				}	
				}
			else{
				if(finalString==""){
				finalString+=p4+" ";
				flag=1;
				}
				else{
					finalString+=" and "+p4+" ";
					flag=1;
				}
				}
		}
		
		SendEmail sendEmail= new SendEmail();
		if(flag==0){
			BarcodeEmployee barcodeEmployee=barcodeGenerationDAO.checkBadge(badgeNumber);
			if(barcodeEmployee!=null && checkoutEntry0.getCheckedOutDate()!=null && checkoutEntry1.getCheckedOutDate()!=null && checkoutEntry2.getCheckedOutDate()!=null && checkoutEntry3.getCheckedOutDate()!=null && checkoutEntry4.getCheckedOutDate()!=null){
				sendEmail.sendCheckoutInMail5(barcodeEmployee.getEmailId(), barcodeEmployee.getfName(), barcodeEmployee.getlName(),p0,p1,p2,p3,p4);
				out1.println("5");
			}
			else if(barcodeEmployee!=null && checkoutEntry0.getCheckedOutDate()!=null && checkoutEntry1.getCheckedOutDate()!=null && checkoutEntry2.getCheckedOutDate()!=null && checkoutEntry3.getCheckedOutDate()!=null){
				sendEmail.sendCheckoutInMail4(barcodeEmployee.getEmailId(), barcodeEmployee.getfName(), barcodeEmployee.getlName(),p0,p1,p2,p3,finalReturnedString);
				out1.println("4");
			}
			else if(barcodeEmployee!=null && checkoutEntry0.getCheckedOutDate()!=null && checkoutEntry1.getCheckedOutDate()!=null && checkoutEntry2.getCheckedOutDate()!=null){
				sendEmail.sendCheckoutInMail3(barcodeEmployee.getEmailId(), barcodeEmployee.getfName(), barcodeEmployee.getlName(),p0,p1,p2,finalReturnedString);
				out1.println("3");
			}
			else if(barcodeEmployee!=null && checkoutEntry0.getCheckedOutDate()!=null && checkoutEntry1.getCheckedOutDate()!=null){
				sendEmail.sendCheckoutInMail2(barcodeEmployee.getEmailId(), barcodeEmployee.getfName(), barcodeEmployee.getlName(),p0,p1,finalReturnedString);
				out1.println("2");
			}
			else if(barcodeEmployee!=null && checkoutEntry0.getCheckedOutDate()!=null){
				sendEmail.sendCheckoutInMail1(barcodeEmployee.getEmailId(), barcodeEmployee.getfName(), barcodeEmployee.getlName(),p0,finalReturnedString);
				out1.println("1");
			}
			else
				out1.println("false");
		}
		else{
			finalString=finalString+"does not exist";
			out1.println(finalString);
		}
	
	}
	
}
