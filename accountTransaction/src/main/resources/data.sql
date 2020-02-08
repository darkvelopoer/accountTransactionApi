
 CREATE TABLE IF NOT EXISTS `CUSTOMERS`(
 	  customer_no BIGINT AUTO_INCREMENT PRIMARY KEY,
 	  customer_id VARCHAR(30) NOT NULL,
	  customer_name VARCHAR(30) NOT NULL,
	  password VARCHAR(500) NOT NULL
 );
 
 INSERT INTO CUSTOMERS (customer_id, customer_name, password) VALUES
  ('222', 'Paul Allen','8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92'),
  ('333', 'Bill Gates', '481f6cc0511143ccdd7e2d1b1b94faf0a700a8b49cd13922a70b5ae28acaa8c5');
 
CREATE TABLE IF NOT EXISTS `ACCOUNTS`(
  	  account_no BIGINT PRIMARY KEY,
  	  customer_id VARCHAR(30) NOT NULL
);

 INSERT INTO ACCOUNTS (account_no, customer_id) VALUES
  (8872838283, '222'),
  (8872838299, '222'),
  (6872838260, '333');
  
CREATE TABLE IF NOT EXISTS `TRANSACTIONS`(
	  trx_no BIGINT AUTO_INCREMENT  PRIMARY KEY,
	  account_no BIGINT,
	  amount DECIMAL,
	  description VARCHAR(50),
	  trx_date timestamp
);

INSERT INTO TRANSACTIONS (account_no, amount, description, trx_date) VALUES
  (8872838283, 12300, 'FUND TRANSFER', to_date('20190912111111', 'yyyymmddhh24miss')),
  (8872838283, 112300, 'ATM WITHDRWAL', to_date('20190911111111', 'yyyymmddhh24miss')),
  (8872838283, 122300, 'FUND TRANSFER', to_date('20191011111111', 'yyyymmddhh24miss')),
  (8872838283, 123300, '3rd Party FUND TRANSFER', to_date('20191111111111', 'yyyymmddhh24miss')),
  (8872838283, 124300, '3rd Party FUND TRANSFER', to_date('20190811111111', 'yyyymmddhh24miss')),
  (8872838283, 1255300, '3rd Party FUND TRANSFER', to_date('20190711111111', 'yyyymmddhh24miss')),
  (8872838283, 1211300, 'BILL PAYMENT', to_date('20190811111111', 'yyyymmddhh24miss')),
  (8872838299, 112122300, 'FUND TRANSFER', to_date('20190911111111', 'yyyymmddhh24miss')),
  (8872838299, 1223123300, 'FUND TRANSFER', to_date('20190911111111', 'yyyymmddhh24miss')),
  (8872838299, 11112300, 'FUND TRANSFER', to_date('20190911111111', 'yyyymmddhh24miss')),
  (8872838299, 1222300, 'BILL PAYMENT', to_date('20190911111111', 'yyyymmddhh24miss')),
  (6872838260, 100, 'BILL PAYMENT', to_date('20190911111111', 'yyyymmddhh24miss')),
  (6872838260, 122300, 'BILL PAYMENT', to_date('20190912111111', 'yyyymmddhh24miss')),
  (6872838260, 1232300, 'BILL PAYMENT', to_date('20190913111111', 'yyyymmddhh24miss')),
  (6872838260, 1232300, 'BILL PAYMENT', to_date('20190911111111', 'yyyymmddhh24miss')),
  (6872838260, 12123300, 'BILL PAYMENT', to_date('20190911111111', 'yyyymmddhh24miss')),
  (6872838260, 1992300, 'BILL PAYMENT', to_date('20190911111111', 'yyyymmddhh24miss')),
  (6872838260, 1289300, 'BILL PAYMENT', to_date('20190913111111', 'yyyymmddhh24miss')),
  (6872838260, 9912300, 'FUND TRANSFER', to_date('20190914111111', 'yyyymmddhh24miss'));
  
 
CREATE TABLE IF NOT EXISTS APP_API_AUTH_INFO (
	 AUTH_SEQ BIGINT AUTO_INCREMENT  PRIMARY KEY, 
	 MEM_NO BIGINT, 
	 CLIENT_ID VARCHAR(50) NOT NULL, 
	 ACCESS_TOKEN VARCHAR(400), 
	 REFRESH_TOKEN VARCHAR(400), 
	 CREATE_DATE timestamp, 
	 CREATE_NO BIGINT, 
	 UPDATE_DATE timestamp, 
	 UPDATE_NO BIGINT, 
	 ACCESS_TOKEN_EXPIRY timestamp,
	 REFRESH_TOKEN_EXPIRY timestamp
 )
 