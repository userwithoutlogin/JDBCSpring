/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jdbcspring.queries;

import com.mycompany.jdbcspring.entity.Contact;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.springframework.jdbc.object.MappingSqlQuery;

/**
 *
 * @author root
 */
public class SelectAllContacts extends MappingSqlQuery{
    private static final String SELECT_ALL="select * from contacts";

    public SelectAllContacts(DataSource ds) {
        super(ds,SELECT_ALL);
    }
    
    
    @Override
    protected Object mapRow(ResultSet rs, int i) throws SQLException {
        Contact contact = new Contact();
        contact.setId(rs.getLong("id"));
        contact.setFirstName(rs.getString("first_name"));
        contact.setLastName(rs.getString("last_name"));
        contact.setBirthDate(rs.getDate("birth_date"));
        return contact;
    }
    
}
