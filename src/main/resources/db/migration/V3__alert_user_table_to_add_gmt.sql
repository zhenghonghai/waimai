alter table user
    add gmt_create bigint null comment '创建时间';

alter table user
    add gmt_modified bigint null comment '修改时间';