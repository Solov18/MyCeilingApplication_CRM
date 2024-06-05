insert into roles values(1, "ROLE_USER");
insert into roles values(2, "ROLE_ADMIN");

insert into users values(1, 1, '2018-02-15 10:05:58', 'jakub.malinowski@company.com', 'Jakub', 'Malinowski', '$2a$10$ei9ovqWL31J36O5Gu/UtaODwUZ8YCxIUXD7C3aLlb14ukoQxWk4Qa', '555-001-001', 0, 'admin');
insert into users values(2, 1, '2018-03-08 15:29:48', 'paulina.sobczak@company.com', 'Paulina', 'Sobczak', '$2a$10$MTe7iJnmpwFe0tSiIMYB/uesZmHnx2dq4GzD3NTcEfpRj.JvWeTxS', '700-300-100', 0, 'pausob');
insert into users values(3, 1, '2018-03-08 15:35:42', 'marian.chmielewski@company.com', 'Marian', 'Chmielewski', '$2a$10$p9ONd5YqP/RSmT.vK9H57uLD/7CG189Qt0mfT6/70nqQU9LWB7QPu', '605-303-030', 0, 'marchmiel');
insert into users values(4, 1, '2018-03-08 15:53:15', 'joanna.baranowska@company.com', 'Joanna', 'Baranowska', '$2a$10$8Gzzp5yEhICjZU7u065wQudKYur9LQUGIgm4qTscaaDxMBcdGZeD2', '602-400-500', 1, 'joannabar');
insert into users values(5, 1, '2018-03-08 15:55:34', 'wanda.krawczyk@company.com', 'Wanda', 'Krawczyk', '$2a$10$QuIzx6Lnv8zDMUTh3M8gSuE1kia3R7dw4sufrjKiSkQENQpNoEmDO', '888-100-100', 2, 'wankra');
insert into users values(6, 1, '2018-03-09 15:03:39', 'roman.sawicki@company.com', 'Roman', 'Sawicki', '$2a$10$i9kndLe6x4mMf749ViG6NOWUCylA73QucpZxoqKtuGgnOrij4tnBC', '654-312-200', 0, 'romsaw');
insert into users values(7, 1, '2018-03-09 15:12:57', 'renata.urbanska@company.com', 'Renata', 'Urbanska', '$2a$10$Ni4eiAhKcI/vfz1jN2y.yOzz5h5SyCA.l3NxK2N.68F4e/yYflJn.', '616-300-252', 0, 'renurb');
insert into users values(8, 1, '2018-03-09 15:19:07', 'ewelina.malinowska@company.com', 'Ewelina', 'Malinowska', '$2a$10$T9LEBT497RYw6aH4zBPpEOTrKvWAvXdzaZTCgQ7LqesiDioHLYmCW', '700-350-230', 0, 'ewemalin');
insert into users values(9, 1, '2018-03-09 15:21:40', 'anna.krajewska@company.com', 'Anna', 'Krajewska', '$2a$10$KbJ.8NtnZaGKwRuf6IaTcOSweI225ef0QaE7PkMS0uIYh3IuyNEhe', '601-859-320', 0, 'annkraj');
insert into users values(10, 1, '2018-03-09 15:23:27', 'agata.kubiak@company.com', 'Agata', 'Kubiak', '$2a$10$M9Dxp6YAt9kfNREIlckJieMJuXNDKwO/BG0PBzsVyGUal7/WBDYNm', '510-610-355', 1, 'agakub');

insert into users_roles values(2, 1);
insert into users_roles values(3, 1);
insert into users_roles values(4, 1);
insert into users_roles values(5, 1);
insert into users_roles values(1, 2);
insert into users_roles values(5, 2);
insert into users_roles values(6, 1);
insert into users_roles values(7, 1);
insert into users_roles values(8, 1);
insert into users_roles values(9, 1);
insert into users_roles values(10, 1);

insert into teams values(1, 'Sales Team', 4);
insert into teams values(2, 'Support Team', 10);

insert into teams_members values(1, 2);
insert into teams_members values(1, 3);
insert into teams_members values(1, 6);
insert into teams_members values(2, 7);
insert into teams_members values(2, 8);
insert into teams_members values(2, 9);

