/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jdbcspring.dao;

import com.mycompany.jdbcspring.entity.Contact;
import java.util.List;

/**
 *
 * @author root
 */
public interface ContactDao {
    List<Contact> findAll();
    List<Contact> findByFirstName();
    String findFirstNameById(Long id);
    String findLastNameById(Long id);
    void insert(Contact contact);
    void update(Contact contact);
    void delete(Long id);
    
}
