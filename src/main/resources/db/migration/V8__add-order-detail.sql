create table if not exists order_line
(
    id         bigint not null auto_increment primary key,
    order_header_id   bigint not null,
    quantity_ordered   int    not null,
    created_at timestamp ,
    last_modified_date timestamp,
    constraint order_line_order_header_id_fk foreign key (order_header_id) references order_header(id)
);
