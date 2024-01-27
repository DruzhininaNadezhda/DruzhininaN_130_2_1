drop database if exists Druzhinina_Lab130_1;
CREATE database if not exists Druzhinina_Lab130_1;
-- drop table if exists Druzhinina_Lab130_1.Продукты;
create table if not exists Druzhinina_Lab130_1.Product (
vendor_code varchar(7) PRIMARY KEY not null,
name_product varchar(50) not null,
color varchar(20),
price integer,
balance_in_stock integer,
check(balance_in_stock > -1),
check(price > 0) 
);
-- drop table if exists Druzhinina_Lab130_1.Заказы;
create table if not exists Druzhinina_Lab130_1.Orders (
ID_order INT PRIMARY KEY not null,
order_creation_date date not null,
customer_name varchar(100) not null,
phone varchar(50),
email varchar(50),
address varchar(200),
order_status varchar(1),
-- CONSTRAINT check_order_status,
-- CHECK 
   -- (order_status IN ('P', 'S', 'C')),
order_shipment_date date,
 CHECK (
		(order_status IN ('P', 'S', 'C'))
	and (order_shipment_date is not null and order_status LIKE '%S%') 
     or (order_shipment_date is null and order_status LIKE '%P%')
     or (order_shipment_date is null and order_status LIKE '%C%')
     )
);
create table if not exists Druzhinina_Lab130_1.Order_items (
primary key (order_record_code, vendor_code_product),
order_record_code INT not null,
foreign key (order_record_code) references Druzhinina_Lab130_1.Orders(ID_order),
vendor_code_product varchar(7),
foreign key (vendor_code_product) references Druzhinina_Lab130_1.Product(vendor_code),
price int,
check (price >0),
quantity int,
check (quantity >0)
);
insert into Druzhinina_Lab130_1.Product (vendor_code, name_product, color, price, balance_in_stock) value ('3251615', "Стол кухонный", "белый", 8000, 12);
insert into Druzhinina_Lab130_1.Product (vendor_code, name_product, price, balance_in_stock) value ('3251616', "Стол кухонный", 8000, 12);
insert into Druzhinina_Lab130_1.Product (vendor_code, name_product, color, price, balance_in_stock) 
values ('3251617', 'Стул столовый "гусарский" ', 'орех', 4000, 4),
('3251619', 'Стул столовый с высокой спинкой', 'белый', 3500, 37),
('3251620', 'Стул столовый с высокой спинкой', 'коричневый', 3500, 52);
insert into Druzhinina_Lab130_1.Orders (ID_order, order_creation_date, customer_name, phone, address, order_status, order_shipment_date)
values (1, '2020-11-20', 'Сергей Иванов', '(981) 123-45-67', 'ул. Веденеева, 20-1-41', 'S', '2020-11-29'),
       (2, '2020-11-20', 'Алексей Комаров', '(921) 001-22-33', 'пр. Пархоменко 51-2-123', 'S', '2020-11-29');
insert into Druzhinina_Lab130_1.Orders (ID_order, order_creation_date, customer_name, phone, address, order_status)
value (3, '2020-11-28', 'Ирина Викторова', '(911) 009-88-77', 'Тихорецкий пр. 21-21', 'P');
insert into Druzhinina_Lab130_1.Orders (ID_order, order_creation_date, customer_name, email, address, order_status)
value (4, '2020-12-03', 'Павел Николаев','pasha_nick@mail.ru', 'ул. Хлопина 3-88', 'P');
insert into Druzhinina_Lab130_1.Orders (ID_order, order_creation_date, customer_name, phone, email, address, order_status)
value (5, '2020-12-03', 'Антонина Васильева', '(931) 777-66-55','antvas66@gmail.com', 'пр. Науки, 11-3-9', 'P');
insert into Druzhinina_Lab130_1.Orders (ID_order, order_creation_date, customer_name, phone, address, order_status)
value (6, '2020-12-10', 'Ирина Викторова', '(911) 009-88-77', 'Тихорецкий пр. 21-21', 'P');
insert into Druzhinina_Lab130_1.Order_items (order_record_code, vendor_code_product, quantity)
values (1, '3251616', 1);
insert into Druzhinina_Lab130_1.Order_items (order_record_code, vendor_code_product, quantity)
values (2, '3251615', 1),	(3, '3251615', 1),	(3, '3251617', 4),    (4, '3251619', 2),   (5, '3251615', 1),  (5, '3251617', 4),  (6, '3251617', 2);
UPDATE Druzhinina_Lab130_1.Order_items
JOIN Druzhinina_Lab130_1.Product on Druzhinina_Lab130_1.Order_items.vendor_code_product = Druzhinina_Lab130_1.Product.vendor_code
 set Druzhinina_Lab130_1.Order_items.price = Druzhinina_Lab130_1.Product.price;
select * from Druzhinina_Lab130_1.Order_items;

select * from druzhinina_lab130_1.Orders where order_creation_date between '2020-11-01' and '2020-12-31';

select * from druzhinina_lab130_1.Orders where order_shipment_date between '2020-11-01' and '2020-12-31';

select customer_name, phone, email from druzhinina_lab130_1.Orders;

select * from Druzhinina_Lab130_1.Order_items where order_record_code = 3;

select Druzhinina_Lab130_1.Product.name_product from Druzhinina_Lab130_1.Product 
left join Druzhinina_Lab130_1.Order_items
on Druzhinina_Lab130_1.Order_items.order_record_code = 3 
where Druzhinina_Lab130_1.Order_items.vendor_code_product = Druzhinina_Lab130_1.Product.vendor_code;

select * from Druzhinina_Lab130_1.Product;

UPDATE Druzhinina_Lab130_1.Orders, Druzhinina_Lab130_1.Product
set Druzhinina_Lab130_1.Orders.order_status = 'S',
Druzhinina_Lab130_1.Orders.order_shipment_date = '2023-12-14'
WHERE Druzhinina_Lab130_1.Orders.ID_order = 5;
-- SET SQL_SAFE_UPDATES=0;
select * from Druzhinina_Lab130_1.Orders;

UPDATE Druzhinina_Lab130_1.Product, Druzhinina_Lab130_1.Order_items
set Druzhinina_Lab130_1.Product.balance_in_stock = (Druzhinina_Lab130_1.Product.balance_in_stock - Druzhinina_Lab130_1.Order_items.quantity)
WHERE Druzhinina_Lab130_1.Order_items.order_record_code=5;

select * from Druzhinina_Lab130_1.Product;
select * from Druzhinina_Lab130_1.Order_items;
select * from Druzhinina_Lab130_1.Orders

