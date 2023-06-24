<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="com.util.ProjectUtil"%>
<%@page import="java.sql.Connection"%>
<%
	int quantity = Integer.parseInt(request.getParameter("qty"));
	int uid = Integer.parseInt(request.getParameter("uid"));
	int pid = Integer.parseInt(request.getParameter("pid"));
	int price = Integer.parseInt(request.getParameter("price"));
	int tprice = Integer.parseInt(request.getParameter("tprice"));
	int netPriceChange = (quantity * price) - tprice;
	
	try{
		Connection conn = ProjectUtil.createConnection();
		String sql = "update cart set qty=?, tprice=? where uid=? and pid=?";
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setInt(1, quantity);
		pst.setInt(2, (quantity * price));
		pst.setInt(3, uid);
		pst.setInt(4, pid);
		pst.executeUpdate();
		out.println(netPriceChange);
	}catch(Exception e){
		out.println(e);
	}
%>