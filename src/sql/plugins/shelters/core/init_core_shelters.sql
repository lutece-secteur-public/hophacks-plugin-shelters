--
-- Data for table core_admin_right
--
DELETE FROM core_admin_right WHERE id_right = 'SHELTERS_MANAGEMENT';
DELETE FROM core_admin_right WHERE id_right = 'SHELTERS_BED_MANAGEMENT';

INSERT INTO core_admin_right (id_right,name,level_right,admin_url,description,is_updatable,plugin_name,id_feature_group,icon_url,documentation_url, id_order ) VALUES 
('SHELTERS_MANAGEMENT','shelters.adminFeature.ManageShelters.name',1,'jsp/admin/plugins/shelters/ManageShelters.jsp','shelters.adminFeature.ManageShelters.description',0,'shelters',NULL,NULL,NULL,4),
('SHELTERS_BED_MANAGEMENT', 'shelters.adminFeature.ManageBeds.name', 2, 'jsp/admin/plugins/shelters/ManageBeds.jsp', 'shelters.adminFeature.ManageBeds.description', 0, 'shelters', NULL, NULL, NULL, 3);

--
-- Data for table core_user_right
--
DELETE FROM core_user_right WHERE id_right = 'SHELTERS_MANAGEMENT';
INSERT INTO core_user_right (id_right,id_user) VALUES ('SHELTERS_MANAGEMENT',1);
INSERT INTO core_user_right (id_right,id_user) VALUES ('SHELTERS_BED_MANAGEMENT',1);
