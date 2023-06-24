<%@page import="java.util.List"%>
<%@page import="com.dao.ContactDao"%>
<%@page import="com.bean.Contact"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="zxx">
<head>
    <title>Eshop Contact</title>
    <script>
    	var request = new XMLHttpRequest();
    	function checkProductByCategory()
    	{
    		/* document.<form_name>.<tag_name>.value; */
    		var product = document.searchProduct.pcatgory.value;
    		var url = "searchProductByCategory.jsp?val="+product;
    		try {
				request.onreadystatechange = function()
				{
					if(request.readyState == 4)
					{
						var val = request.responseText;
						document.getElementById("searchProduct_feedback").innerHTML = val;
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
							<li class="active"><a href="blog-single.jsp">Search</a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
	
 	<!-- Start Shop Newsletter  -->
	<section class="shop-newsletter section">
		<div class="container">
			<div class="inner-top">
				<div class="row">
					<div class="col-lg-8 offset-lg-2 col-12">
						<!-- Start Newsletter Inner -->
						<div class="inner">
							<h4>Search Your Like</h4>
							<!-- <p> Subscribe to our newsletter and get <span>10%</span> off your first purchase</p> -->
							<form name="searchProduct" action="" method="post" target="_blank" class="newsletter-inner">
								<input name="pcatgory" placeholder="Search Item" required="" type="search" onkeyup="checkProductByCategory();">
								<button class="btn"><i class="fa fa-search fa-lg" aria-hidden="true"></i></button>
							</form>
							<br>
							<span id="searchProduct_feedback"></span>
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