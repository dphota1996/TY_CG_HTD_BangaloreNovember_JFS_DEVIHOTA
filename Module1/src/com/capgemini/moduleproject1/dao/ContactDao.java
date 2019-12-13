package com.capgemini.moduleproject1.dao;

import java.util.List;

import com.capgemini.moduleproject1.bean.Contact;

public interface ContactDao {
	
public boolean addContact(Contact nameObj);
public boolean deleteContact(int cNumber);
public Contact    searchContact(int cNumber);
public List<Contact>getAllContact();
public boolean modifyContact(int cNumber,String cName);
	

}
