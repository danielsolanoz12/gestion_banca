-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Feb 26, 2020 at 03:35 AM
-- Server version: 10.1.37-MariaDB
-- PHP Version: 7.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_prueba`
--

-- --------------------------------------------------------

--
-- Table structure for table `persona`
--

CREATE TABLE `personas` (
  `personaId` int(11) NOT NULL,
  `primerNombre` varchar(30) COLLATE utf8_bin NOT NULL,
  `segundoNombre` varchar(30) COLLATE utf8_bin,
  `primerApellido` varchar(30) COLLATE utf8_bin NOT NULL,
  `segundoApellido` varchar(30) COLLATE utf8_bin NOT NULL,
  `genero` enum('M','F','O') COLLATE utf8_bin NOT NULL DEFAULT 'O',
  `feechaNacimiento` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dumping data for table `persona`
--

INSERT INTO `personas` (`personaId`, `primerNombre`, `segundoNombre`, `primerApellido`,`segundoApellido`,`genero`,`fechaNacimiento`) VALUES
(1, 'Fernando','','Martinez','Muñoz', 'F', '1978-09-13'),
(2, 'Alex','Mauricio','Fernandez','Holgin','M', '1985-09-20'),
(3, 'Prueba','Neoris','Burbano','Zuñiga', 'F', '1997-09-19');

-- --------------------------------------------------------

--
-- Table structure for table `cliente`
--

CREATE TABLE `cliente` (
  `clienteId` int(11) NOT NULL,
  `personaId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Table structure for table `cuenta`
--

CREATE TABLE `cuentas` (
  `cuentaId` int(11) NOT NULL,
  `numeroCuenta` varchar(20) COLLATE utf8_bin NOT NULL,
  `tipoCuenta` varchar(20) COLLATE utf8_bin NOT NULL,
  `clienteId` varchar(20) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dumping data for table `categoria`
--

INSERT INTO `cuenta` (`cuentaId`, `numeroCuenta`, `tipoCuenta`, `clienteId`) VALUES
(3, '1232324334',"ahorros",2),
(2, '3243553455',"nomina",3),
(7, '6778383828',"nomina",4),
(30, '1000222283',"ahorros",5);

-- --------------------------------------------------------

--
-- Table structure for table `movimientos`
--

CREATE TABLE `movimientos` (
  `movimientoId` int(11) NOT NULL,
  `descripcion` varchar(30) COLLATE utf8_bin NOT NULL,
  `cuentaId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dumping data for table `movimientos`
--

INSERT INTO `movimientos` (`movimientoId`, `descripcion`,`cuentaId`) VALUES
(6, 'transeferencia', 3),
(7, 'Pago pse', 7);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `persona`
--
ALTER TABLE `persona`
  ADD PRIMARY KEY (`personaId`);

--
-- Indexes for table `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`clienteId`),
  ADD KEY `personaId` (`personaId`);

--
-- Indexes for table `cuentas`
--
ALTER TABLE `cuentas`
  ADD PRIMARY KEY (`cuentaId`),
  ADD UNIQUE KEY `numeroCuenta` (`numeroCuenta`),
  ADD UNIQUE KEY `tipoCuenta` (`tipoCuenta`),
  ADD UNIQUE KEY `saldo` (`saldo`),
  ADD KEY `clienteId` (`clienteId`);

--
-- Indexes for table `movimientos`
--
ALTER TABLE `movimientos`
  ADD PRIMARY KEY (`movimientoId`),
  ADD UNIQUE KEY `descripcion` (`descripcion`),
  ADD UNIQUE KEY `valorMovimiento` (`valorMovimiento`),
  ADD UNIQUE KEY `fechaMovimiento` (`fechaMovimiento`),
  ADD KEY `cuentaId` (`cuentaId`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `personas`
--
ALTER TABLE `personas`
  MODIFY `personaId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `cliente`
--
ALTER TABLE `cliente`
  MODIFY `clienteId` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `categoria`
--
ALTER TABLE `cuentas`
  MODIFY `cuentaId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=34;

--
-- AUTO_INCREMENT for table `movimientos`
--
ALTER TABLE `movimientos`
  MODIFY `movimientoId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `cliente`
--
ALTER TABLE `cliente`
  ADD CONSTRAINT `fk_cliente` FOREIGN KEY (`personaId`) REFERENCES `personas` (`personaId`);

--
-- Constraints for table `movimientos`
--
ALTER TABLE `movimientos`
  ADD CONSTRAINT `fk_movimientos` FOREIGN KEY (`cuentaId`) REFERENCES `cuentas` (`cuentaId`);
COMMIT;

--
-- Constraints for table `cuentas`
--
ALTER TABLE `cuentas`
    ADD CONSTRAINT `fk_cuentas` FOREIGN KEY (`clienteId`) REFERENCES `cliente` (`clienteId`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
