-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Dec 04, 2024 at 03:35 AM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.0.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `lapakmobil`
--

-- --------------------------------------------------------

--
-- Table structure for table `datamobil`
--

CREATE TABLE `datamobil` (
  `idmobil` int(11) NOT NULL,
  `merk` varchar(40) DEFAULT NULL,
  `tahun` year(4) DEFAULT NULL,
  `harga` decimal(15,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `datapelanggan`
--

CREATE TABLE `datapelanggan` (
  `idpelanggan` int(11) NOT NULL,
  `nama` varchar(40) DEFAULT NULL,
  `nik` varchar(16) DEFAULT NULL,
  `notelp` varchar(12) DEFAULT NULL,
  `alamat` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `datapelanggan`
--

INSERT INTO `datapelanggan` (`idpelanggan`, `nama`, `nik`, `notelp`, `alamat`) VALUES
(1, 'Bayu', '1608070906020001', '085758076316', 'Center Park Avenue');

-- --------------------------------------------------------

--
-- Table structure for table `datapenjualan`
--

CREATE TABLE `datapenjualan` (
  `idpenjualan` int(11) NOT NULL,
  `idpelanggan` int(11) DEFAULT NULL,
  `idmobil` int(11) DEFAULT NULL,
  `totalbiaya` decimal(15,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `datamobil`
--
ALTER TABLE `datamobil`
  ADD PRIMARY KEY (`idmobil`);

--
-- Indexes for table `datapelanggan`
--
ALTER TABLE `datapelanggan`
  ADD PRIMARY KEY (`idpelanggan`);

--
-- Indexes for table `datapenjualan`
--
ALTER TABLE `datapenjualan`
  ADD PRIMARY KEY (`idpenjualan`),
  ADD KEY `idpelanggan` (`idpelanggan`),
  ADD KEY `idmobil` (`idmobil`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `datamobil`
--
ALTER TABLE `datamobil`
  MODIFY `idmobil` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `datapelanggan`
--
ALTER TABLE `datapelanggan`
  MODIFY `idpelanggan` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `datapenjualan`
--
ALTER TABLE `datapenjualan`
  MODIFY `idpenjualan` int(11) NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `datapenjualan`
--
ALTER TABLE `datapenjualan`
  ADD CONSTRAINT `datapenjualan_ibfk_1` FOREIGN KEY (`idpelanggan`) REFERENCES `datapelanggan` (`idpelanggan`),
  ADD CONSTRAINT `datapenjualan_ibfk_2` FOREIGN KEY (`idmobil`) REFERENCES `datamobil` (`idmobil`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
