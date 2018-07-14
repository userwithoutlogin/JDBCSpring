/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jdbcspring.dao;

import com.mycompany.jdbcspring.entity.Contact;
import com.mycompany.jdbcspring.entity.Phone;
import com.mycompany.jdbcspring.queries.ContactByFirstName;
import com.mycompany.jdbcspring.queries.SelectAllContacts;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.AbstractSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

/**
 *
 * @author root
 */
public class ContactDaoImpl implements ContactDao,InitializingBean{
    private DataSource dataSource;
    private NamedParameterJdbcTemplate jdbcTemplate;
    public DataSource getDataSource() {
        return dataSource;
        
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }
    
    
    
    @Override
    public List<Contact> findAll() {
       String sql ="select * from contacts";
//       return jdbcTemplate.query(sql, new MapAllContacts());
//       return jdbcTemplate.query(sql, new ExtractAllContacts());
        return new SelectAllContacts(dataSource).execute();
    }

    @Override
    public List<Contact> findByFirstName(String first_name) {
        String sql="select c.id , c.first_name , c.last_name , c.birth_date, "+
                 "t.id as t_id , t.t_type , t.phone_number , t.contact_id from contacts c"+
                 " left join tel_numbers t on t.contact_id = c.id"+
                " where first_name=:first_name";
        Map<String,Object> param = new HashMap();
        param.put("first_name", first_name);
//        return jdbcTemplate.query(sql, param, new ExtractAllContactsWithPhones());
            return new ContactByFirstName(dataSource).executeByNamedParam(param);
    }

    @Override
    public String findFirstNameById(Long id) {
       String sql = "select first_name from contacts where id = :id";
       Map<String,Object> param = new HashMap();
       param.put("id",id);
       return jdbcTemplate.queryForObject(sql, param, String.class);
    }

    @Override
    public String findLastNameById(Long id) {
        String sql = "select last_name from contacts where id=:id";
        Map<String,Object> param = new HashMap();
        param.put("id", id);
        return jdbcTemplate.queryForObject(sql, param, String.class);
    }

    @Override
    public void insert(Contact contact) {
         
                
    }

    @Override
    public void update(Contact contact) {
    
    }

    @Override
    public void delete(Long id) {
      String sql = "delete from contacts where id=:id";
      Map<String,Object> param = new HashMap();
      param.put("id",id);
      jdbcTemplate.update(sql, param);
    }

    
    @Override
    public List<Contact> findAllWithPhones() {
         String sql ="select c.id , c.first_name , c.last_name , c.birth_date, "+
                 "t.id as t_id , t.t_type , t.phone_number , t.contact_id from contacts c"+
                 " left join tel_numbers t on t.contact_id = c.id";
         return jdbcTemplate.query(sql, new ExtractAllContactsWithPhones());
//         return jdbcTemplate.query(sql, (rs)->{
//              Map<Long,Contact> contacts = new HashMap<Long, Contact>();
//                    
//              while(rs.next()){
//                  Long c_id = rs.getLong("id");
//                  
//                  Contact c = contacts.get(c_id);
//                  if(c == null){
//                    c.setId(rs.getLong("id"));
//                    c.setFirstName(rs.getString("first_name"));
//                    c.setLastName(rs.getString("last_name"));
//                    c.setBirthDate(rs.getDate("birth_date"));
//                    contacts.put(c_id, c);
//                  }
//                  Long t_id = rs.getLong("t_id");
//                  if(t_id>0){
//                      Phone phone = new  Phone();
//                      phone.setId(rs.getLong("t_id"));
//                      phone.setContact(c);
//                      phone.setType(rs.getString("type"));
//                      phone.setNumber(rs.getString("phone_number"));
//                      c.getPhones().add(phone);
//                          }        
//              }
//              return new ArrayList(contacts.values());
//         });
         
    }
    
    
    private static final class MapAllContacts implements RowMapper<Contact>{

        @Override
        public Contact mapRow(ResultSet rs, int i) throws SQLException {
                  Contact c = new Contact();
                  c.setId(rs.getLong("id"));
                  c.setFirstName(rs.getString("first_name"));
                  c.setLastName(rs.getString("last_name"));
                  c.setBirthDate(rs.getDate("birth_date"));
                  return c;
        }
        
    }
    private static final class ExtractAllContacts implements ResultSetExtractor<List<Contact>>{

        @Override
        public List<Contact> extractData(ResultSet rs) throws SQLException, DataAccessException {
            List<Contact> contacts = new ArrayList();
              while(rs.next()){
                  Contact c = new Contact();
                  c.setId(rs.getLong("id"));
                  c.setFirstName(rs.getString("first_name"));
                  c.setLastName(rs.getString("last_name"));
                  c.setBirthDate(rs.getDate("birth_date"));
                  contacts.add(c);
              }
              return contacts;
        }
        
    }
    private static final class ExtractAllContactsWithPhones implements ResultSetExtractor<List<Contact>>{

        @Override
        public List<Contact> extractData(ResultSet rs) throws SQLException, DataAccessException {
            Map<Long,Contact> contacts = new HashMap<Long, Contact>();
                Contact c=null;
                
              while(rs.next()){
                  Long c_id = rs.getLong("id");
                  
                    c = contacts.get(c_id);
                  if(c == null){
                      c=new Contact();
                        c.setFirstName(rs.getString("first_name"));
                    c.setId(c_id);
                   
                    c.setLastName(rs.getString("last_name"));
                    c.setBirthDate(rs.getDate("birth_date"));
                    contacts.put(c_id, c);
                  }
                  Long t_id = rs.getLong("t_id");
                  if(t_id>0){
                      Phone phone = new  Phone();
                      phone.setId(rs.getLong("t_id"));
                      phone.setContact(c);
                      phone.setType(rs.getString("t_type"));
                      phone.setNumber(rs.getString("phone_number"));
                      c.getPhones().add(phone);
                          }        
              }
              return new ArrayList(contacts.values());
        }
        
    }
    
     @Override
    public void afterPropertiesSet() throws Exception {
        
            if(dataSource== null)
                throw new BeanCreationException("Data source was not been set");
            if(jdbcTemplate == null)
                throw new BeanCreationException("jdbcTemplate was not been set");
    }

}
