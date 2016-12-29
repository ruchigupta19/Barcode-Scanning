/* This will be generating barcode for the users*/

var xmlHttp;
xmlHttp=new XMLHttpRequest();

function createBarcodeEmployee(){
	 if (xmlHttp == null){
         alert("Your browser does not support AJAX!");
         return;
     }
     var fname=document.getElementById("fName").value; 
     var lname=document.getElementById("lName").value; 
     var mail=document.getElementById("eMailID").value; 
     var team=document.getElementById("team").value;
     var cardNo=document.getElementById("cardNo").value; 
     xmlHttp.onreadystatechange = function stateChanged(){
		if(xmlHttp.readyState == 4 && xmlHttp.status == 200){
		   var returnedValue=xmlHttp.responseText;
		   if(returnedValue.trim()=="false"){
			   document.getElementById('BCEmployeeResponse').innerHTML ="AWW!Snap";
		   }
		   if(returnedValue.trim()=="true"){
			   document.getElementById('BCEmployeeResponse').innerHTML ="Barcode Generated.Check your email";
			   document.getElementById('barCodeUser').reset();
			   
		   }
		}
     };
     
     var query= "fName=" + fname + "&lName=" + lname+"&mail=" + mail+"&team=" +team+"&cardNo=" +cardNo;
	 xmlHttp.open("POST","addBarcodeForEmployee.htm",true);
	 xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	 xmlHttp.send(query);
	 return null;
}
