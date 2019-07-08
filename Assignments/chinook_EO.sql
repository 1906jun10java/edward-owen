--2.1
SELECT * FROM EMPLOYEE;
SELECT * FROM EMPLOYEE WHERE LASTNAME = 'King';
SELECT * FROM EMPLOYEE WHERE FIRSTNAME = 'Andrew' 
    AND REPORTSTO is NULL;
    
--2.2
SELECT * FROM ALBUM ORDER BY TITLE;
SELECT FIRSTNAME, CITY FROM CUSTOMER ORDER BY CITY;

--2.3
INSERT INTO GENRE VALUES (26, 'Baroque');
INSERT INTO GENRE VALUES (27, 'Stuff No One Likes');
INSERT INTO EMPLOYEE VALUES (0, 'Public', 'Jon', 'Q', 1, 
    TO_DATE('01-JAN-1901'), TO_DATE('24-MAY-2001'), '1600 Pennsylvania Ave', 
    'Capitoline', 'RI', 'Sesquipedalia', 'YZIPSTRING', '877-CashNow', 
    'ItAintthe90s', 'whyisthissolong@helpme.org');
--Only a few values check for nulls
INSERT INTO EMPLOYEE (EMPLOYEEID, REPORTSTO, LASTNAME, FIRSTNAME) 
    VALUES (9, 5, 'Are NULL', 'My other Values');
INSERT INTO CUSTOMER (CUSTOMERID, SUPPORTREPID, LASTNAME, FIRSTNAME, EMAIL)
    VALUES (0, 1, 'Importante', 'Muy', 'averyimportantcustomer@cia.gov');
INSERT INTO CUSTOMER (CUSTOMERID, SUPPORTREPID, LASTNAME, FIRSTNAME, EMAIL)
    VALUES (60, 4, 'Grossman', 'Tim', 'tim.grossman@msn.com');
    
--2.4
UPDATE CUSTOMER SET FIRSTNAME = 'ROBERT', LASTNAME = 'WALTER'
    WHERE FIRSTNAME = 'Aaron' AND LASTNAME = 'Mitchell';
UPDATE ARTIST SET NAME = 'CCR' WHERE NAME = 'Creedence Clearwater Revival';

--2.5
SELECT * FROM INVOICE WHERE BILLINGADDRESS LIKE 'T%';

--2.6
SELECT * FROM INVOICE WHERE TOTAL BETWEEN 15 AND 50;
SELECT * FROM EMPLOYEE WHERE HIREDATE 
    BETWEEN TO_DATE('1-JUN-2003') AND TO_DATE('1-MAR-2004');
    
--2.7
/*UPDATE INVOICE SET INVOICE.CUSTOMERID = -1 WHERE INVOICE.CUSTOMERID IN 
    (SELECT CUSTOMERID FROM CUSTOMER 
    WHERE FIRSTNAME = 'ROBERT' AND LASTNAME = 'WALTER'); */
--I wanted to update it to a dummy value so that we could keep the invoice
--but I couldn't get that to work
DELETE INVOICELINE WHERE INVOICEID IN 
    (SELECT INVOICEID FROM INVOICE WHERE CUSTOMERID = 32);
DELETE INVOICE WHERE INVOICE.CUSTOMERID IN
    (SELECT CUSTOMERID FROM CUSTOMER 
    WHERE FIRSTNAME = 'ROBERT' AND LASTNAME = 'WALTER');
DELETE CUSTOMER WHERE FIRSTNAME = 'ROBERT' AND LASTNAME = 'WALTER';

--3.1
CREATE OR REPLACE FUNCTION GET_SYS_TIME
RETURN VARCHAR2 AS CURR_TIME VARCHAR2(40);
BEGIN
SELECT TO_CHAR(SYSTIMESTAMP, 'hh24:mi:ss') INTO CURR_TIME FROM DUAL;
RETURN CURR_TIME;
END;
/
SELECT get_sys_time() FROM DUAL;

CREATE OR REPLACE FUNCTION GET_LENGTH
(INPUT_STR IN VARCHAR2)
RETURN NUMBER AS LENGTH_OF NUMBER(2);
BEGIN
    DECLARE COUNTER NUMBER := 0;
    BEGIN
        FOR R IN 1..LENGTH(INPUT_STR)
        LOOP
        COUNTER := COUNTER+ 1;
        END LOOP;
    RETURN COUNTER;
    END;
