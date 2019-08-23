use foody;

create table restaurants(id bigint auto_increment primary key,
restaurant_name varchar(255) not null,cover_image varchar(1000) default null,
cousine mediumtext default null, created_at datetime default CURRENT_TIMESTAMP,
updated_at datetime default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP)engine=innodb;


insert into restaurants(restaurant_name, cover_image, cousine)
values ("Eataly Express", "https://www.thechunkychef.com/wp-content/uploads/2017/08/One-Pot-Chicken-Parmesan-Pasta-feat.jpg", "pasta, noodles, rice, chicken"),
("Eataly Express", "https://www.thechunkychef.com/wp-content/uploads/2017/08/One-Pot-Chicken-Parmesan-Pasta-feat.jpg", "pasta, noodles, rice, chicken"),
("Idly kada", "https://www.thechunkychef.com/wp-content/uploads/2017/08/One-Pot-Chicken-Parmesan-Pasta-feat.jpg", "noodles, rice, chicken"),
("Kadai Curry", "https://www.thechunkychef.com/wp-content/uploads/2017/08/One-Pot-Chicken-Parmesan-Pasta-feat.jpg", "noodles, rice, chicken"),
("Curry Gravy", "https://www.thechunkychef.com/wp-content/uploads/2017/08/One-Pot-Chicken-Parmesan-Pasta-feat.jpg", "pasta, noodles, chicken"),
("Naatu Kozhi", "https://www.thechunkychef.com/wp-content/uploads/2017/08/One-Pot-Chicken-Parmesan-Pasta-feat.jpg", "fish, mutton, rice");


create table cousines(id int auto_increment PRIMARY KEY, cousine_name varchar(255) not null,
 created_at datetime default CURRENT_TIMESTAMP,
updated_at datetime default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP)engine=innodb;

create table restaurant_cousine_mapping(
cousine_id int NOT NULL,
restaurant_id BIGINT NOT NULL,
 created_at datetime default CURRENT_TIMESTAMP,
updated_at datetime default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
FOREIGN KEY `fk_restaurant_id_restaurant_cousine_mapping`(restaurant_id) REFERENCES restaurants(id),
FOREIGN KEY `fk_cousine_id_restaurant_cousine_mapping`(cousine_id) REFERENCES cousines(id))engine=innodb;

create table dishes(id bigint auto_increment PRIMARY KEY,
dish_name varchar(255) NOT NULL,
restaurant_id BIGINT NOT NULL,
incredients MEDIUMTEXT DEFAULT NULL,
created_at datetime default CURRENT_TIMESTAMP,
updated_at datetime default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
FOREIGN KEY `fk_restaurant_id_dishes`(restaurant_id) REFERENCES restaurants(id))engine=innodb;

alter table restaurants DROP COLUMN restaurants.cousine;

alter TABLE restaurant_cousine_mapping add INDEX `idx_restaurant_cousine_mapping_cousine_id_restaurant_id` (cousine_id,restaurant_id),
ADD UNIQUE KEY `uk_restaurant_cousine_mapping_cousine_id_restaurant_id` (cousine_id,restaurant_id);

create table review_points(id int auto_increment PRIMARY KEY,
review_text VARCHAR(255) not NULL UNIQUE,
created_at datetime default CURRENT_TIMESTAMP,
updated_at datetime default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP)engine=innodb;

create table reviews(id BIGINT auto_increment PRIMARY KEY,
user_id BIGINT DEFAULT NULL,
restaurant_id BIGINT NOT NULL,
review_comments MEDIUMTEXT NULL,
review_point INT not NULL,
created_at datetime default CURRENT_TIMESTAMP,
updated_at datetime default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
FOREIGN KEY `fk_reviews_user_id` (user_id) REFERENCES users(id),
FOREIGN KEY `fk_reviews_restaurant_id` (restaurant_id) REFERENCES restaurants(id)
)engine=innodb;

insert into reviews (restaurant_id, review_point) values (1,5),
(1,2),
(1,3),
(1,4),
(2,5);

create table addresses(id BIGINT auto_increment PRIMARY KEY,
user_id BIGINT DEFAULT NULL,
address MEDIUMTEXT DEFAULT NULL,
phone_no BIGINT not NULL,
created_at datetime default CURRENT_TIMESTAMP,
updated_at datetime default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
FOREIGN KEY `fk_cart_user_id` (user_id) REFERENCES users(id)
)engine=innodb;

create table cart(id BIGINT auto_increment PRIMARY KEY,
order_id varchar(20) null UNIQUE,
user_id BIGINT DEFAULT NULL,
created_at datetime default CURRENT_TIMESTAMP,
updated_at datetime default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
FOREIGN KEY `fk_cart_user_id` (user_id) REFERENCES users(id)
)engine=innodb;

create table cart_items(id BIGINT auto_increment PRIMARY KEY,
dish_id bigint not null,
quantity int DEFAULT 1,
price DOUBLE DEFAULT 0.0,
created_at datetime default CURRENT_TIMESTAMP,
updated_at datetime default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
FOREIGN KEY `fk_cart_items_dish_id` (dish_id) REFERENCES dishes(id)
)engine=innodb;

create table orders(id BIGINT auto_increment PRIMARY KEY,
address_id bigint not null,
user_id BIGINT DEFAULT NULL,
price DOUBLE DEFAULT 0.0,
discount DOUBLE DEFAULT 0.0,
total_amount DOUBLE DEFAULT 0.0,
order_status VARCHAR(20) null,
payment_mode varchar(20) null,
created_at datetime default CURRENT_TIMESTAMP,
updated_at datetime default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
FOREIGN KEY `fk_orders_user_id` (user_id) REFERENCES users(id),
FOREIGN KEY `fk_orders_address_id` (address_id) REFERENCES addresses(id)
)engine=innodb;

alter table dishes add column price DOUBLE DEFAULT 0.0;

alter table cart_items add COLUMN cart_id BIGINT not NULL,
add FOREIGN KEY `fk_cart_items_cart_id` (cart_id) REFERENCES cart(id);

