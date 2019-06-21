-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 19, 2019 at 08:36 AM
-- Server version: 10.1.29-MariaDB
-- PHP Version: 7.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_attendance`
--

-- --------------------------------------------------------

--
-- Table structure for table `attendance_table`
--

CREATE TABLE `attendance_table` (
  `id` int(11) NOT NULL,
  `student_id` int(11) NOT NULL,
  `subject_id` int(11) NOT NULL,
  `time` varchar(1000) NOT NULL,
  `date` varchar(700) NOT NULL,
  `type` int(11) NOT NULL,
  `attendance` varchar(700) NOT NULL,
  `created_by` varchar(1000) NOT NULL,
  `modified_by` varchar(1000) NOT NULL,
  `created_on` varchar(1000) NOT NULL,
  `modified_on` varchar(1000) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `attendance_table`
--

INSERT INTO `attendance_table` (`id`, `student_id`, `subject_id`, `time`, `date`, `type`, `attendance`, `created_by`, `modified_by`, `created_on`, `modified_on`) VALUES
(1, 1, 1, '00:00', '00/00/0000', 1, '0', '', '', '', ''),
(6, 1, 2, '00:00', '00/00/0000', 0, '1', '', '', '', ''),
(10, 1, 1, '2:0', '4/3/2019', 0, '1', '', '', '', ''),
(11, 2, 1, '2:0', '4/3/2019', 0, '0', '', '', '', ''),
(18, 1, 1, '11:5', '4/5/2019', 0, '1', '', '', '', ''),
(19, 2, 1, '11:5', '4/5/2019', 0, '1', '', '', '', ''),
(22, 2, 1, 'Select Time ', '4/5/2019', 0, '0', '', '', '', ''),
(23, 1, 1, 'Select Time ', '4/5/2019', 0, '0', '', '', '', ''),
(30, 1, 1, 'Select Time ', 'Select a Date ', 0, '0', '', '', '', ''),
(31, 2, 1, 'Select Time ', 'Select a Date ', 0, '0', '', '', '', ''),
(32, 1, 1, '3:23', '4/4/2009', 0, '1', '', '', '', ''),
(33, 2, 1, '3:23', '4/4/2009', 0, '1', '', '', '', '');

-- --------------------------------------------------------

--
-- Table structure for table `student_sub_table`
--

CREATE TABLE `student_sub_table` (
  `relation_id` int(11) NOT NULL,
  `student_id` int(11) NOT NULL,
  `subject_id` int(11) NOT NULL,
  `created_by` varchar(1000) NOT NULL,
  `modified_by` varchar(1000) NOT NULL,
  `created_on` varchar(1000) NOT NULL,
  `modified_on` varchar(1000) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `student_sub_table`
--

INSERT INTO `student_sub_table` (`relation_id`, `student_id`, `subject_id`, `created_by`, `modified_by`, `created_on`, `modified_on`) VALUES
(1, 1, 1, '', '', '', ''),
(2, 1, 2, '', '', '', ''),
(5, 2, 1, '', '', '', ''),
(6, 2, 3, '', '', '', ''),
(7, 3, 4, '', '', '', ''),
(8, 4, 5, '', '', '', ''),
(11, 1, 3, '', '', '', ''),
(12, 4, 4, '', '', '', ''),
(15, 2, 4, '', '', '', ''),
(16, 3, 5, '', '', '', '');

-- --------------------------------------------------------

--
-- Table structure for table `student_table`
--

CREATE TABLE `student_table` (
  `student_id` int(11) NOT NULL,
  `enrollment` varchar(50) NOT NULL,
  `name` varchar(1000) NOT NULL,
  `branch` varchar(30) NOT NULL,
  `semester` varchar(100) NOT NULL,
  `year` varchar(1000) NOT NULL,
  `created_by` varchar(1000) NOT NULL,
  `modified_by` varchar(1000) NOT NULL,
  `created_on` varchar(1000) NOT NULL,
  `modified_on` varchar(1000) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `student_table`
--

INSERT INTO `student_table` (`student_id`, `enrollment`, `name`, `branch`, `semester`, `year`, `created_by`, `modified_by`, `created_on`, `modified_on`) VALUES
(1, '15CS001943', 'Monu', 'CSE', '8', '4', '', '', '', ''),
(2, '15CS001927', 'Ashwani', 'CSE', '8', '4', '', '', '', ''),
(3, '15CS001900', 'Pappu', 'ME', '8', '4', '', '', '', ''),
(4, '15CS001902', 'Ram', 'CE', '8', '4', '', '', '', '');

-- --------------------------------------------------------

--
-- Table structure for table `subject_table`
--

CREATE TABLE `subject_table` (
  `subject_id` int(11) NOT NULL,
  `code` varchar(20) NOT NULL,
  `name` varchar(1000) NOT NULL,
  `credit` varchar(1000) NOT NULL,
  `created_by` varchar(1000) NOT NULL,
  `modified_by` varchar(1000) NOT NULL,
  `created_on` varchar(1000) NOT NULL,
  `modified_on` varchar(1000) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `subject_table`
--

INSERT INTO `subject_table` (`subject_id`, `code`, `name`, `credit`, `created_by`, `modified_by`, `created_on`, `modified_on`) VALUES
(1, 'CS-001', 'Java', '5', '', '', '', ''),
(2, 'CS-002', 'C++', '4', '', '', '', ''),
(3, 'CS-003', 'C#', '3', '', '', '', ''),
(4, 'CS-004', 'Android', '2', '', '', '', ''),
(5, 'XX-000', 'Optional', '1', '', '', '', ''),
(6, 'CS-123', 'AI', '5', '', '', '', ''),
(7, 'CS-321', 'TOC', '5', '', '', '', ''),
(8, 'CS-090', 'DAA', '3', '', '', '', ''),
(12, 'cs-257', 'toc', '3', '', '', '', ''),
(13, 'cs-695', 'java', '5', '', '', '', ''),
(14, 'cs-258', 'c', '3', '', '', '', '');

-- --------------------------------------------------------

--
-- Table structure for table `teacher_table`
--

CREATE TABLE `teacher_table` (
  `user_id` int(11) NOT NULL,
  `name` varchar(1000) NOT NULL,
  `email` varchar(700) NOT NULL,
  `password` varchar(32) NOT NULL,
  `type` int(11) NOT NULL DEFAULT '0',
  `created_by` varchar(1000) NOT NULL,
  `modified_by` varchar(1000) NOT NULL,
  `created_on` varchar(1000) NOT NULL,
  `modified_on` varchar(1000) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `teacher_table`
--

INSERT INTO `teacher_table` (`user_id`, `name`, `email`, `password`, `type`, `created_by`, `modified_by`, `created_on`, `modified_on`) VALUES
(1, 'Admin', 'admin@gmail.com', '21232f297a57a5a743894a0e4a801fc3', 1, '', '', '', ''),
(2, 'Teacher', 'teacher', '098f6bcd4621d373cade4e832627b4f6', 0, '', '', '', ''),
(3, 'Teacher', 'teacher2', '098f6bcd4621d373cade4e832627b4f6', 0, '', '', '', ''),
(4, 'Teacher', 'teacher3', '098f6bcd4621d373cade4e832627b4f6', 0, '', '', '', '');

-- --------------------------------------------------------

--
-- Table structure for table `teach_sub_table`
--

CREATE TABLE `teach_sub_table` (
  `relation_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `subject_id` int(11) NOT NULL,
  `created_by` varchar(1000) NOT NULL,
  `modified_by` varchar(1000) NOT NULL,
  `created_on` varchar(1000) NOT NULL,
  `modified_on` varchar(1000) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `teach_sub_table`
