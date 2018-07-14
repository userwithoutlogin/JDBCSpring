create table contacts(
id int unsigned not null auto_increment,
first_name varchar(10) not null,
last_name varchar(10) not null,
birth_date date not null,
primary key(id)
);

create table tel_numbers(
id int unsigned not null auto_increment,
t_type varchar(10) not null,
phone_number varchar(10) not null,
contact_id int unsigned not null,
constraint fk_c
foreign key (contact_id) references 
contacts(id) 
on delete cascade 
on update cascade
);