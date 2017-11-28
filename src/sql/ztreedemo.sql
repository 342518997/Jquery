/*
Navicat MySQL Data Transfer

Source Server         : MySQL
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : baba

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2017-11-28 17:20:02
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for ztreedemo
-- ----------------------------
DROP TABLE IF EXISTS `ztreedemo`;
CREATE TABLE `ztreedemo` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '主键id 唯一标识',
  `name` varchar(255) NOT NULL COMMENT '部门名称',
  `parent_id` bigint(11) NOT NULL COMMENT '部门id',
  `parent_ids` varchar(255) NOT NULL COMMENT '父id',
  `avaialble` tinyint(1) NOT NULL COMMENT '是否可用的资源',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ztreedemo
-- ----------------------------
INSERT INTO `ztreedemo` VALUES ('1', '开发部', '0', '0/', '1');
INSERT INTO `ztreedemo` VALUES ('2', '运维部', '0', '0/', '1');
INSERT INTO `ztreedemo` VALUES ('3', '市场部', '0', '0/', '1');
INSERT INTO `ztreedemo` VALUES ('4', '安徽开发组', '1', '0/1/', '1');
INSERT INTO `ztreedemo` VALUES ('5', '湖南开发组', '1', '0/1/', '1');
INSERT INTO `ztreedemo` VALUES ('6', '安徽运维组', '2', '0/2/', '1');
INSERT INTO `ztreedemo` VALUES ('7', '湖南运维组', '2', '0/2/', '1');
