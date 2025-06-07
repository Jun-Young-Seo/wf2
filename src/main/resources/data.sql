insert into product (name, brand, made_in, price) values ('Galaxy S6', 'Samsung Corp', 'Korea', 600.0);
insert into product (name, brand, made_in, price) values ('Galaxy S8', 'Samsung Corp', 'Korea', 800.0);
insert into product (name, brand, made_in, price) values ('Galaxy S10', 'Samsung Corp', 'Korea', 1000.0);
insert into product (name, brand, made_in, price) values ('Galaxy S21', 'Samsung Corp', 'Korea', 1000.0);

insert into product (name, brand, made_in, price) values ('MacBook Air1', 'Apple', 'China',  10000);
insert into product (name, brand, made_in, price) values ('MacBook Air2', 'Apple', 'China',  10000);
insert into product (name, brand, made_in, price) values ('MacBook Air3', 'Apple', 'China',  10000);
insert into product (name, brand, made_in, price) values ('MacBook Air4', 'Apple', 'China',  10000);
insert into product (name, brand, made_in, price) values ('MacBook Air5', 'Apple', 'China',  10000);
insert into product (name, brand, made_in, price) values ('MacBook Pro1', 'Apple', 'China',  15000);
insert into product (name, brand, made_in, price) values ('MacBook Pro2', 'Apple', 'China',  15000);

insert into product (name, brand, made_in, price) values ('iPad Air', 'Apple', 'China',  500);
insert into product (name, brand, made_in, price) values ('iPad Pro', 'Apple', 'China',  800);

insert into product (name, brand, made_in, price) values ('소나타', 'Hyundai', 'Japan',  20000);
insert into product (name, brand, made_in, price) values ('그랜저', 'Hyundai', 'Japan',  30000);
insert into product (name, brand, made_in, price) values ('제너시스', 'Hyundai', 'Japan',  50000);
insert into product (name, brand, made_in, price) values ('에쿠스', 'Hyundai', 'Japan',  60000);

insert into product (name, brand, made_in, price) values ('Accord', 'Honda', 'Japan',  25000);
insert into product (name, brand, made_in, price) values ('sienna', 'Honda', 'Japan',  40000);

insert into product (name, brand, made_in, price) values ('Camry', 'Toyota', 'Japan',  25000);
insert into product (name, brand, made_in, price) values ('Lexus', 'Toyota', 'Japan',  50000);

-- 권한 정보 넣고 시작
insert into role (role_name) values("ROLE_ADMIN");
insert into role (role_name) values("ROLE_USER");

-- Test용 계정 처음에 넣고 시작
-- password는 123에 대한 해시 값(Bcrypt)
insert into user(email,password) values("admin@naver.com", "$2a$10$csx666GPfanxKGKrACSaze5rt/IeUSIhjpvqgovRpAM.Bh2ZCJ7yW");
insert into user(email,password) values("normal@naver.com", "$2a$10$csx666GPfanxKGKrACSaze5rt/IeUSIhjpvqgovRpAM.Bh2ZCJ7yW");

-- DDL-auto = create니까 만들 떄 마다 초기화되므로 처음 계정 두개 강제 넣고 시작
insert into user_role (user_id, role_id) values (1, 1);
insert into user_role (user_id, role_id) values (2, 2);
