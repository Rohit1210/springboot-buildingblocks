INSERT INTO CLIENT
(ID, EMAIL_ADDRESS, FIRST_NAME, LAST_NAME, ROLE, SSN, USER_NAME)
VALUES
(101, 'rohit@example.com', 'Rohit', 'Tiwari', 'ADMIN', 'ssn101', 'rohit123');

INSERT INTO CLIENT
(ID, EMAIL_ADDRESS, FIRST_NAME, LAST_NAME, ROLE, SSN, USER_NAME)
VALUES
(102, 'adu@example.com', 'Aditi', 'Sharma', 'ADMIN', 'ssn102', 'aditi123');

INSERT INTO CLIENT
(ID, EMAIL_ADDRESS, FIRST_NAME, LAST_NAME, ROLE, SSN, USER_NAME)
VALUES
(103, 'sam@example.com', 'Sam', 'Gupta', 'ADMIN', 'ssn103', 'sam123');

INSERT INTO orders values(2001, 'order11', 101);
INSERT INTO orders values(2002, 'order12', 101);
INSERT INTO orders values(2003, 'order13', 101);
INSERT INTO orders values(2004, 'order14', 102);
INSERT INTO orders values(2005, 'order15', 102);
INSERT INTO orders values(2006, 'order16', 103);