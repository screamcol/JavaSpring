BEGIN;

DROP TABLE IF EXISTS customers CASCADE;
CREATE TABLE customers (id bigserial PRIMARY KEY, name VARCHAR(255));
INSERT INTO customers (name) VALUES
('Petr'),
('Vasiliy'),
('Mitrofan'),
('Ivan'),
('Alexey'),
('Evgeney');

DROP TABLE IF EXISTS products CASCADE;
CREATE TABLE products (id bigserial PRIMARY KEY, title VARCHAR(255), price INTEGER);
INSERT INTO products (title, price) VALUES
('t-shirt', 10),
('hat', 5),
('lamp', 50),
('notebook', 3),
('laptop', 899),
('book', 25),
('TV', 500),
('shorts', 15),
('panties', 10);

DROP TABLE IF EXISTS order_items CASCADE;
CREATE TABLE order_items (customer_id bigint, product_id bigint, FOREIGN KEY (customer_id) REFERENCES customers(id), FOREIGN KEY (product_id) REFERENCES products(id));
INSERT INTO order_items (customer_id, product_id) VALUES
(6, 5),
(6, 7),
(6, 3),
(1, 1),
(1, 2),
(3, 7),
(4, 3),
(5, 8),
(5, 9),
(2, 9),
(2, 8),
(2, 6),
(2, 2);

COMMIT;