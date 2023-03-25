-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Czas generowania: 23 Mar 2023, 23:32
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
(1, 'unjz2XPTAOPlSIpN5pFgIvxcgiKZlHiAs+T6cMK389g=', 'https://www.twitter.com', 'portal affected by Musk', 'mail2Test@mail.com', 1);

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
(1, 'loginekTest34', 'X6y93DTEOWMEqR1tuzngWfefZRJYqsgmP1SuwDHfZDd6+tjgebEv3/84lGBjTmyPo/x/gpHaaSYugA1SNSgLrg==', 'J2gFhgnlDEcJ5KC4Ze0umXCSbGnlOg', 1);

--
-- Indeksy dla zrzutów tabel
--

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
-- AUTO_INCREMENT dla tabeli `password`
--
ALTER TABLE `password`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT dla tabeli `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Ograniczenia dla zrzutów tabel
--

--
-- Ograniczenia dla tabeli `password`
--
ALTER TABLE `password`
  ADD CONSTRAINT `User_Id_Index` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
