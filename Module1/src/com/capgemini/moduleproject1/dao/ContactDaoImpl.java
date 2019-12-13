package com.capgemini.moduleproject1.dao;


import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.capgemini.moduleproject1.dto.Contact;


public class ContactDaoImpl implements ContactDao {

	FileReader reader;
	Properties prop;
	Contact con;


	public ContactDaoImpl() {
		try {
			reader= new FileReader("contact.properties");
			prop= new Properties();
			prop.load(reader);
			Class.forName(prop.getProperty("driver"));
		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	@Override
	public boolean insertContact(Contact con) {

		try(Connection conn=DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("user"), 
				prop.getProperty("pass"));
				PreparedStatement pstmt=conn.prepareStatement(prop.getProperty("insert"))){

			pstmt.setString(1, con.getName());
			pstmt.setString(2, con.getNumber());
			pstmt.setString(3, con.getGroupname());

			int r=pstmt.executeUpdate();
			if(r>0) {
				return true;
			} else {
				return false;
			}

		} catch(Exception e) {
			System.out.println("duplicate name not allowed");
		}

		return false;
	}

	@Override
	public boolean deleteContact(String name) {
		try(Connection conn=DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("user"), 
				prop.getProperty("pass"));
				PreparedStatement pstmt=conn.prepareStatement(prop.getProperty("delete"))){

			pstmt.setString(1,name);

			int r=pstmt.executeUpdate();
			if(r>0) {
				return true;
			} else {
				return false;
			}

		} catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateContact(String name,String number) {
		try(Connection conn=DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("user"), 
				prop.getProperty("pass"));
				PreparedStatement pstmt=conn.prepareStatement(prop.getProperty("modify"))){
			pstmt.setString(1,number);
			pstmt.setString(2,name);
			int r=pstmt.executeUpdate();
			if(r>0) {
				return true;
			} else {
				return false;
			}

		} catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Contact> viewAllContacts() {
		List<Contact> l1= new ArrayList<Contact>();
		try(Connection conn=DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("user"), 
				prop.getProperty("pass"));
				Statement stmt=conn.createStatement()){
			ResultSet rs=stmt.executeQuery(prop.getProperty("viewall"));
			while(rs.next()) {
				Contact con=new Contact();
				con.setName(rs.getString(1));
				con.setNumber(rs.getString(2));
				con.setGroupname(rs.getString(3));
				l1.add(con);
			} 
			return l1;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Contact viewContact(String name) {
		try(Connection conn=DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("user"), 
				prop.getProperty("pass"));
				PreparedStatement pstmt=conn.prepareStatement(prop.getProperty("view"))){
			pstmt.setString(1,name);
			ResultSet rs= pstmt.executeQuery();
			if(rs.next()) {
			Contact con=new Contact();
			con.setNumber(rs.getString("number"));
			con.setGroupname(rs.getString("groupname"));
			return con;
			} else {
				return null;
			}

		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}