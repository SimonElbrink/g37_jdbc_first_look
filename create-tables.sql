-- CREATE DB

CREATE SCHEMA library;


-- ACCESS DB
USE library;



-- DETAILS TABLE


CREATE TABLE details (
                         details_id INT NOT NULL AUTO_INCREMENT,
                         email VARCHAR(50) NOT NULL UNIQUE,
                         first_name VARCHAR(50) NOT NULL,
                         last_name VARCHAR(50) NOT NULL,
                         birth_date DATE NOT NULL,
                         app_user_id INT,
                         PRIMARY KEY (details_id)
);


-- APPUSER TABLE


CREATE TABLE app_user (
                          app_user_id INT NOT NULL AUTO_INCREMENT,
                          user_name VARCHAR(50) NOT NULL UNIQUE,
                          pass_word VARCHAR(50) NOT NULL,
                          reg_date DATE,
                          PRIMARY KEY (app_user_id)
);

CREATE TABLE book (
                      book_id INT NOT NULL AUTO_INCREMENT,
                      isbn VARCHAR(50) NOT NULL UNIQUE,
                      title VARCHAR(50) NOT NULL,
                      max_loan_days INT NOT NULL,
                      PRIMARY KEY (book_id)
);

CREATE TABLE book_loan (
                           loan_id INT NOT NULL AUTO_INCREMENT,
                           loan_date DATE NOT NULL,
                           due_date DATE,
                           returned BOOLEAN,
                           borrower_id INT,
                           book_id INT,
                           PRIMARY KEY (loan_id)
);


-- SETTING FOREIGN KEY etc. (Relationships)

ALTER TABLE details
    ADD CONSTRAINT FK_DETAILS_APPUSER FOREIGN KEY (app_user_id)
        REFERENCES app_user(app_user_id);

ALTER TABLE book_loan
    ADD CONSTRAINT FK_BOOKLOAN_APPUSER
        FOREIGN KEY (borrower_id) REFERENCES app_user(app_user_id);

ALTER TABLE book_loan
    ADD CONSTRAINT FK_BOOKLOAN_BOOK
        FOREIGN KEY (book_id) REFERENCES book(book_id);