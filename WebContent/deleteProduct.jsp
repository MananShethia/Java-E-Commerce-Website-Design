<%@page import="com.dao.ProductDao"%>
<%
	ProductDao.deleteProduct(Integer.parseInt(request.getParameter("pid")));
	response.sendRedirect("viewProduct.jsp");
%>