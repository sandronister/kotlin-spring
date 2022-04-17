CREATE TABLE customer_role (
  customer_id int  NOT NULL,
  role varchar(100)  not NULL,
  FOREIGN KEY(customer_id) REFERENCES customer(id)
);
