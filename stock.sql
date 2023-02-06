-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Feb 05, 2023 at 09:50 PM
-- Server version: 10.4.25-MariaDB
-- PHP Version: 8.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `stock`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `id` int(11) NOT NULL,
  `mail` varchar(50) NOT NULL,
  `passwd` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`id`, `mail`, `passwd`) VALUES
(1, 'admin@admin.tn', 'admin');

-- --------------------------------------------------------

--
-- Table structure for table `bonEntree`
--

CREATE TABLE `bonEntree` (
  `id` int(11) NOT NULL,
  `nomP` varchar(50) NOT NULL,
  `qteE` int(11) NOT NULL,
  `dateE` date NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `bonEntree`
--

INSERT INTO `bonEntree` (`id`, `nomP`, `qteE`, `dateE`) VALUES
(1, 'PC DELL ', 2, '2023-01-01'),
(2, 'stylos feutre', 40, '2023-01-01'),
(3, 'a', 1, '2023-01-02'),
(4, 'PC DELL ', 5, '2023-01-03'),
(5, 'MONTRE MIXTE SWATCH', 48, '2023-01-09'),
(6, 'b', 22, '2023-01-09');

-- --------------------------------------------------------

--
-- Table structure for table `bonSortie`
--

CREATE TABLE `bonSortie` (
  `id` int(11) NOT NULL,
  `nomP` varchar(50) NOT NULL,
  `qteS` int(11) NOT NULL,
  `dateS` date NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `bonSortie`
--

INSERT INTO `bonSortie` (`id`, `nomP`, `qteS`, `dateS`) VALUES
(1, 'PC LENOVO', 1, '2023-01-01'),
(2, 'RÉFRIGÉRATEUR', 1, '2023-01-01'),
(3, 'RÉFRIGÉRATEUR', 2, '2023-01-01'),
(4, 'RÉFRIGÉRATEUR', 3, '2023-01-01'),
(5, 'a', 1, '2023-01-02'),
(6, 'PC LENOVO', 3, '2023-01-09'),
(7, 'b', 2, '2023-01-09');

-- --------------------------------------------------------

--
-- Table structure for table `produit`
--

CREATE TABLE `produit` (
  `id` int(11) NOT NULL,
  `nomP` varchar(50) NOT NULL,
  `couleurP` varchar(50) NOT NULL,
  `categorieP` varchar(50) NOT NULL,
  `fournisseurP` varchar(50) NOT NULL,
  `qteP` int(11) NOT NULL,
  `prixUP` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `produit`
--

INSERT INTO `produit` (`id`, `nomP`, `couleurP`, `categorieP`, `fournisseurP`, `qteP`, `prixUP`) VALUES
(11, 'MONTRE MIXTE SWATCH', 'noir', 'Montres', 'f4', 50, 120),
(12, 'PC DELL ', 'noir', 'Informatique', 'f2', 10, 1699),
(16, 'stylos feutre', 'jaune', 'bureautique', 'f1', 40, 2300),
(18, 'b', 'bb', 'bbbb', 'bbbbbb', 20, 20);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `mail` (`mail`);

--
-- Indexes for table `bonEntree`
--
ALTER TABLE `bonEntree`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `bonSortie`
--
ALTER TABLE `bonSortie`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `produit`
--
ALTER TABLE `produit`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admin`
--
ALTER TABLE `admin`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `bonEntree`
--
ALTER TABLE `bonEntree`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `bonSortie`
--
ALTER TABLE `bonSortie`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `produit`
--
ALTER TABLE `produit`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
