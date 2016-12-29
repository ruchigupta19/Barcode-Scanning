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
    
      <div id="checkout">
	  <h1 class="text-center">Scan your Barcode</h1><br>
      <form action="scanning.htm" method="post" class="col-md-6 col-md-offset-3" id="scanner">	
      	<input type="number" class="form-control" placeholder="Read your barcode here" name="barcodeEmp" id="barcodeEmp" onchange="chkUserinDatabase()"/>
      	<div id="chkuser" class="displyNone textScan text-center"></div>
      	<div id="timedText" class="text-center "><h3>You have 60 seconds to move on</h3></div>
      	<div id="mycounter" class="text-center textScan"></div>
      	<input type="number" class="form-control" placeholder="Number of Devices to checkout/checkin" name="numberOfDevices" id="numberOfDevices" min="1" max="5" onchange="devices()" disabled/>
      	<h5 class="text-center" id="warning">Note:you can scan only upto 5 devices in a go</h5>
      	<p class="text-center textScan displyNone" id="ScanningText">Start Scanning Devices</p>
      	
	  </form>
	  <br><br><div id="BCCheckoutResponse" class="text-center textScan col-md-6 col-md-offset-3"></div>
    </div>
    

</div>
</body>
</html>
