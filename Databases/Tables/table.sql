-- STORE
CREATE TABLE STORE (
    sId INT PRIMARY KEY,
    Sname VARCHAR(100),
    Street VARCHAR(100),
    City VARCHAR(50),
    StateAb CHAR(2),
    ZipCode VARCHAR(10),
    Sdate DATE,
    Telno VARCHAR(15),
    URL VARCHAR(255)
);

-- VENDOR
CREATE TABLE VENDOR (
    vId INT PRIMARY KEY,
    Vname VARCHAR(100),
    Street VARCHAR(100),
    City VARCHAR(50),
    StateAb CHAR(2),
    ZipCode VARCHAR(10),
    Vemail VARCHAR(100),
    VTelNo VARCHAR(15)
);

-- VENDOR_STORE
CREATE TABLE VENDOR_STORE (
    vId INT,
    sId INT,
    PRIMARY KEY (vId, sId),
    FOREIGN KEY (vId) REFERENCES VENDOR(vId),
    FOREIGN KEY (sId) REFERENCES STORE(sId)
);

-- CONTRACT
CREATE TABLE CONTRACT (
    vId INT,
    ctId INT,
    Sdate DATE,
    Ctime TIME,
    Ctname VARCHAR(100),
    PRIMARY KEY (vId, ctId),
    FOREIGN KEY (vId) REFERENCES VENDOR(vId)
);


-- ITEM
CREATE TABLE ITEM (
    iId INT PRIMARY KEY,
    Iname VARCHAR(100),
    Sprice DECIMAL(10,2),
    Idescription VARCHAR(255)
);

-- VENDOR_ITEM 
CREATE TABLE VENDOR_ITEM (
    vId INT,
    iId INT,
    Vprice DECIMAL(10,2),
    PRIMARY KEY (vId, iId),
    FOREIGN KEY (vId) REFERENCES VENDOR(vId),
    FOREIGN KEY (iId) REFERENCES ITEM(iId)
);

-- CUSTOMER
CREATE TABLE CUSTOMER (
    cId INT PRIMARY KEY,
    Cname VARCHAR(100),
    Street VARCHAR(100),
    City VARCHAR(50),
    StateAb CHAR(2),
    ZipCode VARCHAR(10),
    CTelNo VARCHAR(15),
    Cdob DATE
);

-- STORE_CUSTOMER 
CREATE TABLE STORE_CUSTOMER (
    sId INT,
    cId INT,
    PRIMARY KEY (sId, cId),
    FOREIGN KEY (sId) REFERENCES STORE(sId),
    FOREIGN KEY (cId) REFERENCES CUSTOMER(cId)
);

-- ORDERS
CREATE TABLE ORDERS (
    oId INT PRIMARY KEY,
    sId INT,
    cId INT,
    Odate DATE,
    Ddate DATE,
    Amount DECIMAL(10,2),
    FOREIGN KEY (sId) REFERENCES STORE(sId),
    FOREIGN KEY (cId) REFERENCES CUSTOMER(cId)
);

-- ORDER_ITEM 
CREATE TABLE ORDER_ITEM (
    oId INT,
    sId INT,
    iId INT,
    Icount INT,
    PRIMARY KEY (oId, sId, iId),
    FOREIGN KEY (oId) REFERENCES ORDERS(oId),
    FOREIGN KEY (sId) REFERENCES STORE(sId),
    FOREIGN KEY (iId) REFERENCES ITEM(iId)
);

-- STORE_ITEM 
CREATE TABLE STORE_ITEM (
    sId INT,
    iId INT,
    Scount INT,
    PRIMARY KEY (sId, iId),
    FOREIGN KEY (sId) REFERENCES STORE(sId),
    FOREIGN KEY (iId) REFERENCES ITEM(iId)
);

-- EMPLOYEE
CREATE TABLE EMPLOYEE (
    sId INT,
    SSN CHAR(9) PRIMARY KEY,
    Ename VARCHAR(100),
    Street VARCHAR(100),
    City VARCHAR(50),
    StateAb CHAR(2),
    ZipCode VARCHAR(10),
    Etype CHAR(1),                
    Bdate DATE,
    Sdate DATE,
    Edate DATE,
    ELevel VARCHAR(50),
    Asalary DECIMAL(10,2),
    Agency VARCHAR(100),
    Hsalary DECIMAL(10,2),
    Institute VARCHAR(100),
    Itype CHAR(1),
    TelNo VARCHAR(15),
    Email VARCHAR(100),
    FOREIGN KEY (sId) REFERENCES STORE(sId)
);

-- FEEDBACK
CREATE TABLE FEEDBACK (
    cId INT,
    rId INT,
    Rating INT CHECK (Rating BETWEEN 1 AND 5),
    Comments VARCHAR(255),
    PRIMARY KEY (cId, rId),
    FOREIGN KEY (cId) REFERENCES CUSTOMER(cId)
);
