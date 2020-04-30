/*
TRUNCATE TABLE tableName
truncate table content;-- where 1=1;
truncate table member;
truncate table device;
truncate table content_allowed_in_countries;
truncate table member_active_content;*//*


*/
/*drop table content if exists;
drop table content_allowed_in_countries if exists;
drop table device if exists;
drop table member if exists;
drop table member_active_content if exists;
drop sequence if exists hibernate_sequence;*//*

--create sequence hibernate_sequence start with 1 increment by 1;
create table content (content_id bigint not null, content_length integer, content_type varchar(255), length integer, width integer, primary key (content_id));
create table content_allowed_in_countries (content_id bigint not null, streaming_allowed_in_countries varchar(255));
create table device (device_id bigint not null, device_type varchar(255), length integer, width integer, member_id bigint not null, primary key (device_id));
create table member (member_id bigint not null, active boolean not null, home_country varchar(255), max_allowed_streams integer, primary key (member_id));
create table member_active_content (member_id bigint not null, content_id bigint not null);
alter table content_allowed_in_countries add constraint FK2gefpy090famibfcw7gfs67p0 foreign key (content_id) references content;
alter table device add constraint FKs2ah6o1y9r1ox99fh8vj5y0ol foreign key (member_id) references member;
alter table member_active_content add constraint foreign key (content_id) references content;
alter table member_active_content add constraint foreign key (member_id) references member;

insert into content (content_id, content_length, content_type, length, width) values (1, 3, "Movie", 100, 120);

insert into content_allowed_in_countries (content_id, streaming_allowed_in_countries) values (1, "USA");
insert into content_allowed_in_countries (content_id, streaming_allowed_in_countries) values (1, "IND");

insert into content (content_id, content_length, content_type, length, width) values (2, 1, "Show", 100, 120);

insert into content_allowed_in_countries (content_id, streaming_allowed_in_countries) values (2, "USA");
insert into content_allowed_in_countries (content_id, streaming_allowed_in_countries) values (2, "IND");
insert into content_allowed_in_countries (content_id, streaming_allowed_in_countries) values (2, "AU");

insert into member (member_id, active, home_country, max_allowed_streams) values (1, true, "USA", 2);
insert into member_active_content (member_id, content_id) values (1, 2);

insert into member (member_id, active, home_country, max_allowed_streams) values (2, false, "IND", 4);
insert into member_active_content (member_id, content_id) values (2, 2);

insert into device (device_id, device_type, length, width, member_id) values (1, "IPhone", 100, 150, 1);

insert into device (device_id, device_type, length, width, member_id) values (2, "GPixel", 100, 120, 1);
*/
