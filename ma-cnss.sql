-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 04, 2022 at 03:04 PM
-- Server version: 10.4.22-MariaDB
-- PHP Version: 8.1.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `ma-cnss`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
                         `id` int(11) NOT NULL,
                         `username` varchar(30) NOT NULL,
                         `email` varchar(200) NOT NULL,
                         `password` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`id`, `username`, `email`, `password`) VALUES
                                                                (1, 'ibrahim_ben', 'ibrahim@admin.com', '1234'),
                                                                (2, '', 'admin', 'admin');

-- --------------------------------------------------------

--
-- Table structure for table `agent`
--

CREATE TABLE `agent` (
                         `id` int(30) NOT NULL,
                         `username` varchar(100) NOT NULL,
                         `email` varchar(100) NOT NULL,
                         `password` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `agent`
--

INSERT INTO `agent` (`id`, `username`, `email`, `password`) VALUES
                                                                (1, 'hamza', 'hamza@agent.com', '1234'),
                                                                (3, 'iman', 'ima@agent.com', '1234'),
                                                                (4, 'walter', 'walter@agent.com', 'walter');

-- --------------------------------------------------------

--
-- Table structure for table `analysis`
--

CREATE TABLE `analysis` (
                            `id` int(30) NOT NULL,
                            `name` varchar(100) NOT NULL,
                            `percentage` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `dossier`
--

CREATE TABLE `dossier` (
                           `id` int(30) NOT NULL,
                           `patient_id` int(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `dossier_analysis`
--

CREATE TABLE `dossier_analysis` (
                                    `id` int(30) NOT NULL,
                                    `dossier_id` int(30) NOT NULL,
                                    `analysis_id` int(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `dossier_medication`
--

CREATE TABLE `dossier_medication` (
                                      `id` int(30) NOT NULL,
                                      `dossier_id` int(30) NOT NULL,
                                      `midication_id` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `dossier_radio`
--

CREATE TABLE `dossier_radio` (
                                 `id` int(30) NOT NULL,
                                 `dossier_id` int(30) NOT NULL,
                                 `radio_id` int(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `dossier_specialty`
--

CREATE TABLE `dossier_specialty` (
                                     `id` int(30) NOT NULL,
                                     `dossier_id` int(30) NOT NULL,
                                     `specialty_id` int(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `medication`
--

CREATE TABLE `medication` (
                              `bar_code` varchar(100) NOT NULL,
                              `name` varchar(30) NOT NULL,
                              `repayment` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `patient`
--

CREATE TABLE `patient` (
                           `id` int(30) NOT NULL,
                           `mat` int(100) NOT NULL,
                           `username` varchar(30) NOT NULL,
                           `email` varchar(30) DEFAULT NULL,
                           `password` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `patient`
--

INSERT INTO `patient` (`id`, `mat`, `username`, `email`, `password`) VALUES
    (1, 1181155155, 'YASSINE', 'YASSINE@GMAIL.COM', '1234');

-- --------------------------------------------------------

--
-- Table structure for table `radio`
--

CREATE TABLE `radio` (
                         `id` int(30) NOT NULL,
                         `name` varchar(100) NOT NULL,
                         `percentage` int(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `specialty`
--

CREATE TABLE `specialty` (
                             `id` int(30) NOT NULL,
                             `name` varchar(100) NOT NULL,
                             `repayment` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
    ADD PRIMARY KEY (`id`);

--
-- Indexes for table `agent`
--
ALTER TABLE `agent`
    ADD PRIMARY KEY (`id`);

--
-- Indexes for table `analysis`
--
ALTER TABLE `analysis`
    ADD PRIMARY KEY (`id`);

--
-- Indexes for table `dossier`
--
ALTER TABLE `dossier`
    ADD PRIMARY KEY (`id`),
    ADD KEY `fk_dossier_patient` (`patient_id`);

--
-- Indexes for table `dossier_analysis`
--
ALTER TABLE `dossier_analysis`
    ADD PRIMARY KEY (`id`),
    ADD KEY `fk_dossieranaly_dossier` (`dossier_id`),
    ADD KEY `fk_dossieranaly_analysis` (`analysis_id`);

--
-- Indexes for table `dossier_medication`
--
ALTER TABLE `dossier_medication`
    ADD PRIMARY KEY (`id`),
    ADD KEY `dossier_id` (`dossier_id`,`midication_id`),
    ADD KEY `fk_medicationdos_medication` (`midication_id`);

--
-- Indexes for table `dossier_radio`
--
ALTER TABLE `dossier_radio`
    ADD PRIMARY KEY (`id`),
    ADD KEY `fk_dossieradio_dossier` (`dossier_id`),
    ADD KEY `fk_dossieradio_radio` (`radio_id`);

--
-- Indexes for table `dossier_specialty`
--
ALTER TABLE `dossier_specialty`
    ADD PRIMARY KEY (`id`),
    ADD KEY `fk_dossierspec_dossier` (`dossier_id`),
    ADD KEY `fk_dossierspec_spec` (`specialty_id`);

--
-- Indexes for table `medication`
--
ALTER TABLE `medication`
    ADD PRIMARY KEY (`bar_code`);

--
-- Indexes for table `patient`
--
ALTER TABLE `patient`
    ADD PRIMARY KEY (`id`);

--
-- Indexes for table `radio`
--
ALTER TABLE `radio`
    ADD PRIMARY KEY (`id`);

--
-- Indexes for table `specialty`
--
ALTER TABLE `specialty`
    ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admin`
--
ALTER TABLE `admin`
    MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `agent`
--
ALTER TABLE `agent`
    MODIFY `id` int(30) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `analysis`
--
ALTER TABLE `analysis`
    MODIFY `id` int(30) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `dossier`
--
ALTER TABLE `dossier`
    MODIFY `id` int(30) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `dossier_analysis`
--
ALTER TABLE `dossier_analysis`
    MODIFY `id` int(30) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `dossier_medication`
--
ALTER TABLE `dossier_medication`
    MODIFY `id` int(30) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `dossier_radio`
--
ALTER TABLE `dossier_radio`
    MODIFY `id` int(30) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `dossier_specialty`
--
ALTER TABLE `dossier_specialty`
    MODIFY `id` int(30) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `patient`
--
ALTER TABLE `patient`
    MODIFY `id` int(30) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `radio`
--
ALTER TABLE `radio`
    MODIFY `id` int(30) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `specialty`
--
ALTER TABLE `specialty`
    MODIFY `id` int(30) NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `dossier`
--
ALTER TABLE `dossier`
    ADD CONSTRAINT `fk_dossier_patient` FOREIGN KEY (`patient_id`) REFERENCES `patient` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `dossier_analysis`
--
ALTER TABLE `dossier_analysis`
    ADD CONSTRAINT `fk_dossieranaly_analysis` FOREIGN KEY (`analysis_id`) REFERENCES `analysis` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
    ADD CONSTRAINT `fk_dossieranaly_dossier` FOREIGN KEY (`dossier_id`) REFERENCES `dossier` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `dossier_medication`
--
ALTER TABLE `dossier_medication`
    ADD CONSTRAINT `fk_medicationdos_dossier` FOREIGN KEY (`dossier_id`) REFERENCES `dossier` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
    ADD CONSTRAINT `fk_medicationdos_medication` FOREIGN KEY (`midication_id`) REFERENCES `medication` (`bar_code`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `dossier_radio`
--
ALTER TABLE `dossier_radio`
    ADD CONSTRAINT `fk_dossieradio_dossier` FOREIGN KEY (`dossier_id`) REFERENCES `dossier` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
    ADD CONSTRAINT `fk_dossieradio_radio` FOREIGN KEY (`radio_id`) REFERENCES `radio` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `dossier_specialty`
--
ALTER TABLE `dossier_specialty`
    ADD CONSTRAINT `fk_dossierspec_dossier` FOREIGN KEY (`dossier_id`) REFERENCES `dossier` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
    ADD CONSTRAINT `fk_dossierspec_spec` FOREIGN KEY (`specialty_id`) REFERENCES `specialty` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
