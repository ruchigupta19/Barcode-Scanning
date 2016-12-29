/*this checks weather the barcode scanned by the user is correct and is presented in the system. 
 * 1) If it is incorrect, "Cannot find you in the system :(" will be displayed for 7 seconds,all 
 * the input boxes will be disabled and the page will be refreshed.
 * 2) If it is correct, timer() function will be called which will start a 60 seconds timer and 
 * will enable the next textbox to take an input for number of devices to be scanned. */

var xmlHttp;
xmlHttp=new XMLHttpRequest();


function chkUserinDatabase(){

	if (xmlHttp == null){
		alert("Your browser does not support AJAX!");
		return;
	}

	var cardNoOfUser = document.getElementById("barcodeEmp").value; 

	xmlHttp.onreadystatechange = function stateChanged(){
		if(xmlHttp.readyState == 4 && xmlHttp.status == 200){
			var returnedValue=xmlHttp.responseText;
			if(returnedValue.trim()=="false"){
				document.getElementById("barcodeEmp").disabled = true;
				document.getElementById("numberOfDevices").disabled = true;
				document.getElementById('chkuser').style.display = "block";
				document.getElementById('chkuser').innerHTML ="Cannot find you in the system :(";
				setTimeout(function(){
					location.reload();
				},7000);
			}
			if(returnedValue.trim()=="true"){
				document.getElementById("numberOfDevices").disabled = false;
				timer();
			}
		}
	};

	var query= "cardNoOfUser=" + cardNoOfUser;
	xmlHttp.open("POST","checkEmployeeInDb.htm",true);
	xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xmlHttp.send(query);
	return null;
}

var timing;
var i=60;
function timer(){
	document.getElementById("barcodeEmp").disabled = true;
	document.getElementById('mycounter').innerHTML="";
	document.getElementById('timedText').style.display = "block";
	document.getElementById('mycounter').innerHTML = i;
	i--;
	if(i<0){
		document.getElementById('mycounter').innerHTML="Session Timed out!Start Again";
		if(document.getElementById('outInBtn')){
			document.getElementById('outInBtn').disabled = "true";
		}
		setTimeout(function(){
			location.reload();
		},3000);
	}
	else{
		timing = setTimeout(timer, 1000);
	}
}

function devices(){

	var numberOfDevices = document.getElementById('numberOfDevices').value;
	if(numberOfDevices<=0 || numberOfDevices>5){
		setTimeout(function(){
			document.getElementById('warning').style.color="black";
			document.getElementById('warning').innerHTML="Note : you can scan only upto 5 devices in a go";
		},3000);
		document.getElementById('numberOfDevices').value = "";
		document.getElementById('warning').style.color="red";
		document.getElementById('warning').innerHTML="Number of devices should be between 1-5";
		return;
	}
	else{

		clearTimeout(timing);	
		document.getElementById('timedText').style.display = "none";
		document.getElementById('mycounter').style.display = "none";
		document.getElementById('ScanningText').style.display = "block";
		document.getElementById('numberOfDevices').disabled="true";
		for(var a=0;a<numberOfDevices;a++){
			var input = document.createElement('input');
			input.setAttribute("type","number");
			input.setAttribute("value","");
			input.setAttribute("id",a);
			input.onchange = function(){
				checkDevices(this.value);
			}
			input.setAttribute("class","form-control spaces");
			input.placeholder="scan your devices here";
			input.name=a;
			var form=document.getElementById('scanner');
			form.appendChild(input);
		}
		var button = document.createElement('input');
		button.setAttribute("type","button");
		button.setAttribute("value","Checkout/Checkin Devices");
		button.setAttribute("onclick","checkoutDevices()");
		button.setAttribute("id","outInBtn");
		button.setAttribute("class","btn btn-primary col-md-offset-4");
		var form=document.getElementById('scanner');
		form.appendChild(button);
	}
}

