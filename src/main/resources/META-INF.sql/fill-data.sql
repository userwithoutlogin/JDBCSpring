 insert into contacts(first_name,last_name,birth_date) values
('Chris','Johnson','1986-1-7'),
('Paul','Dawis','1976-10-17'),
('Michael','Lesnar','1986-1-7'),
('Bob','Davidson','1986-1-7');

insert into tel_numbers(t_type,phone_number,contact_id) values
('Mobile','8922134311',1),
('Home','34311',1),
('Home','31511',2),
('Mobile','8923431500',3),
-- ('Home','89321',4),
('Mobile','8932342521',1);

select c.id , c.first_name , c.last_name , c.birth_date ,
                 t.id as t_id , t.t_type , t.phone_number , t.contact_id from contacts c
                  left join tel_numbers t on t.contact_id = c.id;