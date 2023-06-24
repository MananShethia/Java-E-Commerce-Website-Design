package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.bean.Cart;
import com.bean.Product;
import com.bean.Wishlist;
import com.util.ProjectUtil;

public class CartDao {
	
	public static void addToCart(Cart c) 
	{	
		Product p = ProductDao.viewProductById(c.getPid());
		c.setPrice(p.getPprice());
		c.setQty(1);
		try {
			Connection conn = ProjectUtil.createConnection();
			String sql = "insert into cart(uid,pid,price,qty,tprice) values (?,?,?,?,?)";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, c.getUid());
			pst.setInt(2, c.getPid());
			pst.setInt(3, c.getPrice());
			pst.setInt(4, c.getQty());
			pst.setInt(5, c.getPrice());
			pst.executeUpdate(); 
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static List<Cart> getCartByUser(int uid)
	{
		String status = "pending";
		List<Cart> list = new ArrayList<Cart>();
		try {
			Connection conn = ProjectUtil.createConnection();
			String sql = "select * from cart where uid=? and status=?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, uid);
			pst.setString(2, status);
			ResultSet res = pst.executeQuery();
			while(res.next())
			{
				Cart c = new Cart();
				c.setId(res.getInt("id"));
				c.setUid(res.getInt("uid"));
				c.setPid(res.getInt("pid"));
				c.setPrice(res.getInt("price"));
				c.setQty(res.getInt("qty"));
				c.setTprice(res.getInt("tprice"));
				list.add(c);
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static boolean checkCart(int uid, int pid)
	{
		String status = "pending";
		boolean flag = false;
		try {
			Connection conn = ProjectUtil.createConnection();
			String sql = "select * from cart where uid=? and pid=? and status=?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, uid);
			pst.setInt(2, pid);
			pst.setString(3, status);
			ResultSet res = pst.executeQuery();
			if(res.next())
			{
				flag = true;
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	public static void removeFromCart(Cart c)
	{
		try {
			Connection conn = ProjectUtil.createConnection();
			String sql = "delete from Cart where uid=? and pid=?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, c.getUid());
			pst.setInt(2, c.getPid());
			pst.executeUpdate();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void updateCartQty(Cart c)
	{
		try {
			Connection conn = ProjectUtil.createConnection();
			String sql = "update cart set qty=?, tprice=? where uid=? and pid=?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, c.getQty());
			pst.setInt(2, c.getTprice());
			pst.setInt(3, c.getUid());
			pst.setInt(4, c.getPid());
			pst.executeUpdate();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void updateCartStatus(int id)
	{
		String status = "completed";
		try {
			Connection conn = ProjectUtil.createConnection();
			String sql = "update cart set status=? where id=?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, status);
			pst.setInt(2, id);
			pst.executeUpdate();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
