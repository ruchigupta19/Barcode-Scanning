/*This will be generating barcode for Mobiles*/

var xmlHttp;
xmlHttp=new XMLHttpRequest();

function createBarcodeMobile(){
	if (xmlHttp == null){
        alert("Your browser does not support AJAX!");
        return;
    }
	 var mobileNumber=document.getElementById("mobileNumber").value; 
     var name=document.getElementById("name").value; 
     var typeOfOS=document.getElementById("typeOfOS").value; 
     var version=document.getElementById("version").value;
     var onMeraki=document.getElementById("onMeraki").value; 
     
	xmlHttp.onreadystatechange = function stateChanged(){
		if(xmlHttp.readyState == 4 && xmlHttp.status == 200){
		   var returnedValue=xmlHttp.responseText;
		   if(returnedValue.trim()=="false"){
			   document.getElementById("BCMobileResponse").innerHTML ="Aww!!Snap";
		   }
		   if(returnedValue.trim()=="true"){
			   document.getElementById('BCMobileResponse').innerHTML ="Barcode Generated";
			   document.getElementById('barCodeMobile').reset();
		   }
		}
     };
     var query= "mobileNumber=" +mobileNumber+"&name=" +name+"&typeOfOS="+typeOfOS+"&version="+version+"&onMeraki="+onMeraki;
	 xmlHttp.open("POST","addBarcodeForMobile.htm",true);
	 xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	 xmlHttp.send(query);
	 return null;
}
