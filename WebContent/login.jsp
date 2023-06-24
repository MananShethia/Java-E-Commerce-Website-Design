<%@page import="java.util.List"%>
<%@page import="com.dao.ContactDao"%>
<%@page import="com.bean.Contact"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="zxx">
<head>
    <title>Eshop Login.</title>	
    <script>
    	var request = new XMLHttpRequest();
    	function checkEmail_login()
    	{
    		/* document.<form_name>.<tag_name>.value; */
    		var email = document.login.email.value;
    		var url = "searchEmail.jsp?val="+email;
    		try {
				request.onreadystatechange = function()
				{	
					if(request.readyState == 4)
					{
						var val = request.responseText;
						//document.getElementById("signup_feedback").innerHTML = val;
						if(val.includes("enter"))
						{
							document.getElementById("checkEmail").style.borderColor = "red";
							document.getElementById("login_feedback").innerHTML = val;
							document.getElementById("login_feedback").style.color = "red";
						}
						if(val.includes("already"))
						{
							document.getElementById("checkEmail").style.borderColor = "green";
							document.getElementById("login_feedback").innerHTML = "";
						}
						if(val.includes("available"))
						{
							document.getElementById("checkEmail").style.borderColor = "red";
							document.getElementById("login_feedback").innerHTML = "Email Not Registered";
							document.getElementById("login_feedback").style.color = "red";
						}
					}
				}
				request.open("GET", url, true);
				request.send();
			} catch (e) {
				alert("Unable To Connect Server");
			}
    	}
    </script>
</head>
<body class="js">
	<%@ include file = "header.jsp" %>
	<!-- Breadcrumbs -->
	<div class="breadcrumbs">
		<div class="container">
			<div class="row">
				<div class="col-12">
					<div class="bread-inner">
						<ul class="bread-list">
							<li><a href="index1.jsp">Home<i class="ti-arrow-right"></i></a></li>
							<li class="active"><a href="login.jsp">Login</a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- End Breadcrumbs -->
  
	<!-- Start Contact -->
	<section id="contact-us" class="contact-us section">
		<div class="container">
				<div class="contact-head">
					<div class="row">
						<div class="col-lg-8 col-12">
							<div class="form-main">
								<div class="title">
									<h3>Log in</h3>
									<h4>
										<%
											if(request.getAttribute("feedback") != null)
											{
												out.println(request.getAttribute("feedback"));
											}
										%>
									</h4>
								</div>
								<form name="login" class="form" method="post" action="UserController">
									<div class="row">
										<div class="col-lg-6 col-12">
											<div class="form-group">
												<label>Your Email<span>*</span></label>
												<input id="checkEmail" name="email" type="email" placeholder="Email" onblur="checkEmail_login();">
												<span id="login_feedback"></span>
											</div>	
										</div>
										<div class="col-lg-6 col-12">
											<div class="form-group">
												<label>Your Password<span>*</span></label>
												<input name="password" type="password" placeholder="Password">
											</div>	
										</div>
										<div class="col-6">
											<div class="form-group button">
												<button type="submit" name="action" value="login" class="btn ">Login</button>
											</div>
										</div>
										<div class="col-6">
											<div class="form-group button">
												<a href="forgotPassword.jsp"><button type="button" name="action" value="forgotpassword" class="btn ">Forgot Password</button></a>
											</div>
										</div>
										<div class="col-6">
											<div class="form-group button">
												<br><br><a href="signup.jsp"><button type="button" name="action" value="signup" class="btn ">Signup</button></a>
												      Create New Account
											</div>
										</div>
									</div>
								</form>
							</div>
						</div>
						<div class="col-lg-4 col-12">
							<div class="single-head">
								<div class="single-info">
									<i class="fa fa-phone"></i>
									<h4 class="title">Call us Now:</h4>
									<ul>
										<li>+123 456-789-1120</li>
										<li>+522 672-452-1120</li>
									</ul>
								</div>
								<div class="single-info">
									<i class="fa fa-envelope-open"></i>
									<h4 class="title">Email:</h4>
									<ul>
										<li><a href="mailto:info@yourwebsite.com">info@yourwebsite.com</a></li>
										<li><a href="mailto:info@yourwebsite.com">support@yourwebsite.com</a></li>
									</ul>
								</div>
								<div class="single-info">
									<i class="fa fa-location-arrow"></i>
									<h4 class="title">Our Address:</h4>
									<ul>
										<li>KA-62/1, Travel Agency, 45 Grand Central Terminal, New York.</li>
									</ul>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
	</section>
	<%-- <%
		List<Contact> list = ContactDao.getAllContact();
	%>
	<h1>Recent Messages</h1>
	<%
		for(Contact c: list)
		{
	%>
		<b>Name: </b><%=c.getName() %><br>
		<b>Subject: </b><%=c.getSubject() %><br>
		<b>Message: </b><%=c.getMessage() %><br>
		<hr>
	<%
		}
	%> --%>
	<!--/ End Contact -->
	
	<!-- Map Section -->
	<div class="map-section">
		<div id="myMap"></div>
	</div>
	<!--/ End Map Section -->
	
	<!-- Start Shop Newsletter  -->
	<section class="shop-newsletter section">
		<div class="container">
			<div class="inner-top">
				<div class="row">
					<div class="col-lg-8 offset-lg-2 col-12">
						<!-- Start Newsletter Inner -->
						<div class="inner">
							<h4>Newsletter</h4>
							<p> Subscribe to our newsletter and get <span>10%</span> off your first purchase</p>
							<form action="mail/mail.php" method="get" target="_blank" class="newsletter-inner">
								<input name="EMAIL" placeholder="Your email address" required="" type="email">
								<button class="btn">Subscribe</button>
							</form>
						</div>
						<!-- End Newsletter Inner -->
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- End Shop Newsletter -->
	
	<%@ include file = "footer.jsp" %>
</body>
</html>