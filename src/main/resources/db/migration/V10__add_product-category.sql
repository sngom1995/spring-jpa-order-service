create table if not exists category (
    id bigint not null auto_increment primary key,
    description varchar(50),
    created_at timestamp,
    last_modified_date timestamp
);

create table if not exists product_category
(
    product_id  bigint not null,
    category_id bigint not null,
    primary key (product_id, category_id),
    constraint fk_product_id foreign key (product_id) references product (id),
    constraint fk_category_id foreign key (category_id) references category (id)
);

insert into product (description, status, created_at, last_modified_date)
values ('PRODUCT1', 'NEW', NOW(), NOW());
insert into product (description, status, created_at, last_modified_date)
values ('PRODUCT2', 'NEW', NOW(), NOW());
insert into product (description, status, created_at, last_modified_date)
values ('PRODUCT3', 'NEW', NOW(), NOW());
insert into product (description, status, created_at, last_modified_date)
values ('PRODUCT4', 'NEW', NOW(), NOW());

insert into category (description, created_at, last_modified_date)
values ('CATEGORY1', NOW(), NOW());
insert into category (description, created_at, last_modified_date)
values ('CATEGORY2', NOW(), NOW());
insert into category (description, created_at, last_modified_date)
values ('CATEGORY3', NOW(), NOW());
insert into category (description, created_at, last_modified_date)
values ('CATEGORY4', NOW(), NOW());

insert into product_category  (product_id, category_id)
select p.id, c.id from product p, category c where p.description = 'PRODUCT1' and c.description = 'CATEGORY1';

insert into product_category  (product_id, category_id)
select p.id, c.id from product p, category c where p.description = 'PRODUCT2' and c.description = 'CATEGORY2';
insert into product_category  (product_id, category_id)
select p.id, c.id from product p, category c where p.description = 'PRODUCT2' and c.description = 'CATEGORY3';
insert into product_category  (product_id, category_id)
select p.id, c.id from product p, category c where p.description = 'PRODUCT2' and c.description = 'CATEGORY4';
