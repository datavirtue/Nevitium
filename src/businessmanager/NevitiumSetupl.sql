CREATE TABLE INVENTORY 
(
INVENTORY_ID INT NOT NULL PRIMARY KEY IDENTITY, UPC VARCHAR(14), CODE VARCHAR(16), 
DESCRIPTION VARCHAR(300), SIZE VARCHAR(15), WEIGHT VARCHAR(15), 
ONHAND REAL, COST DECIMAL(19,4), PRICE DECIMAL(19,4),
CATEGORY VARCHAR(30), TAX1 BOOLEAN, TAX2 BOOLEAN,
AVAILABLE BOOLEAN, LAST_SALE BIGINT, LAST_RECEIVED BIGINT,
CUTOFF REAL, PARTIAL_QTY_ALLOWED BOOLEAN
);

CREATE TABLE INVENTORY_IMAGES
(
INVENTORY_IMAGES_ID INT NOT NULL PRIMARY KEY IDENTITY, 
INVENTORY_ID INT CONSTRAINT INVENTORY_IMAGES_REF REFERENCES INVENTORY(INVENTORY_ID) ON DELETE CASCADE ON UPDATE RESTRICT,
TITLE VARCHAR(128), DATE BIGINT, TYPE VARCHAR(3), BITMAP BLOB
);

CREATE TABLE CONNECTIONS
(
CONNECTIONS_ID INT NOT NULL PRIMARY KEY IDENTITY, COMPANY VARCHAR(40), FIRST_NAME VARCHAR(20), LAST_NAME VARCHAR(30),
ADDRESS VARCHAR(40), ADDRESS_2 VARCHAR(40), CITY VARCHAR(30),
STATE VARCHAR(20), POSTCODE VARCHAR(10), COUNTRY VARCHAR(2), CONTACT VARCHAR(30),
PHONE VARCHAR(20), MOBILE VARCHAR(20), FAX VARCHAR(20),
EMAIL VARCHAR(50), WEBSITE VARCHAR(100), NOTE VARCHAR(32000),
CUSTOMER BOOLEAN, SUPPLIER BOOLEAN, TAX1 BOOLEAN, TAX2 BOOLEAN, PICTURE BLOB

);

CREATE TABLE INVENTORY_SUPPLIERS
(
INVENTORY_SUPPLIERS_ID INT NOT NULL PRIMARY KEY IDENTITY, 
INVENTORY_ID INT CONSTRAINT INVENTORY_SUPPLIERS_INVENTORY_REF REFERENCES INVENTORY(INVENTORY_ID)ON DELETE CASCADE ON UPDATE RESTRICT,
CONNECTIONS_ID INT CONSTRAINT INVENTORYY_SUPPLIERS_CONNECTIONS_REF REFERENCES CONNECTIONS(CONNECTIONS_ID) ON DELETE CASCADE ON UPDATE RESTRICT
);

CREATE TABLE CONNECTIONS_SHIPPING
(
CONNECTIONS_SHIPPING_ID INT NOT NULL PRIMARY KEY IDENTITY, CONNECTIONS_ID INT CONSTRAINT CONNECTIONS_SHIPPING_REF REFERENCES CONNECTIONS(CONNECTIONS_ID)ON DELETE CASCADE ON UPDATE RESTRICT,
COMPANY VARCHAR(40), FIRST_NAME VARCHAR(20), LAST_NAME VARCHAR(30),
ADDRESS VARCHAR(40), ADDRESS_2 VARCHAR(40), CITY VARCHAR(30),
STATE VARCHAR(20), POSTCODE VARCHAR(10), COUNTRY VARCHAR(2), CONTACT VARCHAR(30),
MOBILE1 VARCHAR(20), MOBILE2 VARCHAR(20), FAX VARCHAR(20),
EMAIL VARCHAR(50), WEBSITE VARCHAR(100), NOTE VARCHAR(32000), DEFAULT_SHIPPING_ADDRESS BOOLEAN
);

