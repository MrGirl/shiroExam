drop DATABASE if exists shiro;
create DATABASE shiro;
use shiro;

drop table if exists role_permission;
drop table if exists user_role;
drop table if exists user;
drop table if exists role;
drop table if exists permission;

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
resource varchar(100) not null,
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

begin;
	insert into user(id,name,age,birthday,password)values(1,'zyd','27',timestamp("1988-02-04"),'E10ADC3949BA59ABBE56E057F20F883E');
	insert into role(id,code,name)values(1,1,'admin'),(2,2,'normal');
	insert into permission (id,permission)values(1,"user:*");
	insert into user_role(user_id,role_id)values(1,1);
	insert into role_permission(role_id,permission_id)values(1,1);
commit;