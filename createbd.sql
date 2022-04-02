CREATE USER admin WITH ENCRYPTED PASSWORD '1970';
DROP OWNED BY admin;
DROP DATABASE IF EXISTS warehouse_accounting_db;

CREATE DATABASE warehouse_accounting_db;
GRANT ALL PRIVILEGES ON warehouse_accounting_db TO admin;

\connect warehouse_accounting_db

SET ROLE admin;

CREATE TABLE IF NOT EXISTS register_place(
  place_id SERIAL NOT NULL PRIMARY KEY,
  storage_location VARCHAR(255) NOT NULL,
  free VARCHAR(3) NOT NULL CHECK (free IN ('да', 'нет'))
);

CREATE TABLE IF NOT EXISTS products(
  product_id SERIAL NOT NULL PRIMARY KEY,
  product_name VARCHAR(255) NOT NULL,
  type VARCHAR(255) NOT NULL,
  quantity INTEGER NOT NULL CHECK (quantity > 0),
  measure VARCHAR(255) NOT NULL CHECK (measure IN ('штук', 'кг')),
  expiration_date_from timestamp NOT NULL,
  expiration_date_to timestamp NOT NULL,
  place_id INTEGER NOT NULL REFERENCES register_place ON DELETE CASCADE
);


CREATE TABLE IF NOT EXISTS clients(
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
  supply_id SERIAL NOT NULL PRIMARY KEY,
  supplier_id INTEGER NOT NULL REFERENCES suppliers ON DELETE CASCADE,
  data_supply timestamp NOT NULL,
  status VARCHAR(255) NOT NULL,
  store_period INTEGER NOT NULL CHECK (store_period > 0)
);


CREATE TABLE IF NOT EXISTS deliveries(
  delivery_id SERIAL NOT NULL PRIMARY KEY,
  client_id INTEGER NOT NULL REFERENCES clients ON DELETE CASCADE,
  date_issue timestamp NOT NULL,
  status VARCHAR(255) NOT NULL CHECK (status IN ('в процессе', 'завершен'))
);

CREATE TABLE IF NOT EXISTS deliveries_list(
  deliveries_list_id SERIAL NOT NULL PRIMARY KEY,
  product_id INTEGER NOT NULL REFERENCES products ON DELETE CASCADE,
  delivery_id INTEGER NOT NULL REFERENCES deliveries ON DELETE CASCADE,
  quantity INTEGER NOT NULL CHECK (quantity > 0)
);

CREATE TABLE IF NOT EXISTS supplies_list(
  supplies_list_id SERIAL NOT NULL PRIMARY KEY,
  product_id INTEGER NOT NULL REFERENCES products ON DELETE CASCADE,
  supply_id INTEGER NOT NULL REFERENCES supplies ON DELETE CASCADE,
  quantity INTEGER NOT NULL CHECK (quantity > 0)
);