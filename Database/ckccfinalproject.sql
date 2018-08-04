-- phpMyAdmin SQL Dump
-- version 4.8.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Aug 04, 2018 at 06:40 AM
-- Server version: 10.1.32-MariaDB
-- PHP Version: 7.2.5

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `ckccfinalproject`
--

-- --------------------------------------------------------

--
-- Table structure for table `booking`
--

CREATE TABLE `booking` (
  `customerName` varchar(50) NOT NULL,
  `contactNo` varchar(50) NOT NULL,
  `id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `booking`
--

INSERT INTO `booking` (`customerName`, `contactNo`, `id`) VALUES
('thavath', '23421', 1),
('vath', '098435', 2),
('test', '098765', 1),
('dda', '2', 12),
('test', '023454657', 3),
('test1', '232085', 4),
('test2', '0023945', 5),
('sloy', '325246', 6),
('324', '234', 13);

-- --------------------------------------------------------

--
-- Table structure for table `bookingdetail`
--

CREATE TABLE `bookingdetail` (
  `bookingID` varchar(50) NOT NULL,
  `tableID` varchar(50) NOT NULL,
  `id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `bookingdetail`
--

INSERT INTO `bookingdetail` (`bookingID`, `tableID`, `id`) VALUES
('2', ' 002 ', 1),
('001', '#002', 2),
('001', '#12', 3),
('001', '#123', 4),
('001', '#1445', 5),
('12', '#001', 6),
('3', '#sdfaad', 7),
('4', '#sdfaad', 8),
('5', '#sdfaad', 9),
('6', '#new', 10),
('6', '#sdfaad', 11),
('13', '#1adf', 12),
('13', '#2', 13);

-- --------------------------------------------------------

--
-- Table structure for table `food`
--

CREATE TABLE `food` (
  `id` varchar(50) COLLATE utf8_german2_ci NOT NULL,
  `name` varchar(50) COLLATE utf8_german2_ci DEFAULT NULL,
  `price` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_german2_ci;

--
-- Dumping data for table `food`
--

INSERT INTO `food` (`id`, `name`, `price`) VALUES
('001', 'check', 6),
('003', 'Hello', 23),
('0034', 'asdf', 234),
('004', 'sdf', 234),
('005', 'Shukiyaki', 250),
('1SKLDJF', 'LAKSJDFL', 45),
('53354', 'sdf', 234),
('newFile', 'khg', 34),
('sadfsdf', 'update', 34),
('saf', 'sdf', 454),
('sd', 'aslk', 45),
('sdfaf', '34', 34),
('sdfh', 'khg', 34),
('Seccessful', 'Seccessful', 67),
('slajkf', 'aslkfj', 45);

-- --------------------------------------------------------

--
-- Table structure for table `order`
--

CREATE TABLE `order` (
  `id` int(10) NOT NULL,
  `table_id` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `orderdetail`
--

CREATE TABLE `orderdetail` (
  `id` int(10) NOT NULL,
  `order_id` int(10) NOT NULL,
  `food_id` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `tables`
--

CREATE TABLE `tables` (
  `id` varchar(50) COLLATE utf8_german2_ci NOT NULL,
  `numberSeats` int(11) DEFAULT NULL,
  `available` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_german2_ci;

--
-- Dumping data for table `tables`
--

INSERT INTO `tables` (`id`, `numberSeats`, `available`) VALUES
('002', 23, 0),
('12', 5, 0),
('1445', 34, 0),
('1adf', 323, 0),
('2', 7, 0),
('7675', 8, 1),
('hello', 3, 1),
('new', 13, 0),
('sdfaad', 23, 0);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `bookingdetail`
--
ALTER TABLE `bookingdetail`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `food`
--
ALTER TABLE `food`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `order`
--
ALTER TABLE `order`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `orderdetail`
--
ALTER TABLE `orderdetail`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tables`
--
ALTER TABLE `tables`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `bookingdetail`
--
ALTER TABLE `bookingdetail`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
