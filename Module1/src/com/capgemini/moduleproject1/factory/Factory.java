package com.capgemini.moduleproject1.factory;

import com.capgemini.moduleproject1.dao.ContactDao;
import com.capgemini.moduleproject1.dao.ContactDaoImpl;

public class Factory {


	public static ContactDao objectCRUDDaoImpl() {
		ContactDao dao= new ContactDaoImpl();
		return dao;
	}

}