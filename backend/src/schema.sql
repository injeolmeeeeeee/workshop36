drop database if exists feeds;

create database feeds;

use feeds;

create table posts (
   post_id varchar(8) not null,
   comments mediumtext not null,
   picture mediumblob,
   
   constraint primary key(pic_id)
);

grant all privileges on posts.* to 'fred'@'%';

flush privileges;