DROP OWNED BY admin;
DROP USER IF EXISTS admin;
DROP DATABASE IF EXISTS warehouse_accounting_db;

CREATE DATABASE warehouse_accounting_db;
CREATE USER admin WITH WITH ENCRYPTED PASSWORD '1970';
GRANT ALL PRIVILEGES ON warehouse_accounting_db TO admin;

\connect warehouse_accounting_db

SET ROLE admin;


CREATE TABLE IF NOT EXISTS product(
  product_id SERIAL NOT NULL PRIMARY KEY,
  product_name VARCHAR(255) NOT NULL,
  type VARCHAR(255) NOT NULL,
  quantity INTEGER NOT NULL CHECK (quantity > 0),
  measure VARCHAR(255) NOT NULL CHECK (measure IN ('штук', 'кг')),
  expiration_date_from timestamp NOT NULL,
  expiration_date_to timestamp NOT NULL,
  storage_location VARCHAR(255) NOT NULL
);


CREATE TABLE IF NOT EXISTS client(
  client_id SERIAL NOT NULL PRIMARY KEY,
  client_name VARCHAR(255) NOT NULL,
  contact VARCHAR(50) NOT NULL,
  address VARCHAR(255) NOT NULL,
  email VARCHAR(255) NOT NULL
);


CREATE TABLE IF NOT EXISTS suppliers(
  supplier_id SERIAL NOT NULL PRIMARY KEY,
  supplier_name VARCHAR(255) NOT NULL,
  contact VARCHAR(50) NOT NULL,
  address VARCHAR(255) NOT NULL,
  email VARCHAR(255) NOT NULL
);


CREATE TABLE IF NOT EXISTS supplies(
  product_id INTEGER NOT NULL REFERENCES product ON DELETE CASCADE,
  supplier_id INTEGER NOT NULL REFERENCES suppliers ON DELETE CASCADE,
  data_supply timestamp NOT NULL,
  count INTEGER NOT NULL CHECK (count > 0),
  status VARCHAR(255) NOT NULL,
  store_period INTEGER NOT NULL CHECK (store_period > 0)
);


CREATE TABLE IF NOT EXISTS deliveries(
  product_id INTEGER NOT NULL REFERENCES product ON DELETE CASCADE,
  client_id INTEGER NOT NULL REFERENCES client ON DELETE CASCADE,
  date_issue timestamp NOT NULL,
  count INTEGER NOT NULL CHECK (count > 0),
  status VARCHAR(255) NOT NULL CHECK (status IN ('в процессе', 'завершен'))
);