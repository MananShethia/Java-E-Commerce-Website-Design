package com.controller;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.bean.Product;
import com.dao.ProductDao;

@WebServlet("/ProductController")
@MultipartConfig(fileSizeThreshold = 1024*1024*512, maxFileSize = 1024*1024*512, maxRequestSize = 1024*1024*512)
public class ProductController extends HttpServlet {
	
	private String extractFileName(Part file)
	{
		String cd = file.getHeader("content-disposition");
		String[] items = cd.split(";");
		for(String string: items)
		{
			if(string.trim().startsWith("filename"))
			{
				return string.substring(string.indexOf("=") + 2, string.length() - 1);
			}
		}
		return "";
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		
		if(action.equalsIgnoreCase("addproduct"))
		{
			String savePath = "F:\\java\\MyProject\\WebContent\\product_images";
			File fileSaveDir = new File(savePath);
			if(!fileSaveDir.exists())
			{
				fileSaveDir.mkdir();
			}
			Part file1 = request.getPart("pimage");
			String fileName = extractFileName(file1);
			file1.write(savePath + File.separator + fileName);
			String filePath = savePath + File.separator + fileName;
			
			Product p = new Product();
			p.setPname(request.getParameter("pname"));
			p.setPcategory(request.getParameter("pcategory"));
			p.setPprice(Integer.parseInt(request.getParameter("pprice")));
			p.setPsize(request.getParameter("psize"));
			p.setPcolor(request.getParameter("pcolor"));
			p.setPdesc(request.getParameter("pdesc"));
			p.setPimage(fileName);
			p.setSeller(Integer.parseInt(request.getParameter("seller")));
			ProductDao.addProduct(p);
			
			request.setAttribute("feedback", "Product Added Successfully");
			request.getRequestDispatcher("addProduct.jsp").forward(request, response);
		}
		
		else if(action.equalsIgnoreCase("editproduct"))
		{
//			System.out.println(request.getParameter("pimage"));
			
			
			Product p = new Product();
			p.setPid(Integer.parseInt(request.getParameter("pid")));
			p.setPname(request.getParameter("pname"));
			p.setPcategory(request.getParameter("pcategory"));
			p.setPprice(Integer.parseInt(request.getParameter("pprice")));
			p.setPsize(request.getParameter("psize"));
			p.setPcolor(request.getParameter("pcolor"));
			p.setPdesc(request.getParameter("pdesc"));
			
			try {
				String savePath = "F:\\java\\MyProject\\WebContent\\product_images";
				File fileSaveDir = new File(savePath);
				if(!fileSaveDir.exists())
				{
					fileSaveDir.mkdir();
				}
				
				Part file1 = request.getPart("pimage");
				String fileName = extractFileName(file1);
				file1.write(savePath + File.separator + fileName);
				String filePath = savePath + File.separator + fileName;
				p.setPimage(fileName);
				
				String deleteFile = request.getParameter("old_pimage");
				File deleteFileDir = new File(savePath + File.separator + deleteFile);
				deleteFileDir.delete();
			} catch (Exception e) {
				p.setPimage(request.getParameter("old_pimage"));
			}
			
			ProductDao.editProduct(p);
			response.sendRedirect("viewProduct.jsp");
		}
	}

}
