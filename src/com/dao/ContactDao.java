package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.bean.Contact;
import com.util.ProjectUtil;

public class ContactDao {
	public static void insertContact(Contact c)
	{
		try {
			Connection conn = ProjectUtil.createConnection();
			String sql ="insert into contact(name,subject,email,phone,message) values(?,?,?,?,?)";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, c.getName());
			pst.setString(2, c.getSubject());
			pst.setString(3, c.getEmail());
			pst.setLong(4, c.getPhone());
			pst.setString(5, c.getMessage());
			pst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static List<Contact> getAllContact()
	{
		List<Contact> list = new ArrayList<Contact>();
		try {
			Connection conn = ProjectUtil.createConnection();
			String sql ="select * from contact order by id desc limit 2";
			PreparedStatement pst = conn.prepareStatement(sql);
			ResultSet res = pst.executeQuery();
			while(res.next())
			{
				Contact c = new Contact();
				c.setId(res.getInt("id"));
				c.setName(res.getString("name"));
				c.setSubject(res.getString("subject"));
				c.setEmail(res.getString("email"));
				c.setPhone(res.getLong("phone"));
				c.setMessage(res.getString("message"));
				list.add(c);
			}
		} catch (Exception e) {
			
		}
		return list;
	}
}
