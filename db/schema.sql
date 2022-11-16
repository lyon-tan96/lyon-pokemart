create table account (
	acct_id int not null auto_increment,
    username varchar(64) not null,
    email varchar(64) not null,
    password varchar(64) not null,
    pic mediumblob not null,
    
    primary key(username)
);

create table cart (
    cart_id int auto_increment,
    username varchar(128) not null,
	image varchar(128) not null,
    cardName varchar(128) not null,
    id varchar(128) not null,
    price varchar(128) not null,
    
    foreign key(username)
		references account(username),
    
    primary key(cart_id)
);

create table orders (
    order_id varchar(128) not null,
    username varchar(128) not null,
	name varchar(128) not null,
    contact varchar(128) not null,
    postal varchar(128) not null,
    address1 varchar(128) not null,
    address2 varchar(128),
    amount varchar(128) not null,
    date timestamp,
    status varchar(128) not null,
    
    foreign key(username)
		references account(username),
    
    primary key(order_id)
);

create table lineitems (
	lineitem_id int auto_increment,
    order_id varchar(128) not null,
    image varchar(128) not null,
    card_name varchar(128) not null,
    card_id varchar(128) not null,
    price varchar(128) not null,
    
    foreign key(order_id)
		references orders(order_id),
    
    primary key(lineitem_id)
);