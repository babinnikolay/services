alter table if exists orders drop constraint if exists order_client
drop table if exists clients cascade
drop table if exists orders cascade
create table clients (uuid uuid not null, name varchar(255), primary key (uuid))
create table orders (client_uuid uuid unique, uuid uuid not null, order_date date, paid boolean, amount numeric, description varchar(255), primary key (uuid))
alter table if exists orders add constraint order_client foreign key (client_uuid) references clients