CREATE TABLE CONNECTIONS_DOCUMENTS
(
CONNECTIONS_DOCUMENTS_ID INT NOT NULL PRIMARY KEY IDENTITY, CONNECTIONS_ID INT NOT NULL CONSTRAINT CONNECTIONS_DOCUMENTS_REF REFERENCES CONNECTIONS(CONNECTIONS_ID) ON DELETE CASCADE ON UPDATE RESTRICT,
SUBJECT VARCHAR(80), DATE BIGINT, APPLICATION VARCHAR(30), TYPE VARCHAR(3), TEXT_DOCUMENT CLOB, BIN_DOCUMENT BLOB 
);

CREATE TABLE DEBIT_ACCOUNTS
(
DEBIT_ACCOUNTS_ID INT NOT NULL PRIMARY KEY IDENTITY, CONNECTIONS_ID INT NOT NULL CONSTRAINT DEBIT_ACCOUNTS_REF REFERENCES CONNECTIONS(CONNECTIONS_ID) ON DELETE CASCADE ON UPDATE RESTRICT,
ACCOUNT_NUMBER VARCHAR(16), INCEPTION_DATE BIGINT, NOTES VARCHAR(32000)
);
---- LIABILITY ACOUNT / BEGINNING BALANCE IS A CREDIT----
CREATE TABLE DEBIT_ACCOUNT_LEDGER
(
DEBIT_ACCOUNTS_LEDGER_ID INT NOT NULL PRIMARY KEY IDENTITY, DEBIT_ACCOUNTS_ID INT CONSTRAINT DEBIT_ACCOUNTS_LEDGER_REF REFERENCES DEBIT_ACCOUNTS(DEBIT_ACCOUNTS_ID) ON DELETE CASCADE ON UPDATE RESTRICT,
DATE BIGINT, MEMO VARCHAR(40), DEBIT DECIMAL(19,4), CREDIT DECIMAL(19,4)
);

CREATE TABLE CREDIT_ACCOUNTS
(
CREDIT_ACCOUNTS_ID INT NOT NULL PRIMARY KEY IDENTITY, CONNECTIONS_ID INT CONSTRAINT CREDIT_ACCOUNTS_REF REFERENCES CONNECTIONS(CONNECTIONS_ID) ON DELETE CASCADE ON UPDATE RESTRICT,
ACCOUNT_NUMBER VARCHAR(16), INCEPTION_DATE BIGINT, NOTES VARCHAR(32000)
);
---- ASSET ACCOUNT / BEGINNING BALANCE IS A DEBIT ----
CREATE TABLE CREDIT_ACCOUNT_LEDGER
(
CREDIT_ACCOUNT_LEDGER_ID INT NOT NULL PRIMARY KEY IDENTITY, CREDIT_ACCOUNTS_ID INT CONSTRAINT CREDIT_ACCOUNTS_LEDGER_REF REFERENCES CREDIT_ACCOUNTS(CREDIT_ACCOUNTS_ID) ON DELETE CASCADE ON UPDATE RESTRICT,
DATE BIGINT, MEMO VARCHAR(40), DEBIT DECIMAL(19,4), CREDIT DECIMAL(19,4), CREDIT_LIMIT DECIMAL(19,4)
);

CREATE TABLE PROJECTS
(
PROJECTS_ID INT NOT NULL PRIMARY KEY IDENTITY, CONNECTIONS_ID INT CONSTRAINT PROJECTS_REF REFERENCES CONNECTIONS(CONNECTIONS_ID) ON DELETE CASCADE ON UPDATE RESTRICT,
TITLE VARCHAR(100), MISSION VARCHAR(32000), START_DATE BIGINT, TARGET_DATE BIGINT, ACTUAL_END_DATE BIGINT, NOTES CLOB
);

CREATE TABLE INVOICES
(
INVOICES_ID INT NOT NULL PRIMARY KEY IDENTITY, CONNECTIONS_ID INT CONSTRAINT INVOICES_REF REFERENCES CONNECTIONS(CONNECTIONS_ID) ON DELETE CASCADE ON UPDATE RESTRICT,
PROJECTS_ID INT CONSTRAINT INVOICES_PROJECT_REF REFERENCES PROJECTS(PROJECTS_ID) ON DELETE CASCADE ON UPDATE RESTRICT,
INVOICE_NUMBER VARCHAR(12), DATE BIGINT, CUSTOMER VARCHAR(200), MESSAGE VARCHAR(4000), TAX1 DECIMAL(19,4), TAX2 DECIMAL(19,4),
PAID BOOLEAN, VOID BOOLEAN, QUOTE_ID INT
);

