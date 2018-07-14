/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jdbcspring.queries;

import java.sql.Types;
import javax.sql.DataSource;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.BatchSqlUpdate;

/**
 *
 * @author root
 */
public class InsertPhones extends BatchSqlUpdate{
    private static final String INSERT_PHONE=
            "insert into tel_numbers(t_type,phone_number,contact_id) values"+
            "(:t_type,:phone_number,:contact_id)";
    private static final int BATCH_SIZE=10;
    public InsertPhones(DataSource ds ) {
        super(ds, INSERT_PHONE);
        super.declareParameter(new SqlParameter("t_type",Types.VARCHAR));
        super.declareParameter(new SqlParameter("phone_number",Types.VARCHAR));
        super.declareParameter(new SqlParameter("contact_id",Types.INTEGER));
        super.setBatchSize(BATCH_SIZE);
        super.setGeneratedKeysColumnNames(new String[]{"id"});
        super.setReturnGeneratedKeys(true);
    }
    
}
