use shiro

if exists drop table user;
if exists drop table role;
if exists drop table user_role;
if exists drop table permission;

create table user(
id int not null auto_increment,
name varchar(100),
age int, 
birthday timestamp,
password varchar(50),
primary key(id)
);

create table role(
id int not null auto_increment,
code varchar(40),
name varchar(100) not null,
primary key(id)
);


create table permission(
id int not null auto_increment,
permission varchar(100) not null,
primary key(id),
unique(permission)
);

create table user_role(
id int not null auto_increment,
user_id int not null,
role_id int not null,
primary key(id),
unique(user_id,role_id),
foreign key(user_id) references  user(id),
foreign key(role_id) references  role(id)
);

create table role_permission(
id int not null auto_increment,
role_id int not null,
permission_id int not null,
primary key(id),
unique(role_id,permission_id),
foreign key(role_id) references  role(id),
foreign key(permission_id) references  permission(id)
);