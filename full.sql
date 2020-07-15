BEGIN;

DROP TABLE IF EXISTS products CASCADE;
CREATE TABLE products (id bigserial PRIMARY KEY, title VARCHAR(255), price INTEGER);
INSERT INTO products (title, price) VALUES
('t-shirt',	10),
('hat',	3),
('lamp',	50),
('notebook',	3),
('laptop', 999),
('book', 3),
('TV', 999),
('shorts', 15),
('table', 200),
('axe', 98),
('hammer', 80),
('chainsaw', 299),
('baseball cap', 13),
('sandals', 21),
('pants', 30),
('jacket', 37),
('scooter', 999),
('jeans', 10),
('mivroeave owen',	236),
('iron', 79);

COMMIT;