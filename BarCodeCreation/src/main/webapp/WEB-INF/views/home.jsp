<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Home</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/resources/CSS/logs.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script
	src="<%=request.getContextPath()%>/resources/JS/checkUser&StartTimer.js"></script>
<script
	src="<%=request.getContextPath()%>/resources/JS/createBarcodeForEmployee.js"></script>
<script
	src="<%=request.getContextPath()%>/resources/JS/createBarcodeForMobile.js"></script>
<script
	src="<%=request.getContextPath()%>/resources/JS/background.cycle.js"></script>
<script
	src="<%=request.getContextPath()%>/resources/JS/modernizr.custom.js"></script>
</head>
<script>
function hiding()
{
	
	document.getElementById('userN').style.display = 'none';
	document.getElementById('pwUsers').style.display = 'none';
	document.getElementById("hideIt").style.display = 'none';
}


            $(document).ready(function() {
                $("body").backgroundCycle({
                    imageUrls: [
                        '<%=request.getContextPath()%>/resources/IMG/bg2.jpg',
                        '<%=request.getContextPath()%>/resources/IMG/bg3.jpg',
                        '<%=request.getContextPath()%>/resources/IMG/bg1.jpg' ],
											fadeSpeed : 2000,
											duration : 5000,
											backgroundSize : SCALING_MODE_COVER
										});
					});

	function chkUser() {
		var nameUser = document.getElementById("nameOfUser").value;
		var pwUser = document.getElementById("pwOfUser").value;

		if (nameUser == "") {
			document.getElementById('userN').style.display = 'block';
			document.getElementById("userN").innerHTML = "You cannot leave this Field blank!!";
			return false;
		}

		if (pwUser == '') {
			document.getElementById('pwUsers').style.display = 'block';
			document.getElementById("pwUsers").innerHTML = "You cannot leave this Field blank!!";
			return false;
		}

		re = /^\w+$/;
		if (!re.test(nameUser)) {
			document.getElementById('userN').style.display = 'block';
			document.getElementById("userN").innerHTML = "Username must contain only letters, numbers and underscores!";
			return false;
		}

		p = /^[a-zA-Z0-9]*$/;
		if (!p.test(pwUser)) {
			document.getElementById('pwUsers').style.display = 'block';
			document.getElementById("pwUsers").innerHTML = "Password must contain only letters and numbers!!";
			return false;
		}
		

 		xmlHttp.onreadystatechange = function stateChanged() {
			if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
				var returnedValue = xmlHttp.responseText;
				if (returnedValue.trim() == "notFound") {
					document.getElementById('hideIt').style.display = 'block';
					document.getElementById("hideIt").innerHTML = "Id and password could not be found";
				} else if (returnedValue.trim() == "adminIsHere") {
					location.href = "/BarCodeCreation/adminIsHere.htm";
				} else if (returnedValue.trim() == "userIsHere") {
					location.href = "/BarCodeCreation/userIsHere.htm";
				} else {
					document.getElementById('hideIt').style.display = 'block';
					document.getElementById("hideIt").innerHTML = "Your Role could not be found";
				}
			}
		}; 
		
		  var query= "Username=" + nameUser + "&password=" + pwUser;
		  xmlHttp.open("POST","login.htm",true);
		  xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
		  xmlHttp.send(query);
		
		  return null;
	}
</script>
<body>
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-3 col-md-offset-8 ">
				<div class="panel panel-default" id="mainBox">
					<div class="panel-heading text-center">
						<strong class="">LOGIN</strong>
					</div>
					<div class="panel-body">
						<form class="form-horizontal" action="login.htm" role="form" method="post">
							<div class="form-group">
								<label for="inputEmail3" class="col-sm-3 control-label">User
									Id</label>
								<div class="col-sm-9">
									<input type="text" class="form-control" onfocus="hiding()"
										placeholder="User Id" name="Username" id="nameOfUser"
										required="" />
									<div id="userN"></div>
								</div>
							</div>
							<div class="form-group">
								<label for="inputPassword3" class="col-sm-3 control-label">Password</label>
								<div class="col-sm-9">
									<input type="password" class="form-control" name="Password"
										id="pwOfUser" onfocus="hiding()" placeholder="Password"
										required="" />
									<div id="pwUsers"></div>
								</div>
							</div>
							<div id="hideIt"></div>
							<div class="form-group last">
								<div class="col-sm-offset-3 col-sm-9">
								<input type="button" value="Sign In" onclick="chkUser()" class="btn btn-success btn-sm"/>
									<button type="reset"
										class="col-sm-offset-5 btn btn-default btn-sm">Reset</button>
								</div>
							</div>
							<div>
								<p class="col-sm-offset-3 col-sm-9" id="invalidCreds"></p>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>