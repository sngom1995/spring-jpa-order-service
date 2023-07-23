drop table if exists order_header;

create table order_header
(
    id            bigint not null auto_increment,
    customer_name varchar(255),
    primary key (id)
);

insert into order_header (id, customer_name) VALUES (1, 'John Doe');
insert into order_header (id, customer_name) VALUES (2, 'Samba Ngom');
insert into order_header (id, customer_name) VALUES (3, 'Samba Lo');

