
CREATE DATABASE money_transfer_db;
USE money_transfer_db;

CREATE TABLE IF NOT EXISTS accounts (
	id INT NOT NULL AUTO_INCREMENT,
	account_number VARCHAR(20) NOT NULL UNIQUE,
	balance DECIMAL(10, 2) NOT NULL,
	PRIMARY KEY(id)
)ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS transfers (
	id BIGINT NOT NULL AUTO_INCREMENT,
    transfer_timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	source_account_id INT NOT NULL,
	destination_account_id INT NOT NULL,
	amount DECIMAL(10, 2) NOT NULL,
	PRIMARY KEY(id),
    FOREIGN KEY (source_account_id) REFERENCES accounts(id),
    FOREIGN KEY (destination_account_id) REFERENCES accounts(id)
)ENGINE=InnoDB;