CREATE DATABASE my_inventory;
USE my_inventory;

CREATE TABLE product(
	productID VARCHAR(15) PRIMARY KEY,
    name VARCHAR(30) NOT NULL,
    vendor VARCHAR(30) NOT NULL,
    price INT NOT NULL,
    stock INT
);
CREATE TABLE customers(
	Name VARCHAR(50) NOT NULL,
    Enrollno VARCHAR(15) PRIMARY KEY,
    mail VARCHAR(320) NOT NULL,
    Phno VARCHAR(15) NOT NULL,
    batch VARCHAR(10),
    Section VARCHAR(10),
    Password VARCHAR(20),
    UNIQUE(Phno)
);

CREATE TABLE orders(
	orderID INT AUTO_INCREMENT,
    Enrollno VARCHAR(15),
    productID VARCHAR(15),
    Quantity INT,
    Amount INT,
    stat VARCHAR(15),
    FOREIGN KEY (Enrollno) REFERENCES customers (Enrollno),
    FOREIGN KEY (productID) REFERENCES product(productID),
    PRIMARY KEY(orderID)
);

ALTER TABLE orders AUTO_INCREMENT = 100;

delimiter |
	CREATE TRIGGER orders_trig1
    BEFORE
    INSERT on orders
    FOR EACH ROW 
    BEGIN
    SET NEW.Amount = New.Quantity * (SELECT price 
    FROM product
    WHERE product.productID = New.productID);
    END;
|
delimiter ;

delimiter |
	CREATE TRIGGER order_trig2
    BEFORE
    INSERT ON orders
    FOR EACH ROW
    BEGIN
    UPDATE product
    SET product.stock=product.stock-NEW.quantity
    WHERE product.productID=NEW.productID;
    END;
|
delimiter ;

delimiter |
	CREATE TRIGGER orders_trig3
	BEFORE
    INSERT ON orders
    FOR EACH ROW
    BEGIN
    SET NEW.stat = 'Pending';
    END;
|
delimiter ;


INSERT INTO product VALUES('NES001','Burger','Nescafe',33,100);
INSERT INTO product VALUES('NES002','HotChocolate','Nescafe',22,100);
INSERT INTO product VALUES('NES003','Coffee','Nescafe',20,100);
INSERT INTO product VALUES('NES004','MilkShake','Nescafe',30,100);

INSERT INTO product VALUES('HOD001','Biryani','HOD',90,100);
INSERT INTO product VALUES('HOD002','KadaiPaneer','HOD',80,100);
INSERT INTO product VALUES('HOD003','Roti','HOD',15,100);
INSERT INTO product VALUES('HOD004','KadaiChicken','HOD',150,100);
INSERT INTO product VALUES('HOD005','TandooriRoti','HOD',20,100);
INSERT INTO product VALUES('HOD006','ButterNan','HOD',30,100);
INSERT INTO product VALUES('HOD007','ButterChicken','HOD',150,100);

INSERT INTO product VALUES('TMP001','ChillyPotato','TMP',40,100);
INSERT INTO product VALUES('TMP002','FrenchFries','TMP',40,100);
INSERT INTO product VALUES('TMP003','Momos','TMP',30,100);
INSERT INTO product VALUES('TMP004','ChickenRoll','TMP',90,100);

INSERT INTO customers
VALUES ('Vamsi','U101116FCS088','pskvamshi@gmail.com','9000292903','2016','S4','akkiy0j6');
INSERT INTO customers
VALUES ('Pradeep','U101116FCS089','pradeep.yadav@st.niituniversity.in','7023079907','2016','S5','Prad#771');
INSERT INTO customers
VALUES ('Prudhvith','U101116FCS142','tavvag.prudhvith@st.niituniversity.in','7737887989','2016','S6','pardhu#1998');
INSERT INTO customers
VALUES ('Yeshwanth','U101116FCS155','yeshwanth@st.niituniversity.in','9154466970','2016','S4','yeshwanth123');

INSERT INTO orders(Enrollno,productID,Quantity)
VALUES('U101116FCS142','HOD005',15);
INSERT INTO orders(Enrollno,productID,Quantity)
VALUES('U101116FCS088','HOD001',15);
INSERT INTO orders(Enrollno,productID,Quantity)
VALUES('U101116FCS089','HOD002',15);

INSERT INTO orders(Enrollno,productID,Quantity)
VALUES('U101116FCS089','NES001',10);
INSERT INTO orders(Enrollno,productID,Quantity)
VALUES('U101116FCS089','NES003',10);
INSERT INTO orders(Enrollno,productID,Quantity)
VALUES('U101116FCS142','NES002',15);

INSERT INTO orders(Enrollno,productID,Quantity)
VALUES('U101116FCS089','TMP002',10);
INSERT INTO orders(Enrollno,productID,Quantity)
VALUES('U101116FCS089','TMP003',10);
INSERT INTO orders(Enrollno,productID,Quantity)
VALUES('U101116FCS155','TMP001',10);

SELECT * FROM orders;