CREATE TABLE INVOICE_ITEMS
(
INVOICE_ITEMS_ID INT NOT NULL PRIMARY KEY IDENTITY, INVOICES_ID INT CONSTRAINT INVOICE_ITEMS_REF REFERENCES INVOICES(INVOICES_ID) ON DELETE CASCADE ON UPDATE RESTRICT,
DATE BIGINT, QTY_SOLD DECIMAL(19,4), CODE VARCHAR(20), DESCRIPTION VARCHAR(300), UNIT_PRICE DECIMAL(19,4), 
TAX1 BOOLEAN, TAX2 BOOLEAN, COST DECIMAL(19,4)
);

CREATE TABLE QUOTES
(
QUOTES_ID INT NOT NULL PRIMARY KEY IDENTITY, CONNECTIONS_ID INT CONSTRAINT QUOTES_REF REFERENCES CONNECTIONS(CONNECTIONS_ID) ON DELETE RESTRICT ON UPDATE RESTRICT,
INVOICE_NUMBER VARCHAR(12), DATE BIGINT, CUSTOMER VARCHAR(200), MESSAGE VARCHAR(4000), TAX1 DECIMAL(19,4), TAX2 DECIMAL(19,4)
);

CREATE TABLE QUOTE_ITEMS
(
QUOTE_ITEMS_ID INT NOT NULL PRIMARY KEY IDENTITY, QUOTES_ID INT CONSTRAINT QUOTE_ITEMS_REF REFERENCES QUOTES(QUOTES_ID) ON DELETE CASCADE ON UPDATE RESTRICT,
DATE BIGINT, QTY_SOLD DECIMAL(19,4), CODE VARCHAR(20), DESCRIPTION VARCHAR(300), UNIT_PRICE DECIMAL(19,4), 
TAX1 BOOLEAN, TAX2 BOOLEAN, COST DECIMAL(19,4)
);

CREATE TABLE INVOICE_PAYMENTS
(
INVOICE_PAYMENTS_ID INT NOT NULL PRIMARY KEY IDENTITY, INVOICES_ID INT CONSTRAINT INVOICE_PAYMENTS REFERENCES INVOICES(INVOICES_ID) ON DELETE CASCADE ON UPDATE RESTRICT,
DATE BIGINT, TYPE VARCHAR(10), MEMO VARCHAR(30), DEBIT DECIMAL(19,4), CREDIT DECIMAL(19,4)
);

CREATE TABLE INVOICE_RETURNS
(
INVOICE_RETURNS_ID INT NOT NULL PRIMARY KEY IDENTITY, INVOICES_ID INT CONSTRAINT INVOICE_RETURNS_REF REFERENCES INVOICES(INVOICES_ID) ON DELETE CASCADE ON UPDATE RESTRICT,
INVOICE_ITEMS_ID INT CONSTRAINT INVOICE_RETURNS_ITEMS_REF REFERENCES INVOICE_ITEMS(INVOICE_ITEMS_ID) ON DELETE CASCADE ON UPDATE RESTRICT,
DATE BIGINT, QTY_RETURNED DECIMAL(19,4), CODE VARCHAR(20), DESCRIPTION VARCHAR(300), CREDIT DECIMAL(19,4)
);

CREATE TABLE USER_ACCOUNTS
(
USER_ACCOUNTS_ID INT NOT NULL PRIMARY KEY IDENTITY, USERNAME VARCHAR(20), PASSWORD VARCHAR(200), MASTER BOOLEAN, INVENTORY INT, 
CONNECTIONS INT, INVOICE_MANAGER INT, INVOICES INT, QUOTES INT, REPORTS INT, CHECKS INT, EXPORTS INT, SETTINGS INT
);



