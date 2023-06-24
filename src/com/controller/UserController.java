package com.controller;

import java.io.IOException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bean.User;
import com.dao.UserDao;
import com.service.Services;

@WebServlet("/UserController")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		
		if(action.equalsIgnoreCase("signup"))
		{
			boolean flag = UserDao.checkEmail(request.getParameter("email"));
			if(flag == true)
			{
//				System.out.println("Check Email");
				request.setAttribute("feedback", "Email Already Registered");
				request.getRequestDispatcher("signup.jsp").forward(request, response);
			}
			else if(!request.getParameter("password").equals(request.getParameter("cpassword")))
			{
//				System.out.println("Check Password");
				request.setAttribute("feedback", "Confirm Password Doesn't Match");
				request.getRequestDispatcher("signup.jsp").forward(request, response);
			}
			else
			{
//				System.out.println("Create Account");
				User u = new User();
				u.setFname(request.getParameter("fname"));
				u.setLname(request.getParameter("lname"));
				u.setEmail(request.getParameter("email"));
				u.setPhone(request.getParameter("phone"));
				u.setPassword(request.getParameter("password"));
				u.setCpassword(request.getParameter("cpassword"));
				u.setAddress(request.getParameter("address"));
				u.setUsertype(request.getParameter("usertype"));
				UserDao.signupUser(u);
				
				Random t = new Random();
			    int minRange = 1000, maxRange= 9999;
		        int otp = t.nextInt(maxRange - minRange) + minRange;
				Services.sendMail(request.getParameter("email"),otp);
				request.setAttribute("type","signup");
				request.setAttribute("otp",otp);
				request.setAttribute("email",request.getParameter("email"));
				request.getRequestDispatcher("otp.jsp").forward(request, response);
//				response.sendRedirect("login.jsp");
			}
		}
		
		else if(action.equalsIgnoreCase("login"))
		{
			User u = UserDao.checkLogin(request.getParameter("email"), request.getParameter("password"));

			if(u == null)
			{
				request.setAttribute("feedback", "Incorrect Email or Password");
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
			else if(u.getUsertype().equalsIgnoreCase("user"))
			{
				System.out.println(u.getUsertype());
				HttpSession session = request.getSession();
				session.setAttribute("u", u);
				/* System.out.println(session.getId()); */
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}
			else if(u.getUsertype().equalsIgnoreCase("seller"))
			{
				System.out.println(u.getUsertype());
				HttpSession session = request.getSession();
				session.setAttribute("u", u);
				/* System.out.println(session.getId()); */
				request.getRequestDispatcher("sellerIndex.jsp").forward(request, response);
			}
		}
		
		else if(action.equalsIgnoreCase("changepassword"))
		{
			int id = Integer.parseInt(request.getParameter("id"));
			String opassword = request.getParameter("opassword");
			String npassword = request.getParameter("npassword");
			String cnpassword = request.getParameter("cnpassword");
			
			boolean flag = UserDao.checkPassword(id, opassword);
			
			if(flag)
			{
				if(npassword.equals(cnpassword))
				{
					UserDao.changePassword(id, npassword);
					response.sendRedirect("logout.jsp");
				}
				else
				{
					request.setAttribute("feedback", "Your New Password & Confirm Password Doesn't Match");
					request.getRequestDispatcher("changePassword.jsp").forward(request, response);
				}
			}
			else
			{
				request.setAttribute("feedback", "Incorrect Old Password");
				request.getRequestDispatcher("changePassword.jsp").forward(request, response);
			}
		}
		
		else if(action.equalsIgnoreCase("sendotp"))
		{
			String email = request.getParameter("email");
			
			boolean flag = UserDao.checkEmail(email);
			
			if(flag)
			{
				Random t = new Random();
			    int minRange = 1000, maxRange= 9999;
		        int otp = t.nextInt(maxRange - minRange) + minRange;
				Services.sendMail(email,otp);
				request.setAttribute("otp",otp);
				request.setAttribute("email",email);
				request.getRequestDispatcher("otp.jsp").forward(request, response);
			}
			else
			{
				request.setAttribute("feedback", "Email Not Registered");
				request.getRequestDispatcher("forgotPassword.jsp").forward(request, response);
			}
		}
		
		else if(action.equalsIgnoreCase("verifyotp"))
		{
			String type = "";
			int otp1 = Integer.parseInt(request.getParameter("otp1"));
			int otp2 = Integer.parseInt(request.getParameter("otp2"));
			String email = request.getParameter("email");
			
			try {
				type = request.getParameter("type");
				if(type.equals("signup") && otp1 == otp2)
				{
					UserDao.updateStatus(email);
					request.setAttribute("feedback","Your Status Is Now Active. Proced For Login");
					request.getRequestDispatcher("login.jsp").forward(request, response);
				}
			} catch (Exception e) {
				if(otp1 == otp2)
				{
					request.setAttribute("email", email);
					request.getRequestDispatcher("newPassword.jsp").forward(request, response);
				}
				else
				{
					request.setAttribute("type", type);
					request.setAttribute("otp",otp1);
					request.setAttribute("email",email);
					request.setAttribute("feedback", "OTP Doesn't Match");
					request.getRequestDispatcher("otp.jsp").forward(request, response);
				}
			}
			
			
		}
		
		else if(action.equalsIgnoreCase("newpassword"))
		{
			String email = request.getParameter("email");
			String npassword = request.getParameter("npassword");
			String cnpassword = request.getParameter("cnpassword");
			
			if(npassword.equals(cnpassword))
			{
				UserDao.newPassword(email, npassword);
				response.sendRedirect("login.jsp");
			}
			else
			{
				request.setAttribute("email", email);
				request.setAttribute("feedback", "New Password & Confirm Password Not Match");
				request.getRequestDispatcher("newPassword.jsp").forward(request, response);
			}
		}
		
		else if(action.equalsIgnoreCase("editprofile"))
		{
			User u = new User();
			u.setId(Integer.parseInt(request.getParameter("id")));
			u.setFname(request.getParameter("fname"));
			u.setLname(request.getParameter("lname"));
			u.setEmail(request.getParameter("email"));
			u.setPhone(request.getParameter("phone"));
			u.setAddress(request.getParameter("address"));
			UserDao.editProfile(u);
			HttpSession session = request.getSession();
			session.setAttribute("u", u);
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
	}

}
