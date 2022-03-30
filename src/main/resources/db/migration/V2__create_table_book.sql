CREATE TABLE book (
  id int  AUTO_INCREMENT primary key,
  name varchar(255)  NOT NULL,
  price decimal(10,2)  not NULL,
  status varchar(255) not null,
  customer_id int not null,
  FOREIGN KEY(customer_id) REFERENCES customer(id)
);
