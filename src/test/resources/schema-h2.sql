/* =========================================================
   ===============  Limpieza previa (idempotente) ===========
   ========================================================= */
DROP TABLE IF EXISTS order_items;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS products;
DROP TABLE IF EXISTS customers;

DROP SEQUENCE IF EXISTS customer_seq;
DROP SEQUENCE IF EXISTS product_seq;
DROP SEQUENCE IF EXISTS orders_seq;

/* =========================================================
   ===============  Secuencias (por compatibilidad) =========
   ========================================================= */
CREATE SEQUENCE IF NOT EXISTS customer_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE IF NOT EXISTS product_seq  START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE IF NOT EXISTS orders_seq   START WITH 1 INCREMENT BY 1;

/* =========================================================
   ===============  Tablas  =================================
   ========================================================= */

/* ---- CLIENTES ---- */
CREATE TABLE customers (
                           id          BIGINT AUTO_INCREMENT PRIMARY KEY,
                           first_name  VARCHAR(50)  NOT NULL,
                           last_name   VARCHAR(50)  NOT NULL,
                           email       VARCHAR(100) NOT NULL UNIQUE,
                           created_at  TIMESTAMP    DEFAULT CURRENT_TIMESTAMP
);

/* ---- PRODUCTOS ---- */
CREATE TABLE products (
                          id     BIGINT AUTO_INCREMENT PRIMARY KEY,
                          name   VARCHAR(80)   NOT NULL,
                          price  DECIMAL(10,2) NOT NULL,
                          stock  INT DEFAULT 0
);

/* ---- PEDIDOS ---- */
CREATE TABLE orders (
                        id           BIGINT AUTO_INCREMENT PRIMARY KEY,
                        customer_id  BIGINT     NOT NULL,
                        order_date   TIMESTAMP  DEFAULT CURRENT_TIMESTAMP,
                        CONSTRAINT fk_orders_customer
                            FOREIGN KEY (customer_id) REFERENCES customers(id)
);

/* ---- ÍTEMS DE PEDIDO ---- */
CREATE TABLE order_items (
                             order_id   BIGINT NOT NULL,
                             product_id BIGINT NOT NULL,
                             quantity   INT    NOT NULL,
                             price      DECIMAL(10,2) NOT NULL,
                             PRIMARY KEY (order_id, product_id),
                             CONSTRAINT fk_oi_order   FOREIGN KEY (order_id)   REFERENCES orders(id),
                             CONSTRAINT fk_oi_product FOREIGN KEY (product_id) REFERENCES products(id)
);
