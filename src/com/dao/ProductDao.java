package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.bean.Product;
import com.util.ProjectUtil;

public class ProductDao {
	
	public static void addProduct(Product p)
	{
		try {
			Connection conn = ProjectUtil.createConnection();
			String sql = "insert into product(pcategory,pname,pprice,psize,pcolor,pdesc,pimage,seller) values(?,?,?,?,?,?,?,?)";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, p.getPcategory());
			pst.setString(2, p.getPname());
			pst.setInt(3, p.getPprice());
			pst.setString(4, p.getPsize());
			pst.setString(5, p.getPcolor());
			pst.setString(6, p.getPdesc());
			pst.setString(7, p.getPimage());
			pst.setInt(8, p.getSeller());
			pst.executeUpdate();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static List<Product> viewProductBySeller(int sellerID)
	{
		List<Product> list = new ArrayList<Product>();
		
		try {
			Connection conn = ProjectUtil.createConnection();
			String sql = "select * from product where seller=?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, sellerID);
			ResultSet res = pst.executeQuery();
			while(res.next())
			{
				Product p = new Product();
				p.setPid(res.getInt("pid"));
				p.setPcategory(res.getString("pcategory"));
				p.setPname(res.getString("pname"));
				p.setPprice(res.getInt("pprice"));
				p.setPsize(res.getString("psize"));
				p.setPcolor(res.getString("pcolor"));
				p.setPdesc(res.getString("pdesc"));
				p.setPimage(res.getString("pimage"));
				p.setSeller(res.getInt("seller"));
				list.add(p);
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static Product viewProductById(int pid)
	{
		Product p = null;
		
		try {
			Connection conn = ProjectUtil.createConnection();
			String sql = "select * from product where pid=?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, pid);
			ResultSet res = pst.executeQuery();
			if(res.next())
			{
				p = new Product();
				p.setPid(res.getInt("pid"));
				p.setPcategory(res.getString("pcategory"));
				p.setPname(res.getString("pname"));
				p.setPprice(res.getInt("pprice"));
				p.setPsize(res.getString("psize"));
				p.setPcolor(res.getString("pcolor"));
				p.setPdesc(res.getString("pdesc"));
				p.setPimage(res.getString("pimage"));
				p.setSeller(res.getInt("seller"));
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return p;
	}
	
	public static void editProduct(Product p)
	{
		try {
			Connection conn = ProjectUtil.createConnection();
			String sql = "update product set pname=?,pcategory=?,pprice=?,psize=?,pcolor=?,pdesc=?,pimage=? where pid=?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, p.getPname());
			pst.setString(2, p.getPcategory());
			pst.setInt(3, p.getPprice());
			pst.setString(4, p.getPsize());
			pst.setString(5, p.getPcolor());
			pst.setString(6, p.getPdesc());
			pst.setString(7, p.getPimage());
			pst.setInt(8, p.getPid());
			pst.executeUpdate();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void deleteProduct(int pid)
	{
		try {
			Connection conn = ProjectUtil.createConnection();
			String sql = "delete from product where pid=?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, pid);
			pst.executeUpdate();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static List<Product> getAllProduct()
	{
		List<Product> list = new ArrayList<Product>();
		
		try {
			Connection conn = ProjectUtil.createConnection();
			String sql = "select * from product";
			PreparedStatement pst = conn.prepareStatement(sql);
			ResultSet res = pst.executeQuery();
			while(res.next())
			{
				Product p = new Product();
				p.setPid(res.getInt("pid"));
				p.setPcategory(res.getString("pcategory"));
				p.setPname(res.getString("pname"));
				p.setPprice(res.getInt("pprice"));
				p.setPsize(res.getString("psize"));
				p.setPcolor(res.getString("pcolor"));
				p.setPdesc(res.getString("pdesc"));
				p.setPimage(res.getString("pimage"));
				p.setSeller(res.getInt("seller"));
				list.add(p);
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static List<Product> getAllProductByCategory(String pc)
	{
		List<Product> list = new ArrayList<Product>();
		
		try {
			Connection conn = ProjectUtil.createConnection();
			String sql = "select * from product where pcategory=?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, pc);
			ResultSet res = pst.executeQuery();
			while(res.next())
			{
				Product p = new Product();
				p.setPid(res.getInt("pid"));
				p.setPcategory(res.getString("pcategory"));
				p.setPname(res.getString("pname"));
				p.setPprice(res.getInt("pprice"));
				p.setPsize(res.getString("psize"));
				p.setPcolor(res.getString("pcolor"));
				p.setPdesc(res.getString("pdesc"));
				p.setPimage(res.getString("pimage"));
				p.setSeller(res.getInt("seller"));
				list.add(p);
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
