CREATE TABLE customer (
  id int  AUTO_INCREMENT primary key,
  name varchar(255)  NOT NULL,
  email varchar(255)  not NULL unique
);
