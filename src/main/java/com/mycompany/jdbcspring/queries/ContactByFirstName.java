/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jdbcspring.queries;

import com.mycompany.jdbcspring.entity.Contact;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import javax.sql.DataSource;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;

/**
 *
 * @author root
 */
public class ContactByFirstName extends MappingSqlQuery{
    private static final String SELECT_BY_FIRST_NAME=
            "select * from contacts where first_name=:first_name";

    public ContactByFirstName(DataSource ds) {
        super(ds, SELECT_BY_FIRST_NAME);
        declareParameter(new SqlParameter("first_name",Types.VARCHAR));
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
