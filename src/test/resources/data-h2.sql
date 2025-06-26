/* ==== CLIENTES ==== */
INSERT INTO customers (id, first_name, last_name, email) VALUES
                                                             (1,'Ana',   'Ramírez', 'ana.ramirez@email.com'),
                                                             (2,'Luis',  'García',  'luis.garcia@email.com'),
                                                             (3,'Karla', 'Torres',  'karla.torres@email.com');

/* ==== PRODUCTOS ==== */
INSERT INTO products (id, name, price, stock) VALUES
                                                  (1,'Laptop Lenovo ThinkPad',        18500.00, 15),
                                                  (2,'Mouse Logitech MX Master 3S',    2500.00, 40),
                                                  (3,'Monitor Dell 27\"',              7200.00, 10),
                                                  (4,'Teclado Keychron K8 Pro',        2900.00, 25),
                                                  (5,'Auriculares Sony WH-1000XM5',    6500.00, 18);

/* ==== PEDIDOS ==== */
INSERT INTO orders (id, customer_id, order_date) VALUES
                                                     (1, 1, DATEADD('DAY', -10, CURRENT_TIMESTAMP)),
                                                     (2, 2, DATEADD('DAY',  -5, CURRENT_TIMESTAMP)),
                                                     (3, 1, DATEADD('DAY',  -1, CURRENT_TIMESTAMP));

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
