--CLIENTS
insert into clients (uuid, name)
values ('d6bffeca-8f45-41b6-82f9-7d12f2f11720', 'first demo client');
insert into clients (uuid, name)
values ('3711ef6a-6ff6-4990-b2cf-f7c65af7b845', 'second demo client');
--ORDERS
insert into orders (uuid, description, client_uuid, amount)
values ('0042b9ec-1faf-4cbb-a49e-418fdac4780a', 'first demo order', 'd6bffeca-8f45-41b6-82f9-7d12f2f11720', 101.34);
insert into orders (uuid, description, client_uuid, amount)
values ('33a29cf6-ab31-40e7-8816-092a127209b2', 'second demo order', null, 0);
