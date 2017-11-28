/*
Navicat MySQL Data Transfer

Source Server         : MySQL
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : baba

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2017-11-28 17:24:57
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `index` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_index` (`index`) USING HASH
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '彭冲', 'pc');
INSERT INTO `user` VALUES ('2', '彭飞', 'pf');
INSERT INTO `user` VALUES ('3', '彭佳', 'pj');
INSERT INTO `user` VALUES ('4', '张飞', 'zf');
INSERT INTO `user` VALUES ('5', '张萢', 'zp');
INSERT INTO `user` VALUES ('6', '关羽', 'gy');
INSERT INTO `user` VALUES ('7', '关平', 'gp');
