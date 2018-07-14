/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jdbcspring.queries;

import com.sun.xml.internal.ws.org.objectweb.asm.Type;
import java.sql.Types;
import javax.sql.DataSource;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

/**
 *
 * @author root
 */
public class UpdateContact extends SqlUpdate{
    private static final String UPDATE_CONTACT=
            "update contacts set first_name=:first_name , last_name=:last_name ,"+
            "birth_date = :birth_date where id=:id";

    public UpdateContact(DataSource ds ) {
        super(ds, UPDATE_CONTACT);
        super.declareParameter(new SqlParameter("first_name",Types.VARCHAR));
        super.declareParameter(new SqlParameter("last_name",Types.VARCHAR));
        super.declareParameter(new SqlParameter("birth_date",Types.DATE));
        super.declareParameter(new SqlParameter("id",Types.INTEGER));
    }
    
    
}
