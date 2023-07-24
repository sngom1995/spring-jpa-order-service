drop table if exists product;
create table product
(
    id          bigint auto_increment primary key,
    description varchar(400),
    status      varchar(30),
    created_at  timestamp,
    last_modified_date  timestamp
);

