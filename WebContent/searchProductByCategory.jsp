<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="com.util.ProjectUtil"%>
<%@page import="java.sql.Connection"%>
<%
	String category = request.getParameter("val");
	if(category == null || category.trim().equals(""))
	{
		out.println("Please Enter Your Item To Search");
	}
	else
	{
		try{
			Connection conn = ProjectUtil.createConnection();
			String sql = "select * from product where pcategory like '%"+category+"%'";
			PreparedStatement pst = conn.prepareStatement(sql);
			ResultSet res = pst.executeQuery();
			if(!res.isBeforeFirst())
			{
				out.println("<p>No Record Found</p>");
			}
			else
			{
				out.println("<table border='1' cellpadding='2' width='100%'>");
				out.println("<tr> <th>Pr. ID</th> <th>Pr. NAme</th> <th>Pr. Category</th> <th>Pr. Price</th> <th>Pr. Size</th> </tr>");
				while(res.next())
				{
					out.println("<tr> <td>"+res.getString(1)+"</td> <td>"+res.getString(3)+"</td> <td>"+res.getString(2)+"</td> <td>"+res.getString(4)+"</td> <td>"+res.getString(6)+"</td>");
				}
			}
		}catch(Exception e){
			out.println(e);
		}
	}
%>