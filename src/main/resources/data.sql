/* ==== CLIENTES ==== */
INSERT INTO customers (first_name, last_name, email) VALUES
                                                         ('Ana',   'Ramírez',  'ana.ramirez@email.com'),
                                                         ('Luis',  'García',   'luis.garcia@email.com'),
                                                         ('Karla', 'Torres',   'karla.torres@email.com');

/* ==== PRODUCTOS ==== */
INSERT INTO products (name, price, stock) VALUES
                                              ('Laptop Lenovo ThinkPad',        18500.00, 15),
                                              ('Mouse Logitech MX Master 3S',     2500.00, 40),
                                              ('Monitor Dell 27"',               7200.00, 10),
                                              ('Teclado Keychron K8 Pro',        2900.00, 25),
                                              ('Auriculares Sony WH-1000XM5',    6500.00, 18);

/* ==== PEDIDOS ==== */
INSERT INTO orders (customer_id, order_date) VALUES
                                                 (1, SYSDATE-10),
                                                 (2, SYSDATE-5),
                                                 (1, SYSDATE-1);

/* ==== ITEMS DE PEDIDO ==== */
-- Pedido 1 (Ana)
INSERT INTO order_items VALUES (1, 1, 1, 18500.00); -- Laptop
INSERT INTO order_items VALUES (1, 2, 2,  2500.00); -- Mouse

-- Pedido 2 (Luis)
INSERT INTO order_items VALUES (2, 3, 1,  7200.00); -- Monitor

-- Pedido 3 (Ana)
INSERT INTO order_items VALUES (3, 2, 1,  2500.00); -- Mouse
INSERT INTO order_items VALUES (3, 4, 1,  2900.00); -- Teclado
INSERT INTO order_items VALUES (3, 5, 1,  6500.00); -- Audífonos