insert into addresses values('1', 'lok. 221', 'Warszawa', 'Polska', '00-140', 'Al. Jerozolimskie', '127');
insert into addresses values('2', 'pawilon A', 'Warszawa', 'Polska', '01-201', 'Pulawska', '155');
insert into addresses values('3', 'blok H', 'Warszawa', 'Polska', '04-345', '11 Listopada', '34');
insert into addresses values('4', 'lok.30', 'Katowice', 'Polska', '34-405', 'Wysoka', '134');
insert into addresses values('5', '', 'Łódź', 'Polska', '95-005', 'Piotrkowska', '26');
insert into addresses values('6', '', 'Ruda Śląska', 'Polska', '35-200', 'Wydobywcza', '3');
insert into addresses values('7', '', 'Radom', 'Polska', '20-200', 'Wschodnia', '1');
insert into addresses values('8', '', 'Kraków', 'Polska', '32-100', 'Złota', '12');
insert into addresses values('9', 'paw. 10', 'Warszawa', 'Polska', '02-450', 'Barnicza', '7');
insert into addresses values('10', '', 'Gdańsk', 'Polska', '80-120', 'Nadbrzeżna', '2');
insert into addresses values('11', '', 'Płock', 'Polska', '09-411', 'Chemików', '7');
insert into addresses values('12', '', 'Gdańsk', 'Polska', '80-718', 'Elbląska', '135');
insert into addresses values('13', '', 'Warszawa', 'Polska', '01-224', 'Kasprzaka', '25');
insert into addresses values('14', '', 'Warszawa', 'Polska', '00-496', 'Mysia', '2');
insert into addresses values('15', '', 'Warszawa', 'Polska', '00-133', 'al. Jana Pawła II ', '24');
insert into addresses values('16', '', 'Warszawa', 'Polska', '00-120', 'Długa', '2');
insert into addresses values('17', '', 'Szczecin', 'Polska', '70-120', 'Prosta', '12');
insert into addresses values('18', '', 'Poznań', 'Polska', '60-120', 'Krzywa', '5');
insert into addresses values('19', '', 'Kielce', 'Polska', '56-120', 'Krótka', '30');
insert into addresses values('20', '', 'Wrocław', 'Polska', '48-120', 'Ciasna', '1');

insert into clients values('1', 'CODEXO', 1, '0', 'CODEXO Technology', '1', 2);
insert into clients values('2', 'XETEL', 1, '1', 'XETEL Communication', '2', 3);
insert into clients values('3', 'FUTURO', 1, '8', 'FUTURO SA', '3', NULL);
insert into clients values('4', 'LIMBO', 1, '7', 'LIMBO Sp. z o.o.', '4', NULL);
insert into clients values('5', 'TOYSLEGO', 1, '8', 'TOYSLEGO Sp. z o.o.', '5', NULL);
insert into clients values('6', 'PROFILE', 1, '7', 'PROFILE SA', '6', NULL);
insert into clients values('7', 'BGR', 1, '3', 'Bank Gospodarstwa Regionalnego SA', '7', NULL);
insert into clients values('8', 'MBK', 1, '3', 'Małopolski Bank Kredytowy SA', '8', NULL);
insert into clients values('9', 'BTRF', 1, '3', 'Bank Twojej Rezerwy Federalnej', '9', NULL);
insert into clients values('10', 'FISHERGOLD', 1, '8', 'FISHERGOLD SA', '10', NULL);
insert into clients values('11', 'ORLEN', 1, '7', 'Polski Koncern Naftowy ORLEN SA', '11', NULL);
insert into clients values('12', 'LOTOS', 1, '7', 'Grupa LOTOS SA', '12', NULL);
insert into clients values('13', 'PGNiG', 1, '7', 'Polskie Górnictwo Naftowe i Gazownictwo SA', '13', NULL);
insert into clients values('14', 'PGE', 1, '8', 'Polska Grupa Energetyczna SA', '14', NULL);
insert into clients values('15', 'PZU', 1, '5', 'Powszechny Zakład Ubezpieczeń SA', '15', NULL);
insert into clients values('16', 'Dobra firma1', 1, '10', 'Dobra firma1', '16', NULL);
insert into clients values('17', 'Dobra firma2', 1, '10', 'Dobra firma2', '17', NULL);
insert into clients values('18', 'Dobra firma3', 1, '10', 'Dobra firma3', '18', NULL);
insert into clients values('19', 'Dobra firma4', 1, '10', 'Dobra firma4', '19', NULL);
insert into clients values('20', 'Dobra firma5', 1, '10', 'Dobra firma5', '20', NULL);


