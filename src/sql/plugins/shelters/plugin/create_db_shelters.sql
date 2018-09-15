
--
-- Structure for table shelters_shelter
--

DROP TABLE IF EXISTS shelters_shelter;
CREATE TABLE shelters_shelter (
id_shelter int AUTO_INCREMENT,
name varchar(50) default '' NOT NULL,
description varchar(255) default '',
email varchar(255) default '' NOT NULL,
web_site varchar(255) default '',
workgroup_key varchar(50) default '' NOT NULL,
reminder_status SMALLINT,
bed_capacity int default '0',
phone_number varchar(50) default '',
location_lat int default '0',
location_lon int default '0',
address long varchar,
PRIMARY KEY (id_shelter)
);
