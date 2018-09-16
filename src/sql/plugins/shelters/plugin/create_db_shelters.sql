
--
-- Structure for table shelters_shelter
--

DROP TABLE IF EXISTS shelters_shelter;
CREATE TABLE shelters_shelter (
id_shelter int AUTO_INCREMENT,
name varchar(50) default '' NOT NULL,
description varchar(500) default '',
email varchar(255) default '' NOT NULL,
web_site varchar(255) default '',
workgroup_key varchar(50) default '' NOT NULL,
reminder_status SMALLINT,
bed_capacity int default '0',
phone_number varchar(50) default '',
location_lat float(11) default '0',
location_lon float(11) default '0',
address long varchar,
PRIMARY KEY (id_shelter)
);


--
-- Structure for table shelters_bed_availability
--

DROP TABLE IF EXISTS shelters_bed_availability;
CREATE TABLE shelters_bed_availability (
id_shelter int  default '0' NOT NULL,
date_code varchar(50) default '' NOT NULL,
bed_available_count int default '0' NOT NULL,
total_bed_capacity int default '0' NOT NULL,
PRIMARY KEY (id_shelter , date_code )
);