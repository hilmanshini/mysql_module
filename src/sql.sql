-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               5.7.21 - MySQL Community Server (GPL)
-- Server OS:                    macos10.13
-- HeidiSQL Version:             9.5.0.5196
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for test_app
CREATE DATABASE IF NOT EXISTS `test_app` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `test_app`;

-- Dumping structure for table test_app.admin
CREATE TABLE IF NOT EXISTS `admin` (
  `id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK__user_adm` FOREIGN KEY (`id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.
-- Dumping structure for table test_app.class
CREATE TABLE IF NOT EXISTS `class` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `subject` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- Data exporting was unselected.
-- Dumping structure for procedure test_app.fun_admin_add_class
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `fun_admin_add_class`(
	IN `class_name` TEXT


)
BEGIN
set @simple := LOWER(trim(class_name));
select count(*) from class where class.subject = @simple into @cnt;
if @cnt = 0 then 
	insert into class(subject) values(class_name);
	select 'success' as message ,200 as status,'' as data;
else 
	select 'class_exists' as message ,-1 as status,'' as data;
end if;
END//
DELIMITER ;

-- Dumping structure for procedure test_app.fun_admin_add_student
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `fun_admin_add_student`(
	IN `email` TEXT,
	IN `password` TEXT,
	IN `profile_name` TEXT

)
BEGIN
select count(*) from user where email = email into @cnt;
if @cnt = 0 then
	insert into user(email,password) values (email,(password));
	select last_insert_id into @id;
	insert into student values(@id,profile_name);
	select 'success' as message ,200 as status,'' as data;
 else 
 	select 'email exists' as message ,-1 as status,'' as data;
 	end if;
END//
DELIMITER ;

-- Dumping structure for procedure test_app.fun_admin_add_teacher
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `fun_admin_add_teacher`(
	IN `email` TEXT,
	IN `password` TEXT,
	IN `profile_name` TEXT


)
BEGIN
select count(*) from user where email = email into @cnt;
if @cnt = 0 then
	insert into user(email,password) values (email,(password));
	select last_insert_id into @id;
	insert into teacher values(@id,profile_name);
	select 'success' as message ,200 as status,'' as data;
 else 
 	select 'email exists' as message ,-1 as status,'' as data;
 	end if;
END//
DELIMITER ;

-- Dumping structure for procedure test_app.fun_auth_user
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `fun_auth_user`(
	IN `email` TEXT,
	IN `password` TEXT





)
    DETERMINISTIC
BEGIN
select  user.id,user.email  from user inner join student on student.id = user.id where user.email = email and user.password = password into @id,@email;
select  case when count(@id) = 0 then -1 else @id end as e into @cnt;
select case when @cnt = -1 then 'invalid_auth'
				else 'user_exists' end as message ,
				@id as status,
				case when @cnt = -1 then null else @id end as data;
END//
DELIMITER ;

-- Dumping structure for procedure test_app.fun_auth_user_admin
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `fun_auth_user_admin`(
	IN `email` TEXT,
	IN `password` TEXT

)
BEGIN
select  user.id,user.email  from user inner join admin on admin.id = user.id where user.email = email and user.password = password into @id,@email;
select  case when count(@id) = 0 then -1 else @id end as e into @cnt;
select case when @cnt = -1 then 'invalid_auth'
				else 'user_exists' end as message ,
				@id as status,
				case when @cnt = -1 then null else @id end as data;
END//
DELIMITER ;

-- Dumping structure for procedure test_app.fun_auth_user_teacher
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `fun_auth_user_teacher`(
	IN `email` text,
	IN `password` text


)
BEGIN
select  user.id,user.email  from user inner join teacher on teacher.id = user.id where user.email = email and user.password = password into @id,@email;
select  case when count(@id) = 0 then -1 else @id end as e into @cnt;
select case when @cnt = -1 then 'invalid_auth'
				else 'user_exists' end as message ,
				@id as status,
				case when @cnt = -1 then null else @id end as data;
END//
DELIMITER ;

-- Dumping structure for procedure test_app.fun_student_view_class
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `fun_student_view_class`(
	IN `id_student` INT,
	IN `start_time` DATETIME,
	IN `end_Time` DATETIME

)
BEGIN
select distinct room,  class.subject,teacher.profile_name,student_class.time_start,student_class.time_end from student_class 
	inner join class on class.id = student_class.id_class
	inner join teacher on teacher.id = student_class.id_teacher
where student_class.id_student = id_student and 
student_class.time_start >= start_time and 
student_class.time_end <= end_time

;
END//
DELIMITER ;

-- Dumping structure for procedure test_app.fun_teacher_add_class
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `fun_teacher_add_class`(
	IN `id_student` INT,
	IN `id_class` INT,
	IN `id_teacher` INT,
	IN `start_time` DATETIME,
	IN `end_time` DATETIME
,
	IN `room` INT


)
root:BEGIN

select count(*) from user inner join teacher on teacher.id = user.id where user.id = id_teacher into @cnt;
if
	@cnt = 0 then 
	select -1 as status,'teacher_not_exists' as message , ''as data;
	leave root;
	end if;


select count(*) from user inner join student on student.id = user.id where user.id = id_student into @cnt;
if
	@cnt = 0 then 
	select -2 as status,'student_not_exists' as message , ''as data;
	leave root;
	end if;

select count(*) from class where id = id_class into @cnt;
if
	@cnt = 0 then 
	select -3 as status,'class_not_exists' as message , ''as data;
	leave root;
	end if;


if
	time_start is null or time_end is null then
	select -4 as status,'time_invalid' as message , ''as data;
	leave root;
	end if;	

select count(*) from student_class 
	where 
		student_class.time_start >= start_time  and 
		student_class.time_end < end_time and 
		student_class.id_teacher = id_teacher and 
		student_class.id_class = id_class
		
		 into @cnt2;
		
if @cnt2 > 0 then 
	select -5 as status,'still_have_appointment' as message , ''as data;
	leave root;
	end if;	

INSERT INTO `student_class` (`time_start`, `time_end`,`id_student`,`id_class`,`id_teacher`,`room`) VALUES (time_start,time_end,id_student,id_class,id_teacher,room);
	select 0 as status,'success' as message , ''as data;
END//
DELIMITER ;

-- Dumping structure for procedure test_app.fun_teacher_view_class_by_time
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `fun_teacher_view_class_by_time`(
	IN `id_teacher` INT,
	IN `start_time` DATETIME,
	IN `end_time` DATETIME



)
BEGIN
select distinct room,  class.subject,class.id,student_class.time_start,student_class.time_end from student_class 
	inner join class on class.id = student_class.id_class
where id_teacher = id_teacher and 
student_class.time_start >= start_time and 
student_class.time_end <= end_time group by room,student_class.id_class

;
END//
DELIMITER ;

-- Dumping structure for procedure test_app.fun_teacher_view_student_by_room_and_subject
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `fun_teacher_view_student_by_room_and_subject`(
	IN `id_teacher` INT,
	IN `id_class` INT,
	IN `start_time` DATETIME,
	IN `end_time` DATETIME

,
	IN `room` INT
)
BEGIN
select student.id,student.profile_name,student_class.id as id_student_class from student_class
left outer join student on student.id = student_class.id
where student_class.id_teacher = id_teacher and 
		student_class.id_class = id_class and 
		student_class.time_start >= start_time and 
		student_class.time_end <= end_time and 
		student_class.room = room
		;
END//
DELIMITER ;

-- Dumping structure for table test_app.student
CREATE TABLE IF NOT EXISTS `student` (
  `id` int(11) NOT NULL,
  `profile_name` text,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK_student_user` FOREIGN KEY (`id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.
-- Dumping structure for table test_app.student_class
CREATE TABLE IF NOT EXISTS `student_class` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `room` int(11) NOT NULL,
  `time_start` datetime NOT NULL,
  `time_end` datetime NOT NULL,
  `id_student` int(11) NOT NULL,
  `id_teacher` int(11) NOT NULL,
  `id_class` int(11) NOT NULL,
  PRIMARY KEY (`id`,`time_start`,`room`,`time_end`,`id_student`,`id_teacher`,`id_class`),
  KEY `FK_student_class_student` (`id_student`),
  KEY `FK_student_class_teacher` (`id_teacher`),
  KEY `FK_student_class_class` (`id_class`),
  CONSTRAINT `FK_student_class_class` FOREIGN KEY (`id_class`) REFERENCES `class` (`id`),
  CONSTRAINT `FK_student_class_student` FOREIGN KEY (`id_student`) REFERENCES `student` (`id`),
  CONSTRAINT `FK_student_class_teacher` FOREIGN KEY (`id_teacher`) REFERENCES `teacher` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.
-- Dumping structure for table test_app.teacher
CREATE TABLE IF NOT EXISTS `teacher` (
  `id` int(11) NOT NULL,
  `profile_name` text NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK__user` FOREIGN KEY (`id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.
-- Dumping structure for table test_app.user
CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` text,
  `password` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=112 DEFAULT CHARSET=latin1;

-- Data exporting was unselected.
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
