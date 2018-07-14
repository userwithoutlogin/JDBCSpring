/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jdbcspring;

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
       
    }
}
