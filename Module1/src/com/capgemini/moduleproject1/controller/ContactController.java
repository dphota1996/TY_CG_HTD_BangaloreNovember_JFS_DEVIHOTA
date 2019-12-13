package com.capgemini.moduleproject1.controller;

import java.util.List;
import java.util.Scanner;



import com.capgemini.moduleproject1.dao.ContactDao;
import com.capgemini.moduleproject1.dto.Contact;
import com.capgemini.moduleproject1.factory.Factory;

public class ContactController {
	public static void main(String[] args) {
		Scanner sc= new Scanner(System.in);
		ContactDao dao= Factory.objectContactDaoImpl();
		while(true) {
			System.out.println("Enter option to perform operations");
			
			System.out.println("2.Delete contact\n");
			System.out.println("3.update contact\n");
			System.out.println("view all contacts\n");
			System.out.println("5.Go back");
			
			int choice=sc.nextInt();
			switch(choice) {
			case 1:
				System.out.println("Enter the contact details to insert");
				Contact con = new Contact();
				System.out.println("Enter the name ");
				con.setName(sc.next());
				System.out.println("enter the phone number");
				con.setNumber(sc.next());
				System.out.println("enter the group name");
				con.setGroupname(sc.next());
				if(dao.insertContact(con)) {
					System.out.println("contact inserted");
				} else {
					System.out.println("contact not inserted");
				}
				break;
			case 2:
				System.out.println("enter contact name to delete");
				String name=sc.next();
				if(dao.deleteContact(name)) {
					System.out.println("contact deleted");
				} else {
					System.out.println("contact not found");
				}
				break;
			case 3:
				System.out.println("enter the contact name to update");
				String name1=sc.next();
				Contact con2=dao.viewContact(name1);
				if(con2!=null) {
					System.out.println("enter the new number to update ");
					String num=sc.next();
					if(dao.updateContact(name1, num)) {
						System.out.println("contact updated");
					} else {
						System.out.println("contact not updated");
					} 
				} else {
					System.out.println("contact not found");
				}
				break;
			case 4:
				List<Contact> l1= dao.viewAllContacts();
				if(l1!=null) {
					for (Contact contact : l1) {
						System.out.println(contact);
					}
				} else {
					System.out.println("no data");
				}
				break;
			case 5:
				PhoneMain.main(args);
				break;
			default:
				System.out.println("enter valid option");
				break;
			}
		}

	}

}