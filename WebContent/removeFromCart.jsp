<%@page import="com.dao.CartDao"%>
<%@page import="com.bean.Cart"%>
<%
	int uid = Integer.parseInt(request.getParameter("uid"));
	int pid = Integer.parseInt(request.getParameter("pid"));
	Cart c = new Cart();
	c.setUid(uid);
	c.setPid(pid);
	CartDao.removeFromCart(c);
	response.sendRedirect("userCart.jsp");
%>