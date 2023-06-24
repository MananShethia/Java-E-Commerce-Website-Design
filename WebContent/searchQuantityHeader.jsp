<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="com.util.ProjectUtil"%>
<%@page import="java.sql.Connection"%>
<%
	int uid = Integer.parseInt(request.getParameter("uid"));
	int pid = Integer.parseInt(request.getParameter("pid"));
	
	try{
		Connection conn = ProjectUtil.createConnection();
		String sql = "select * from cart where uid=? and pid=?";
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setInt(1, uid);
		pst.setInt(2, pid);
		ResultSet res = pst.executeQuery();
		out.println(res.getString(5));
	}catch(Exception e){
		out.println(e);
	}
%>