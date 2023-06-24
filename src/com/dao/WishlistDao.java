package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.bean.Wishlist;
import com.util.ProjectUtil;

public class WishlistDao {
	
	public static void addToWishlist(Wishlist w)
	{
		try {
			Connection conn = ProjectUtil.createConnection();
			String sql = "insert into wishlist(uid,pid) values(?,?)";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, w.getUid());
			pst.setInt(2, w.getPid());
			pst.executeUpdate();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static List<Wishlist> getWishlistByUser(int uid)
	{
		List<Wishlist> list = new ArrayList<Wishlist>();
		try {
			Connection conn = ProjectUtil.createConnection();
			String sql = "select * from wishlist where uid=?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, uid);
			ResultSet res = pst.executeQuery();
			while(res.next())
			{
				Wishlist w = new Wishlist();
				w.setId(res.getInt("id"));
				w.setUid(res.getInt("uid"));
				w.setPid(res.getInt("pid"));
				list.add(w);
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static boolean checkWishlist(int uid, int pid)
	{
		boolean flag = false;
		try {
			Connection conn = ProjectUtil.createConnection();
			String sql = "select * from wishlist where uid=? and pid=?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, uid);
			pst.setInt(2, pid);
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
	
	public static void removeFromWishlist(Wishlist w)
	{
		try {
			Connection conn = ProjectUtil.createConnection();
			String sql = "delete from wishlist where uid=? and pid=?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, w.getUid());
			pst.setInt(2, w.getPid());
			pst.executeUpdate();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