function checkDevices(deviceNumber){ //this function will check if the device is available in the system as soon as you scan the device
	if (xmlHttp == null){
		alert("Your browser does not support AJAX!");
		return;
	}

	xmlHttp.onreadystatechange = function stateChanged(){
		if(xmlHttp.readyState == 4 && xmlHttp.status == 200){
			var returnedValue=xmlHttp.responseText;
			if(returnedValue.trim()=="false"){
				document.getElementById('BCCheckoutResponse').style.display = "block";
				document.getElementById('BCCheckoutResponse').innerHTML ="Cannot find "+deviceNumber+" in the system";
				setTimeout(function(){
					document.getElementById('BCCheckoutResponse').innerHTML ="";
				},3000);
				return;
			}
		}
	};

	var query= "deviceNumber=" + deviceNumber;
	xmlHttp.open("POST","checkMobileInDb.htm",true);
	xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xmlHttp.send(query);
	return null;
}

function checkoutDevices(){
	if (xmlHttp == null){
		alert("Your browser does not support AJAX!");
		return;
	}
	var badgeNumber=document.getElementById("barcodeEmp").value; 
	var numberOfDevices=document.getElementById("numberOfDevices").value; 
	var names=[5];
	for(var t=0;t<numberOfDevices;t++){
		names[t]=document.getElementById(t).value;
	}
	
	var sorted_arr = names.slice().sort(); 
	for (var y = 0; y < names.length - 1; y++) {
		if (sorted_arr[y + 1] == sorted_arr[y]) {
			document.getElementById("BCCheckoutResponse").innerHTML="please check duplicate entries of devices";
			setTimeout(function(){
				document.getElementById("BCCheckoutResponse").innerHTML=" "
			},4000);
			return;
			
		}
	}
	
	xmlHttp.onreadystatechange = function stateChanged(){
		if(xmlHttp.readyState == 4 && xmlHttp.status == 200){
			var returnedValue=xmlHttp.responseText;
			document.getElementById('BCCheckoutResponse').style.display = "block";
			if(returnedValue.trim()=="5"){
				document.getElementById("BCCheckoutResponse").innerHTML="You successfully checked out/in 5 devices.Check your email for details";
				setTimeout(function(){
					location.reload();
				},10000);
			}
			else if(returnedValue.trim()=="4"){
				document.getElementById("BCCheckoutResponse").innerHTML="You successfully checked out/in 4 devices.Check your email for details";
				setTimeout(function(){
					location.reload();
				},10000);
			}
			else if(returnedValue.trim()=="3"){
				document.getElementById("BCCheckoutResponse").innerHTML="You successfully checked out/in 3 devices.Check your email for details";
				setTimeout(function(){
					location.reload();
				},10000);
			}
			else if(returnedValue.trim()=="2"){
				document.getElementById("BCCheckoutResponse").innerHTML="You successfully checked out/in 2 devices.Check your email for details";
				setTimeout(function(){
					location.reload();
				},10000);
			}
			else if(returnedValue.trim()=="1"){
				document.getElementById("BCCheckoutResponse").innerHTML="You successfully checked out/in 1 device.Check your email for details";
				setTimeout(function(){
					location.reload();
				},10000);
			}
			else if(returnedValue.trim()=="false"){
				document.getElementById("BCCheckoutResponse").innerHTML="AWW!Snap";
				setTimeout(function(){
					location.reload();
				},10000);
		    }
			else{
				document.getElementById("BCCheckoutResponse").innerHTML=returnedValue.trim();
			}
		}
	};
	
	var query= "badgeNumber=" +badgeNumber+"&numberOfDevices=" +numberOfDevices+"&p0=" +names[0]+"&p1="+names[1]+"&p2="+names[2]+"&p3="+names[3]+"&p4="+names[4];
	xmlHttp.open("POST","scanning.htm",true);
	xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xmlHttp.send(query);
	return null;
}