END;
/
SELECT GET_LENGTH(NAME) FROM MEDIATYPE WHERE MEDIATYPEID =3;

--3.2
SELECT AVG(TOTAL) FROM INVOICE;
SELECT MAX(TOTAL) FROM INVOICE;

--3.3
CREATE OR REPLACE FUNCTION GET_AVG_UNIT
RETURN NUMBER AS AVERAGE_PRICE NUMBER(10,2);
BEGIN
    DECLARE DIVISOR NUMBER := 0;
    BEGIN
        SELECT SUM(UNITPRICE) INTO AVERAGE_PRICE FROM INVOICELINE;
        SELECT COUNT(DISTINCT INVOICELINEID) INTO DIVISOR FROM INVOICELINE;
        RETURN AVERAGE_PRICE/DIVISOR;
    END;
END;
/

--3.4
SELECT * FROM EMPLOYEE WHERE BIRTHDATE >= TO_DATE('01-JAN-1969');

--4.1
CREATE OR REPLACE PROCEDURE GET_NAMES
(CURSOR_ OUT SYS_REFCURSOR)
AS
BEGIN
    OPEN CURSOR_ FOR
    SELECT FIRSTNAME, LASTNAME FROM EMPLOYEE;
END;
/
--4.2
CREATE OR REPLACE PROCEDURE UPDATE_EMPLOYEE
(EMP_ID IN NUMBER, INPUT_VAL IN VARCHAR2, COL_VAL IN VARCHAR2)
AS
BEGIN
    UPDATE EMPLOYEE SET COL_VAL = INPUT_VAL
    WHERE EMPLOYEEID = EMP_ID;
    COMMIT;
END;
/
--4.3

--5.0
CREATE OR REPLACE PROCEDURE REMOVE_INVOICELINE_WITH_ID
(IDTOREMOVE IN NUMBER)
IS
BEGIN
   EXECUTE IMMEDIATE 
   'ALTER TABLE INVOICELINE DROP CONSTRAINT FK_INVOICELINEINVOICEID';
   DELETE INVOICE WHERE INVOICEID = IDTOREMOVE;
   EXECUTE IMMEDIATE
   'ALTER TABLE INVOICELINE ADD CONSTRAINT FK_INVOICELINEINVOIDEID 
   FOREIGN KEY (INVOICELINEID) REFERENCES INVOICE(INVOICEID)';
   COMMIT;
END;
/
--6.1
CREATE OR REPLACE TRIGGER EMP_ADD_TRIG
AFTER
INSERT ON EMPLOYEE
BEGIN
COMMIT;
END;
/
CREATE OR REPLACE TRIGGER ALBUM_TRIG
AFTER INSERT ON ALBUM
BEGIN
    INSERT INTO EMPLOYEE (EMPLOYEEID, REPORTSTO, LASTNAME, FIRSTNAME) 
    VALUES(10, 5, 'SCAPEGOAT', 'BILLY');
END;
/
CREATE OR REPLACE TRIGGER CUSTOMER_TRIG
AFTER DELETE ON CUSTOMER
BEGIN
    DELETE FROM EMPLOYEE WHERE LASTNAME = 'SCAPEGOAT';
END;
/
--7.1
SELECT CUSTOMER.FIRSTNAME, CUSTOMER.LASTNAME, INVOICE.INVOICEID
FROM CUSTOMER
INNER JOIN INVOICE
ON CUSTOMER.CUSTOMERID = INVOICE.CUSTOMERID;

SELECT CUSTOMER.FIRSTNAME, CUSTOMER.LASTNAME, INVOICE.INVOICEID, INVOICE.TOTAL
FROM CUSTOMER LEFT JOIN INVOICE
ON CUSTOMER.CUSTOMERID = INVOICE.CUSTOMERID;

SELECT ALBUM.TITLE, ARTIST.NAME
FROM ALBUM RIGHT JOIN ARTIST
ON ALBUM.ARTISTID = ARTIST.ARTISTID;

SELECT * FROM ALBUM, ARTIST
CROSS JOIN ARTIST.NAME
ORDER BY ARTIST.NAME ASC;

SELECT e1.LASTNAME "Employee", e2.LASTNAME "Manager"
FROM EMPLOYEE e1 JOIN EMPLOYEE e2
ON  (e1.REPORTSTO = e2.EMPLOYEEID);