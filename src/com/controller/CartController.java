package com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.Cart;
import com.bean.Product;
import com.dao.CartDao;
import com.dao.ProductDao;

@WebServlet("/CartController")
public class CartController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int qty = Integer.parseInt(request.getParameter("qty"));
		int uid = Integer.parseInt(request.getParameter("uid"));
		int pid = Integer.parseInt(request.getParameter("pid"));
		
		Product p = ProductDao.viewProductById(pid);
		int tprice = qty * p.getPprice();
		
		Cart c = new Cart();
		c.setQty(qty);
		c.setTprice(tprice);
		c.setUid(uid);
		c.setPid(pid);
		CartDao.updateCartQty(c);
		response.sendRedirect("userCart.jsp");
	}

}
