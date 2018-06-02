-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Jun 02, 2018 at 05:11 AM
-- Server version: 5.7.19
-- PHP Version: 5.6.31

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `my_inventory`
--

-- --------------------------------------------------------

--
-- Table structure for table `customers`
--

DROP TABLE IF EXISTS `customers`;
CREATE TABLE IF NOT EXISTS `customers` (
  `Name` varchar(50) NOT NULL,
  `Enrollno` varchar(15) NOT NULL,
  `mail` varchar(320) NOT NULL,
  `Phno` varchar(15) NOT NULL,
  `batch` varchar(10) DEFAULT NULL,
  `Section` varchar(10) DEFAULT NULL,
  `Password` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`Enrollno`),
  UNIQUE KEY `Phno` (`Phno`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `customers`
--

INSERT INTO `customers` (`Name`, `Enrollno`, `mail`, `Phno`, `batch`, `Section`, `Password`) VALUES
('Nikhil Reddy', 'U101116FCS156', 'nikhilreddy.maligireddy1@gmail.com', '8185043150', '2016', 'S5', 'niKILLreddy'),
('Sathwik', 'U101116FCS161', 'sathwik@gmail.com', '8121852081', '2018', 'S6', '12345678'),
('Yeshwanth', 'U101116FCS155', 'yeshwanth@st.niituniversity.in', '9154466970', '2016', 'S4', 'yeshwanth123');

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
CREATE TABLE IF NOT EXISTS `orders` (
  `orderID` int(11) NOT NULL AUTO_INCREMENT,
  `Enrollno` varchar(15) DEFAULT NULL,
  `productID` varchar(15) DEFAULT NULL,
  `Quantity` int(11) DEFAULT NULL,
  `Amount` int(11) DEFAULT NULL,
  `stat` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`orderID`),
  KEY `Enrollno` (`Enrollno`),
  KEY `productID` (`productID`)
) ENGINE=MyISAM AUTO_INCREMENT=142 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `orders`
--

INSERT INTO `orders` (`orderID`, `Enrollno`, `productID`, `Quantity`, `Amount`, `stat`) VALUES
(139, 'U101116FCS161', 'NES001', 1, 33, 'Pending'),
(140, 'U101116FCS161', 'NES002', 1, 22, 'Pending'),
(141, 'U101116FCS161', 'NES003', 1, 20, 'Complete');

--
-- Triggers `orders`
--
DROP TRIGGER IF EXISTS `order_trig2`;
DELIMITER $$
CREATE TRIGGER `order_trig2` BEFORE INSERT ON `orders` FOR EACH ROW BEGIN
    UPDATE product
    SET product.stock=product.stock-NEW.quantity
    WHERE product.productID=NEW.productID;
    END
$$
DELIMITER ;
DROP TRIGGER IF EXISTS `orders_trig1`;
DELIMITER $$
CREATE TRIGGER `orders_trig1` BEFORE INSERT ON `orders` FOR EACH ROW BEGIN
    SET NEW.Amount = New.Quantity * (SELECT price 
    FROM product
    WHERE product.productID = New.productID);
    END
$$
DELIMITER ;
DROP TRIGGER IF EXISTS `orders_trig3`;
DELIMITER $$
CREATE TRIGGER `orders_trig3` BEFORE INSERT ON `orders` FOR EACH ROW BEGIN
    SET NEW.stat = 'Pending';
    END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
CREATE TABLE IF NOT EXISTS `product` (
  `productID` varchar(15) NOT NULL,
  `name` varchar(30) NOT NULL,
  `vendor` varchar(30) NOT NULL,
  `price` int(11) NOT NULL,
  `stock` int(11) DEFAULT NULL,
  PRIMARY KEY (`productID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`productID`, `name`, `vendor`, `price`, `stock`) VALUES
('NES001', 'Burger', 'Nescafe', 33, 82),
('NES002', 'HotChocolate', 'Nescafe', 22, 82),
('NES003', 'Coffee', 'Nescafe', 20, 87),
('NES004', 'MilkShake', 'Nescafe', 30, 100),
('HOD001', 'Biryani', 'HOD', 90, 80),
('HOD002', 'KadaiPaneer', 'HOD', 80, 85),
('HOD003', 'Roti', 'HOD', 15, 97),
('HOD004', 'KadaiChicken', 'HOD', 150, 100),
('HOD005', 'TandooriRoti', 'HOD', 20, 84),
('HOD006', 'ButterNan', 'HOD', 30, 100),
('HOD007', 'ButterChicken', 'HOD', 150, 100),
('TMP001', 'ChillyPotato', 'TMP', 40, 85),
('TMP002', 'FrenchFries', 'TMP', 40, 84),
('TMP003', 'Momos', 'TMP', 30, 86),
('TMP004', 'ChickenRoll', 'TMP', 90, 99),
('NES005', 'Sandwitch', 'Nescafe', 40, 29),
('NES006', 'Cheese Sandwitch', 'Nescafe', 50, 31),
('NES007', 'Cheese Burger', 'Nescafe', 44, 20),
('NES008', 'Maggie', 'Nescafe', 20, 60),
('NES009', 'Aata Maggie', 'Nescafe', 28, 60),
('TMP005', 'Bread Omlet', 'TMP', 30, 79),
('TMP006', 'Half Fry', 'TMP', 30, 59),
('TMP007', 'Banana shake', 'TMP', 30, 60),
('TMP008', 'Egg Roll', 'TMP', 50, 60),
('HOD008', 'Paneer pakoda', 'HOD', 18, 60);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
