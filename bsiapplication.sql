-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Czas generowania: 14 Kwi 2023, 18:58
-- Wersja serwera: 10.4.27-MariaDB
-- Wersja PHP: 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Baza danych: `bsiapplication`
--

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `data_change`
--

CREATE TABLE `data_change` (
  `id` int(4) NOT NULL,
  `id_user` int(4) NOT NULL,
  `id_message_record` int(4) DEFAULT NULL,
  `previous_value_of_record` text DEFAULT NULL,
  `present_value_of_record` text DEFAULT NULL,
  `time` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `action_type` enum('Create','Update','Delete') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Zrzut danych tabeli `data_change`
--

INSERT INTO `data_change` (`id`, `id_user`, `id_message_record`, `previous_value_of_record`, `present_value_of_record`, `time`, `action_type`) VALUES
(1, 1, NULL, '\"READONLY\"', '\"MODIFY\"', '2023-04-13 21:24:37', 'Update'),
(2, 1, 3, NULL, '{\r\n  \"id\" : 3,\r\n  \"password\" : \"unjz2XPTAOPlSIpN5pFgIvxcgiKZlHiAs+T6cMK389g=\",\r\n  \"webAddress\" : \"https://www.twitter.com\",\r\n  \"description\" : \"portal affected by Musk223\",\r\n  \"login\" : \"mail2Test@mail.com\",\r\n  \"user\" : {\r\n    \"id\" : 1,\r\n    \"login\" : \"loginekTest34\",\r\n    \"passwordHash\" : \"X6y93DTEOWMEqR1tuzngWfefZRJYqsgmP1SuwDHfZDd6+tjgebEv3/84lGBjTmyPo/x/gpHaaSYugA1SNSgLrg==\",\r\n    \"salt\" : \"J2gFhgnlDEcJ5KC4Ze0umXCSbGnlOg\",\r\n    \"keptAsHash\" : true\r\n  }\r\n}', '2023-04-13 21:24:44', 'Create'),
(3, 1, 3, '{\r\n  \"id\" : 3,\r\n  \"password\" : \"eS6f9cSyjG3FEx/KAF1XZYjBdCNVNzx4cFo0skDNFDs=\",\r\n  \"webAddress\" : \"https://www.youtube.com\",\r\n  \"description\" : \"portalwith some movies asdfd\",\r\n  \"login\" : \"mail3@mail.com\",\r\n  \"user\" : {\r\n    \"id\" : 1,\r\n    \"login\" : \"loginekTest34\",\r\n    \"passwordHash\" : \"X6y93DTEOWMEqR1tuzngWfefZRJYqsgmP1SuwDHfZDd6+tjgebEv3/84lGBjTmyPo/x/gpHaaSYugA1SNSgLrg==\",\r\n    \"salt\" : \"J2gFhgnlDEcJ5KC4Ze0umXCSbGnlOg\",\r\n    \"keptAsHash\" : true\r\n  }\r\n}', '{\r\n  \"id\" : 3,\r\n  \"password\" : \"eS6f9cSyjG3FEx/KAF1XZYjBdCNVNzx4cFo0skDNFDs=\",\r\n  \"webAddress\" : \"https://www.youtube.com\",\r\n  \"description\" : \"portalwith some movies asdfd\",\r\n  \"login\" : \"mail3@mail.com\",\r\n  \"user\" : {\r\n    \"id\" : 1,\r\n    \"login\" : \"loginekTest34\",\r\n    \"passwordHash\" : \"X6y93DTEOWMEqR1tuzngWfefZRJYqsgmP1SuwDHfZDd6+tjgebEv3/84lGBjTmyPo/x/gpHaaSYugA1SNSgLrg==\",\r\n    \"salt\" : \"J2gFhgnlDEcJ5KC4Ze0umXCSbGnlOg\",\r\n    \"keptAsHash\" : true\r\n  }\r\n}', '2023-04-13 21:25:02', 'Update'),
(4, 2, NULL, NULL, '{\r\n  \"id\" : 2,\r\n  \"login\" : \"loginekTest3467\",\r\n  \"passwordHash\" : \"l0XSowbp5MPQ+NqyVb/wXhvxAz5HW5SfOvN+5O18JYSukCP61s7TGBk5Z6PN81fdJa+Jpxi9oKpeW2Oemx3k4A==\",\r\n  \"salt\" : \"3buV8kssnTipAUc2pAZBKtg3LM4oPF\",\r\n  \"keptAsHash\" : true\r\n}', '2023-04-13 21:26:24', 'Create'),
(5, 1, NULL, '{\r\n  \"id\" : 1,\r\n  \"login\" : \"loginekTest34\",\r\n  \"passwordHash\" : \"I/RzPs//ZvuxR35UejvWkbjpOz00tJIlthH+l/BonelqxUQMjfcVAopouTIE0XTh5Q/w+DqPpbNcWfpPjijM4g==\",\r\n  \"salt\" : \"nxx1dY8mTe99ryNz8SBTwSbYNgvGcv\",\r\n  \"keptAsHash\" : true\r\n}', '{\r\n  \"id\" : 1,\r\n  \"login\" : \"loginekTest34\",\r\n  \"passwordHash\" : \"I/RzPs//ZvuxR35UejvWkbjpOz00tJIlthH+l/BonelqxUQMjfcVAopouTIE0XTh5Q/w+DqPpbNcWfpPjijM4g==\",\r\n  \"salt\" : \"nxx1dY8mTe99ryNz8SBTwSbYNgvGcv\",\r\n  \"keptAsHash\" : true\r\n}', '2023-04-13 21:26:47', 'Update');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `function_run`
--

CREATE TABLE `function_run` (
  `id` int(4) NOT NULL,
  `id_user` int(4) DEFAULT NULL,
  `time` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `function` enum('create_password','get_session_user_passwords','get_decrypted_password','edit_password','create_user','get_all_users','get_user_by_login','login','change_password','change_access','current_access') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Zrzut danych tabeli `function_run`
--

INSERT INTO `function_run` (`id`, `id_user`, `time`, `function`) VALUES
(1, NULL, '2023-04-13 21:15:02', 'create_user'),
(2, NULL, '2023-04-13 21:23:48', 'login'),
(3, 1, '2023-04-13 21:24:02', 'get_session_user_passwords'),
(4, 1, '2023-04-13 21:24:31', 'create_password'),
(5, 1, '2023-04-13 21:24:37', 'change_access'),
(6, 1, '2023-04-13 21:24:44', 'create_password'),
(7, 1, '2023-04-13 21:25:01', 'edit_password'),
(8, 1, '2023-04-13 21:26:24', 'create_user'),
(9, 1, '2023-04-13 21:26:47', 'change_password'),
(10, NULL, '2023-04-13 21:26:53', 'login'),
(11, NULL, '2023-04-13 21:27:11', 'login');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `password`
--

CREATE TABLE `password` (
  `id` int(11) NOT NULL,
  `password` varchar(512) NOT NULL,
  `web_address` varchar(512) NOT NULL,
  `description` varchar(512) NOT NULL,
  `login` varchar(40) NOT NULL,
  `user_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Zrzut danych tabeli `password`
--

INSERT INTO `password` (`id`, `password`, `web_address`, `description`, `login`, `user_id`) VALUES
(1, 'HQo/w3G3wawRmwk2pyHGijVTLzBeUdLbuqZROmh9M5A=', 'https://www.twitter.com', 'portal affected by Musk', 'mail2Test@mail.com', 1),
(2, '1O0AZ3Dl7VKc2df7ncI5wTTgYRD5T7eGPkyF2q1I2CQ=', 'https://www.youtube.com', 'portalwith some movies', 'mail3@mail.com', 1),
(3, '1O0AZ3Dl7VKc2df7ncI5wTTgYRD5T7eGPkyF2q1I2CQ=', 'https://www.youtube.com', 'portalwith some movies asdfd', 'mail3@mail.com', 1);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `login` varchar(40) NOT NULL,
  `password_hash` varchar(512) NOT NULL,
  `salt` varchar(30) NOT NULL,
  `is_kept_as_hash` tinyint(1) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Zrzut danych tabeli `user`
--

INSERT INTO `user` (`id`, `login`, `password_hash`, `salt`, `is_kept_as_hash`) VALUES
(1, 'loginekTest34', 'I/RzPs//ZvuxR35UejvWkbjpOz00tJIlthH+l/BonelqxUQMjfcVAopouTIE0XTh5Q/w+DqPpbNcWfpPjijM4g==', 'nxx1dY8mTe99ryNz8SBTwSbYNgvGcv', 1),
(2, 'loginekTest3467', 'l0XSowbp5MPQ+NqyVb/wXhvxAz5HW5SfOvN+5O18JYSukCP61s7TGBk5Z6PN81fdJa+Jpxi9oKpeW2Oemx3k4A==', '3buV8kssnTipAUc2pAZBKtg3LM4oPF', 1);

--
-- Indeksy dla zrzutów tabel
--

--
-- Indeksy dla tabeli `data_change`
--
ALTER TABLE `data_change`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKdata_chang739617` (`id_message_record`),
  ADD KEY `FKdata_chang567764` (`id_user`);

--
-- Indeksy dla tabeli `function_run`
--
ALTER TABLE `function_run`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKfunction_r609630` (`id_user`);

--
-- Indeksy dla tabeli `password`
--
ALTER TABLE `password`
  ADD PRIMARY KEY (`id`),
  ADD KEY `User_Id_Index` (`user_id`);

--
-- Indeksy dla tabeli `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `login_unique` (`login`);

--
-- AUTO_INCREMENT dla zrzuconych tabel
--

--
-- AUTO_INCREMENT dla tabeli `data_change`
--
ALTER TABLE `data_change`
  MODIFY `id` int(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT dla tabeli `function_run`
--
ALTER TABLE `function_run`
  MODIFY `id` int(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT dla tabeli `password`
--
ALTER TABLE `password`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT dla tabeli `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Ograniczenia dla zrzutów tabel
--

--
-- Ograniczenia dla tabeli `data_change`
--
ALTER TABLE `data_change`
  ADD CONSTRAINT `FKdata_chang567764` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `FKdata_chang739617` FOREIGN KEY (`id_message_record`) REFERENCES `password` (`id`) ON UPDATE CASCADE;

--
-- Ograniczenia dla tabeli `function_run`
--
ALTER TABLE `function_run`
  ADD CONSTRAINT `FKfunction_r609630` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`) ON UPDATE CASCADE;

--
-- Ograniczenia dla tabeli `password`
--
ALTER TABLE `password`
  ADD CONSTRAINT `User_Id_Index` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
