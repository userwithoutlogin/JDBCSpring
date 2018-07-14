/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jdbcspring.entity;

/**
 *
 * @author root
 */
public class Phone {
    private Long id;
    private String type;
    private String number;
    private Contact contact;

    public Phone() {
    }

    public Phone(String type, String number, Contact contact) {
        this.type = type;
        this.number = number;
        this.contact = contact;
    }

    

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

   
    
}
