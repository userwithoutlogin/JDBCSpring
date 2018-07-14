/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jdbcspring;

import com.mycompany.jdbcspring.dao.ContactDao;
import com.mycompany.jdbcspring.entity.Contact;
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
        List<Contact> contacts2 = contactDao.findByFirstName("Chris");
        String name = contactDao.findFirstNameById(1L);
        String lastN = contactDao.findLastNameById(1L);
        contactDao.delete(1L);
        List<Contact> contacts3 = contactDao.findAllWithPhones();
        
    }
}
