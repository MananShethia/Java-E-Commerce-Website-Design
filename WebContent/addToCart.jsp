<%@page import="com.bean.Cart"%>
<%@page import="com.dao.CartDao"%>
<%@page import="com.dao.ProductDao"%>
<%@page import="com.bean.Product"%>
<%
	int uid = Integer.parseInt(request.getParameter("uid"));
	int pid = Integer.parseInt(request.getParameter("pid"));
	/* Product p = ProductDao.viewProductById(pid); */
	Cart c = new Cart();
	c.setUid(uid);
	c.setPid(pid);
	CartDao.addToCart(c);
	response.sendRedirect("userCart.jsp");
%>