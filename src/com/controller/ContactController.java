package com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.Contact;
import com.dao.ContactDao;

@WebServlet("/ContactController")
public class ContactController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String action = request.getParameter("action");
		
		if(action.equalsIgnoreCase("insert"));
		{
			Contact c = new Contact();
			c.setName(request.getParameter("name"));
			c.setSubject(request.getParameter("subject"));
			c.setEmail(request.getParameter("email"));
			c.setPhone(Long.parseLong(request.getParameter("phone")));
			c.setMessage(request.getParameter("message"));
			ContactDao.insertContact(c);
			request.setAttribute("feedback", "Response Save Successfully");
			request.getRequestDispatcher("contact.jsp").forward(request, response);
//			response.sendRedirect("contact.jsp");
		}
	}

}
