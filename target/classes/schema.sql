drop table if exists `user`;
create table user(
	id int IDENTITY primary key,
	username varchar(100),
	password varchar(100),
	age int,
	create_time long
);