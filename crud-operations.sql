USE library;

--  CRUD
-- CREATE
-- SELECT
-- UPDATE
-- DELETE

-- deleting one by one. (Removing the relationship before)
DELETE FROM details WHERE app_user_id = 3;
DELETE FROM app_user WHERE app_user_id = 3;

-- clear the Tables
DELETE FROM details;
DELETE FROM app_user;

-- Create
INSERT INTO app_user (app_user_id, username, password, reg_date)
VALUES (1, 'simonelbrink', '1234', '2020-01-01');

INSERT INTO app_user (username, password, reg_date)
VALUES ('eriksvensson', '56789', '2019-01-01');

-- Let's update the password of eriksvensson
UPDATE app_user SET password = '65876' WHERE app_user_id = 6;

-- Find all
SELECT * FROM app_user;

-- find by username simonelbrink
-- SYNTAX- SELECT [columns] FROM [tablename] WHERE [CONDITION]

SELECT reg_date, password  FROM app_user WHERE username LIKE 's%';