insert into representatives values('1', 1, 'alicja.izdebska@codexo.com', 'Alicja', 'Izdebska', '500-134-202', 'Sales Director', '1');
insert into representatives values('2', 1, 'dominika.sawicka@xetel.com', 'Dominika', 'Sawicka', '602-510-130', 'KAM', '2');
insert into representatives values('3', 1, 'jan.puchalski@futuro.pl', 'Jan', 'Puchalski', '730-012-012', 'Dyrektor Sprzedazy', '3');
insert into representatives values('4', 1, 'edyta.michalska@limbo.com.pl', 'Edyta', 'Michalska', '603-603-030', 'Partner', '4');
insert into representatives values('5', 1, 's.wojciechowski@toyslego.com', 'Szymon', 'Wojciechowski', '607-360-760', 'Dyrektor Handlowy', '5');
insert into representatives values('6', 1, 'zygmund.nadolny@profile.pl', 'Zygmund', 'Nadolny', '515-030-012', 'KAM', '6');
insert into representatives values('7', 1, 'angelika.hoit@bgr.com.pl', 'Angelika', 'Hoit', '22-630-12-20', 'Dyrektor Marketingu', '7');
insert into representatives values('8', 1, 'renata.cybulska@mbkbank.pl', 'Renata', 'Cybulska', '32-250-30-40', 'Dyrektor Sprzedazy', '8');
insert into representatives values('9', 1, 'jerzy.mendrek@bankrezerwy.pl', 'Jerzy', 'Mendrek', '620-750-750', 'Partner', '9');
insert into representatives values('10', 1, 'h.goldfish@fishergold.pl', 'Hannah', 'Goldfish', '510-099-099', 'Owner', '10');

insert into representatives values('11', 1, 'andrzej.wozniak@codexo.com', 'Andrzej', 'Wozniak', '500-134-201', 'Sales Manager', '1');
insert into representatives values('12', 1, 'mikolaj.stanicki@xetel.com', 'Mikołaj', 'Stanicki', '602-510-230', 'KAM', '2');
insert into representatives values('13', 1, 'tomasz.ryski@futuro.pl', 'Tomasz', 'Ryski', '730-012-011', 'Menedżer Sprzedazy', '3');
insert into representatives values('14', 1, 'edyta.zdanowska@limbo.com.pl', 'Edyta', 'Zdanowska', '603-603-035', 'Specjalista ds. Zakupów', '4');
insert into representatives values('15', 1, 'l.bones@toyslego.com', 'Lena', 'Bones', '607-360-761', 'Przedstawiciel Handlowy', '5');
insert into representatives values('16', 1, 'stanislaw.gorny@profile.pl', 'Stanisław', 'Górny', '500-350-620', 'Dyrektor Handlowy', '6');
insert into representatives values('17', 1, 'barbara.luft@bgr.com.pl', 'Barbara', 'Luft', '22-630-15-61', 'Dyrektor Handlowy', '7');
insert into representatives values('18', 1, 'robert.szybki@mbkbank.pl', 'Robert', 'Szybki', '32-250-30-50', 'Dyrektor ds. Zakupów', '8');
insert into representatives values('19', 1, 'anna.lasek@bankrezerwy.pl', 'Anna', 'Lasek', '620-750-753', 'Partner', '9');
insert into representatives values('20', 1, 'n.goldfish@fishergold.pl', 'Norbert', 'Goldfish', '510-099-990', 'Owner', '10');

insert into representatives values('21', 1, 'krzysztof.golawski@orlen.pl', 'Krzysztof', 'Golawski', '510-810-005', 'Dyrektor Handlowy', '11');
insert into representatives values('22', 1, 'irena.szewicka@lotos.pl', 'Irena', 'Szewicka', '602-400-302', 'Dyrektor Handlowy', '12');
insert into representatives values('23', 1, 'dariusz.robkiewicz@pgnig.pl', 'Dariusz', 'Robkiewicz', '724-131-313', 'Dyrektor ds. Zakupów', '13');
insert into representatives values('24', 1, 'joanna.drzewicka@pge.pl', 'Joanna', 'Drzewicka', '530-601-601', 'Dyrektor Handlowy', '14');
insert into representatives values('25', 1, 'justyna.abramowicz@pzu.pl', 'Justyna', 'Abramowicz', '540-100-035', 'Przedstawiciel Handlowy', '15');








