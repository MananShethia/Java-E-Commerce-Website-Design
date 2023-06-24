package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.bean.User;
import com.util.ProjectUtil;

public class UserDao {
	public static void signupUser(User u)
	{
		try {
			Connection conn = ProjectUtil.createConnection();
			String sql = "insert into user(fname,lname,email,phone,password,cpassword,address,usertype) values(?,?,?,?,?,?,?,?)";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, u.getFname());
			pst.setString(2, u.getLname());
			pst.setString(3, u.getEmail());
			pst.setString(4, u.getPhone());
			pst.setString(5, u.getPassword());
			pst.setString(6, u.getCpassword());
			pst.setString(7, u.getAddress());
			pst.setString(8, u.getUsertype());
			pst.executeUpdate();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static boolean checkEmail(String email)
	{
		boolean flag = false;
		try {
			Connection conn = ProjectUtil.createConnection();
			String sql = "select * from user where email=?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, email);
			ResultSet res = pst.executeQuery();
			if(res.next())
			{
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	public static User checkLogin(String email, String password)
	{
		User u = null;
		try {
			Connection conn = ProjectUtil.createConnection();
			String sql = "select * from user where email=? and password=?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, email);
			pst.setString(2, password);
			ResultSet res = pst.executeQuery();
			if(res.next())
			{
				u = new User();
				u.setId(res.getInt("id"));
				u.setFname(res.getString("fname"));
				u.setLname(res.getString("lname"));
				u.setEmail(res.getString("email"));
				u.setPhone(res.getString("phone"));
				u.setPassword(res.getString("password"));
				u.setCpassword(res.getString("cpassword"));
				u.setAddress(res.getString("address"));
				u.setUsertype(res.getString("usertype"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return u;
	}
	
	public static boolean checkPassword(int id, String password)
	{
		boolean flag = false;
		try {
			Connection conn = ProjectUtil.createConnection();
			String sql = "select * from user where id=? and password=?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			pst.setString(2, password);
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
	
	public static void changePassword(int id, String password)
	{
		try {
			Connection conn = ProjectUtil.createConnection();
			String sql = "update user set password=?, cpassword=? where id=? ";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, password);
			pst.setString(2, password);
			pst.setInt(3, id);
			pst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void newPassword(String email, String password)
	{
		try {
			Connection conn = ProjectUtil.createConnection();
			String sql = "update user set password=?, cpassword=? where email=? ";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, password);
			pst.setString(2, password);
			pst.setString(3, email);
			pst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void updateStatus(String email)
	{
		String status = "active";
		try {
			Connection conn = ProjectUtil.createConnection();
			String sql="update user set status=? where email=?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, status);
			pst.setString(2, email);
			pst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void editProfile(User u)
	{
		try {
			Connection conn = ProjectUtil.createConnection();
			String sql = "update user set fname=?, lname=?, email=?, phone=?, address=? where id=?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, u.getFname());
			pst.setString(2, u.getLname());
			pst.setString(3, u.getEmail());
			pst.setString(4, u.getPhone());
			pst.setString(5, u.getAddress());
			pst.setInt(6, u.getId());
			pst.executeUpdate();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
