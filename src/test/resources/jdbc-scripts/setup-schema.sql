create table customer(id long auto_increment primary key not null, first_name varchar(50) not null, last_name varchar(50) not null);
create table account(id long auto_increment primary key not null, customer_id int, foreign key(customer_id) references customer(id));
