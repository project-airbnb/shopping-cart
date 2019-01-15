create database cart;
use cart;
ALTER DATABASE cart CHARACTER SET utf8 COLLATE utf8_unicode_ci;
create table user(
  id INT(255) NOT NULL  PRIMARY KEY
);
ALTER TABLE user CONVERT TO CHARACTER SET utf8 COLLATE utf8_unicode_ci;

create table product(
  id INT(255) NOT NULL  PRIMARY KEY
);
ALTER TABLE product CONVERT TO CHARACTER SET utf8 COLLATE utf8_unicode_ci;

create table order_cart(
  id INT(255) NOT NULL PRIMARY KEY 
);
ALTER TABLE order_cart CONVERT TO CHARACTER SET utf8 COLLATE utf8_unicode_ci;
