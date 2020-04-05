create table user
(
    id         bigint auto_increment primary key comment '主键',
    avatar_url varchar(100) null comment '头像',
    user_name  varchar(20)  null comment '用户名',
    account    varchar(20)  null comment '账号',
    password   varchar(20)  null comment '密码'
)
    comment '用户信息表';

