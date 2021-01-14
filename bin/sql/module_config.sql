
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for module_config
-- ----------------------------
DROP TABLE IF EXISTS `module_config`;
CREATE TABLE `module_config` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `app_name` varchar(255) NOT NULL COMMENT '应用名',
  `environment` varchar(255) NOT NULL COMMENT '环境信息',
  `mock_class` varchar(255) NOT NULL,
  `mock_method` varchar(255) DEFAULT NULL,
  `is_throws` int(8) DEFAULT '0',
  `return_obj` varchar(2555) DEFAULT NULL,
  `rule_config` varchar(48) NOT NULL COMMENT '配置信息',
  `is_usable` tinyint(8) NOT NULL DEFAULT '1',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8 COMMENT='模块配置信息';

SET FOREIGN_KEY_CHECKS = 1;
