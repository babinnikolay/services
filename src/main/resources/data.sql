--CLIENTS
insert into clients (uuid, name)
values ('d6bffeca-8f45-41b6-82f9-7d12f2f11720', 'first demo client');
insert into clients (uuid, name)
values ('3711ef6a-6ff6-4990-b2cf-f7c65af7b845', 'second demo client');
--ORDERS
insert into orders (uuid, description, client_uuid, amount)
values ('0042b9ec-1faf-4cbb-a49e-418fdac4780a', 'first demo order', 'd6bffeca-8f45-41b6-82f9-7d12f2f11720', 101.34);
insert into orders (uuid, description, client_uuid, amount)
values ('33a29cf6-ab31-40e7-8816-092a127209b2', 'demo order', null, 0);
insert into orders (uuid, description, client_uuid, amount)
values ('dc813a48-fc4b-439f-9a38-37c00b113ad2', 'demo order', null, 0);
insert into orders (uuid, description, client_uuid, amount)
values ('3d55d8fe-8a6b-4f92-8bdd-69ea995e8528', 'demo order', null, 0);
insert into orders (uuid, description, client_uuid, amount)
values ('91d429d8-cf7f-4c05-b7fc-fa707453fe4e', 'demo order', '3711ef6a-6ff6-4990-b2cf-f7c65af7b845', 10001.54);
insert into orders (uuid, description, client_uuid, amount)
values ('e7af6e10-e66f-4e57-898a-66db58c98349', 'demo order', null, 0);
