show databases;
create database cinemaproject;
use cinemaproject;
create table Users (
	user_id varchar(8) primary key,
    full_name varchar(50) not null,
    email varchar(30) not null,
    phone varchar(10) not null,
    birthday Date not null,
    gender int not null check (gender >= 0 and gender <= 1),
    password varchar(30) not null,
    city varchar(100) not null,
    user_type_id varchar(8) default "Bronze"
);
create table City (
	city_id int not null primary key,
    city_name varchar(20)
);
drop table users;
SELECT * FROM Users;
select curdate() from dual;
INSERT INTO Users VALUES ('00000001', 'Bùi Dương Quốc Khánh', 'khanh@gmail.com', '0808923982', '2005-10-30', 1, 'qk', 'TP.Cần Thơ', 'Bronze');

drop table city;
SELECT * FROM City;
INSERT INTO City VALUES('1', 'Hà Nội'), ('2', 'TP.Hồ Chí Minh'), ('3', 'TP.Cần Thơ'), ('4', 'TP.Đà Nẵng'),
('5', 'TP.Hải Phòng'), ('6', 'TP.Huế'), ('7', 'An Giang'), ('8', 'Bắc Ninh'), ('9', 'Cao Bằng'),
('10', 'Cà Mau'), ('11', 'Gia Lai'), ('12', 'Hà Tĩnh'), ('13', 'Hưng Yên'), ('14', 'Điện Biên'),
('15', 'Đắk Lắk'), ('16', 'Đồng Nai'), ('17', 'Đồng Tháp'), ('18', 'Khánh Hòa'), ('19', 'Lai Châu'),
('20', 'Lào Cai'), ('21', 'Lâm Đồng'), ('22', 'Lạng Sơn'), ('23', 'Nghệ An'), ('24', 'Ninh Bình'),
('25', 'Phú Thọ'), ('26', 'Quảng Ngãi'), ('27', 'Quảng Ninh'), ('28', 'Quảng Trị'), ('29', 'Sơn La'),
('30', 'Thanh Hóa'), ('31', 'Thái Nguyên'), ('32', 'Tuyên Quang'), ('33', 'Tây Ninh'), ('34', 'Vĩnh Long');
COMMIT;

delimiter @
CREATE PROCEDURE add_new_users (IN id varchar(8), IN user_fullname varchar(50), IN user_email varchar(30), 
IN user_phone varchar(10), IN user_birthday varchar(10), IN user_gender varchar(10), IN user_password varchar(30), 
IN user_city varchar(100))
BEGIN
	DECLARE gender_bool int;
    if user_gender = 'MALE' then
		SET gender_bool = 1;
	else
		SET gender_bool = 0;
	end if;
	INSERT INTO Users(user_id, full_name, email, phone, birthday, gender, password, city)
    VALUES (id, user_fullname, user_email, user_phone, user_birthday, gender_bool, user_password, user_city);
END@
CALL add_new_users('00000002', 'Tran Van A', 'tranvana@gmail.com', '0826327463', '1984-07-28', 'MALE', 'tva', 'An Giang');
describe users;

delimiter @
create procedure update_user_info(IN id varchar(8), IN user_fullname varchar(50), IN user_email varchar(30), 
IN user_phone varchar(10), IN user_birthday varchar(10), IN user_gender varchar(10), IN user_password varchar(30), 
IN user_city varchar(100))
BEGIN
	DECLARE gender_bool int;
    if user_gender = 'MALE' then
		SET gender_bool = 1;
	else
		SET gender_bool = 0;
	end if;
	update Users set full_name = user_fullname, email=user_email, 
    phone=user_phone, birthday=user_birthday, gender=gender_bool, password=user_password, city=user_city
    where user_id = id;
END@



