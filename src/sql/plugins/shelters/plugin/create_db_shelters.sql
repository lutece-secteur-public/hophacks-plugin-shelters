
--
-- Structure for table shelters_shelter
--

DROP TABLE IF EXISTS shelters_shelter;
CREATE TABLE shelters_shelter (
id_shelter int(6) NOT NULL,
name varchar(50) default '' NOT NULL,
description varchar(255) default '',
email varchar(255) default '' NOT NULL,
web_site varchar(255) default '',
workgroup_key varchar(50) default '' NOT NULL,
reminder_status SMALLINT,
bed_capacity int(11) default '0',
phone_number varchar(50) default '',
location_lat int(11) default '0',
location_lon int(11) default '0',
PRIMARY KEY (id_shelter)
);
