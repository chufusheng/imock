
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for module_info
-- ----------------------------
DROP TABLE IF EXISTS `module_info`;
CREATE TABLE `module_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `app_name` varchar(255) NOT NULL COMMENT '应用名',
  `environment` varchar(255) NOT NULL COMMENT '环境信息',
  `ip` varchar(36) NOT NULL COMMENT '机器IP',
  `port` varchar(12) NOT NULL COMMENT '链路追踪ID',
  `version` varchar(128) NOT NULL COMMENT '模块版本号',
  `status` varchar(36) NOT NULL COMMENT '模块状态',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='在线模块信息';

SET FOREIGN_KEY_CHECKS = 1;
