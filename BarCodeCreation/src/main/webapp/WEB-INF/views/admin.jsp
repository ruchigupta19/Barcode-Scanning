<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
	<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
	<script src="<%=request.getContextPath()%>/resources/JS/checkUser&StartTimer.js"></script>
	<script src="<%=request.getContextPath()%>/resources/JS/createBarcodeForEmployee.js"></script>
	<script src="<%=request.getContextPath()%>/resources/JS/createBarcodeForMobile.js"></script>
</head>
<body>
<style>
.displyNone{
display:none;
}
#chkuser{
margin-bottom:30px;
}
#ScanningText{
margin-top:30px;
}
.spaces{
margin-bottom:20px;
}
.textScan{
font-size: 30px;
}
#barcodeEmp{
margin-bottom:20px;
}
#timedText{
display:none;
margin-top:10px;
}
</style>

<div class="container">
<ul class="nav nav-pills col-md-offset-3">
<li class="active col-md-4 text-center"><a data-toggle="pill" href="#emp" >Generate Your Barcode</a></li>
<li class="col-md-4 text-center"><a data-toggle="pill"  href="#mob">Generate Barcode for Mobiles</a></li>
</ul>
<div class="tab-content">
	<div id="emp" class="tab-pane fade in active">
	  <h1 class="text-center">Fill out the form for generating your barcode</h1><br>
      <form action="addBarcodeForEmployee.htm" method="post" class="col-md-4 col-md-offset-4" id="barCodeUser">	
		
		<input type="text" class="form-control" placeholder="First Name" name="fName" id="fName"/><br>
    	<input type="text" class="form-control" placeholder="Last Name" name="lName" id="lName"/><br>
    	<input type="email" class="form-control" placeholder="Email ID" name="eMailID" id="eMailID"/><br>
    	<input type="text" class="form-control" placeholder="team" name="team" id="team"/><br>
    	<input type="number" class="form-control" placeholder="Badge Number" name="cardNo" id="cardNo" /><br>
    	<input type="button" class="btn btn-primary col-md-offset-4" value="Generate Barcode" onclick="createBarcodeEmployee()"/>
	    <br><br><div id="BCEmployeeResponse" class="text-center textScan"></div>
	    
	  </form>
    </div>
    
      <div id="mob" class="tab-pane fade ">
	  <h1 class="text-center">Fill out the form for generating barcode for a Mobile device</h1><br>
      <form action="addBarcodeForMobile.htm" method="post" class="col-md-4 col-md-offset-4" id="barCodeMobile">	
		
		<input type="number" class="form-control" placeholder="Mobile Number" name="mobileNumber" id="mobileNumber"/><br>
    	<input type="text" class="form-control" placeholder="Name of Mobile" name="name" id="name"/><br>
    	<input type="text" class="form-control" placeholder="Type of OS" name="typeOfOS" id="typeOfOS"/><br>
    	<input type="text" class="form-control" placeholder="Version of Mobile" name="version" id="version"/><br>
    	<input type="text" class="form-control" placeholder="Is it on Meraki?" name="onMeraki" id="onMeraki" /><br>
    	<input type="button"  class="btn btn-primary col-md-offset-4" value="Generate Barcode" onclick="createBarcodeMobile()"/>
		<br><br><div id="BCMobileResponse" class="text-center textScan"></div>
		
	  </form>
    </div>
    
</div>
</div>
</body>
</html>
