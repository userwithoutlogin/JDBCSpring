/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jdbcspring;

import com.mycompany.jdbcspring.dao.ContactDao;
import com.mycompany.jdbcspring.entity.Contact;
import com.mycompany.jdbcspring.entity.Phone;
import java.util.GregorianCalendar;
import java.util.List;
import org.springframework.context.support.GenericXmlApplicationContext;

/**
 *
 * @author root
 */
public class Main {
    public static void main(String[] args) {
       GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
       ctx.load("classpath:META-INF.spring/root.xml");
       ctx.refresh();
       
        ContactDao contactDao = ctx.getBean("contactDao",ContactDao.class);
        List<Contact> contacts = contactDao.findAll();
        List<Contact> contacts1 = contactDao.findAllWithPhones();
        List<Contact> contacts2 = contactDao.findByFirstName("Bob");
        String name = contactDao.findFirstNameById(1L);
        String lastN = contactDao.findLastNameById(1L);
        contactDao.delete(1L);
        List<Contact> contacts3 = contactDao.findAllWithPhones();
        contacts2.get(0).setFirstName("Tom");
        contactDao.update(contacts2.get(0));
        List<Contact> contacts4 = contactDao.findAll();
        
        Contact c = new Contact("John","Dow",new GregorianCalendar(1989, 3, 21).getTime());
        Phone p1 = new Phone("Mobile","89124113",c);
        Phone p2 = new Phone("Home","13413",c);
        Phone p3 = new Phone("Mobile","89124341",c);
        c.getPhones().add(p1);
        c.getPhones().add(p2);
        c.getPhones().add(p3);
        contactDao.insert(c);
        
         List<Contact> contacts5 = contactDao.findAllWithPhones();
    }
}
