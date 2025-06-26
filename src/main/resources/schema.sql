-- IGNORARÁ los errores si el objeto no existe
DROP SEQUENCE customer_seq;
DROP SEQUENCE product_seq;
DROP SEQUENCE orders_seq;

DROP TABLE order_items CASCADE CONSTRAINTS;
DROP TABLE orders      CASCADE CONSTRAINTS;
DROP TABLE products    CASCADE CONSTRAINTS;
DROP TABLE customers   CASCADE CONSTRAINTS;

/* Secuencias */
CREATE SEQUENCE customer_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE product_seq  START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE orders_seq   START WITH 1 INCREMENT BY 1;

/* Tablas */
CREATE TABLE customers (
                           id           NUMBER        DEFAULT customer_seq.NEXTVAL PRIMARY KEY,
                           first_name   VARCHAR2(50)  NOT NULL,
                           last_name    VARCHAR2(50)  NOT NULL,
                           email        VARCHAR2(100) NOT NULL UNIQUE,
                           created_at   TIMESTAMP     DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE products (
                          id      NUMBER        DEFAULT product_seq.NEXTVAL PRIMARY KEY,
                          name    VARCHAR2(80)  NOT NULL,
                          price   NUMBER(10,2)  NOT NULL,
                          stock   NUMBER        DEFAULT 0
);

CREATE TABLE orders (
                        id           NUMBER      DEFAULT orders_seq.NEXTVAL PRIMARY KEY,
                        customer_id  NUMBER      NOT NULL,
                        order_date   TIMESTAMP   DEFAULT CURRENT_TIMESTAMP,
                        CONSTRAINT fk_orders_customer
                            FOREIGN KEY (customer_id) REFERENCES customers(id)
);

CREATE TABLE order_items (
                             order_id   NUMBER      NOT NULL,
                             product_id NUMBER      NOT NULL,
                             quantity   NUMBER      NOT NULL,
                             price      NUMBER(10,2) NOT NULL,
                             CONSTRAINT pk_order_items PRIMARY KEY (order_id, product_id),
                             CONSTRAINT fk_oi_order   FOREIGN KEY (order_id)   REFERENCES orders(id),
                             CONSTRAINT fk_oi_product FOREIGN KEY (product_id) REFERENCES products(id)
);
``