--

INSERT INTO `teach_sub_table` (`relation_id`, `user_id`, `subject_id`, `created_by`, `modified_by`, `created_on`, `modified_on`) VALUES
(1, 2, 1, '', '', '', ''),
(2, 2, 2, '', '', '', ''),
(3, 3, 3, '', '', '', ''),
(4, 3, 4, '', '', '', ''),
(5, 4, 5, '', '', '', '');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `attendance_table`
--
ALTER TABLE `attendance_table`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `student_id` (`student_id`,`subject_id`,`time`(767),`date`,`type`) USING BTREE;

--
-- Indexes for table `student_sub_table`
--
ALTER TABLE `student_sub_table`
  ADD PRIMARY KEY (`relation_id`),
  ADD UNIQUE KEY `student_id` (`student_id`,`subject_id`);

--
-- Indexes for table `student_table`
--
ALTER TABLE `student_table`
  ADD PRIMARY KEY (`student_id`),
  ADD UNIQUE KEY `enrollment` (`enrollment`);

--
-- Indexes for table `subject_table`
--
ALTER TABLE `subject_table`
  ADD PRIMARY KEY (`subject_id`),
  ADD UNIQUE KEY `code` (`code`);

--
-- Indexes for table `teacher_table`
--
ALTER TABLE `teacher_table`
  ADD PRIMARY KEY (`user_id`),
  ADD UNIQUE KEY `email` (`email`);

--
-- Indexes for table `teach_sub_table`
--
ALTER TABLE `teach_sub_table`
  ADD PRIMARY KEY (`relation_id`),
  ADD UNIQUE KEY `user_id` (`user_id`,`subject_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `attendance_table`
--
ALTER TABLE `attendance_table`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=34;

--
-- AUTO_INCREMENT for table `student_sub_table`
--
ALTER TABLE `student_sub_table`
  MODIFY `relation_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT for table `student_table`
--
ALTER TABLE `student_table`
  MODIFY `student_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `subject_table`
--
ALTER TABLE `subject_table`
  MODIFY `subject_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT for table `teacher_table`
--
ALTER TABLE `teacher_table`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `teach_sub_table`
--
ALTER TABLE `teach_sub_table`
  MODIFY `relation_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
