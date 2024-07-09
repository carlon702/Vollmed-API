create table patients(
    id bigint not null auto_increment,
    name varchar(100) not null,
    email varchar(100) not null unique,
    document varchar(14) not null unique,
    street varchar(100) not null,
    district varchar(100) not null,
    complement varchar(100),
    number varchar(20),
    city varchar(100) not null,
    phone varchar(20) not null,
    active tinyint not null,

    primary key(id)
);