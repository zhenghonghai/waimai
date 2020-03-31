create table address
(
    id              bigint auto_increment primary key comment '主键',
    user_id         bigint        null comment '用户id',
    contacts        varchar(20)   null comment '联系人',
    contacts_mobile int           null comment '手机号码',
    address         nvarchar(100) null comment '地址',
    house_number    varchar(10)   null comment '门牌号',
    tag             varchar(10)   null comment '标签',
    foreign key (user_id) references user (id)
)
    comment '收获地址表';

