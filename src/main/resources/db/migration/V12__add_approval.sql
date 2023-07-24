create table if not exists order_approval
(
    id          bigint auto_increment primary key,
    approved_by varchar(50),
    created_at timestamp,
    last_modified_date timestamp
);

alter table order_header
    add column approval_id bigint;
alter table order_header
        add constraint fk_order_approval_id foreign key (approval_id) references order_approval (id);
