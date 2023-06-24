<%@page import="com.dao.WishlistDao"%>
<%@page import="com.bean.Wishlist"%>
<%
	int uid = Integer.parseInt(request.getParameter("uid"));
	int pid = Integer.parseInt(request.getParameter("pid"));
	Wishlist w = new Wishlist();
	w.setUid(uid);
	w.setPid(pid);
	WishlistDao.addToWishlist(w);
	response.sendRedirect("userWishlist.jsp");
%>