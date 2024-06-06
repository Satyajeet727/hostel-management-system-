package com.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.entities.*;

public class UserDAO {

	private Connection con;

	public UserDAO(Connection con) {
		super();
		this.con = con;
	}

	
	public boolean userRegister(user us)
	{
		boolean f=false;
		try {
			String qu="insert into user(name,branch,email,password) values(?,?,?,?)";
			PreparedStatement ps=con.prepareStatement(qu);
			ps.setString(1,us.getName());
			ps.setString(2,us.getBranch());
			ps.setString(3,us.getEmail());
			ps.setString(4,us.getPassword());
			
			ps.executeUpdate();
			f=true;  
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return f;
	}
	
	
	public user getLogin(String em,String ps)
	{
		user us=null;
		try {
			String qu="select * from user where email=? and password=?";
			
			PreparedStatement pst=con.prepareStatement(qu);
			pst.setString(1, em);
			pst.setString(2, ps);
			
			ResultSet rs=pst.executeQuery();
			
			if(rs.next())
			{
				us=new user(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5));
				
				
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return us;
	}
	
	
	
	
	
}