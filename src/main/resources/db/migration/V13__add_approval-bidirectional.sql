alter table order_approval
  add column order_header_id bigint;

alter table order_approval
    add constraint order_approval_order_header_id_fk
    foreign key (order_header_id)
    references order_header (id);
