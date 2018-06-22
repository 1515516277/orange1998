/*
Navicat MySQL Data Transfer

Source Server         : test
Source Server Version : 50722
Source Host           : localhost:3306
Source Database       : ssm

Target Server Type    : MYSQL
Target Server Version : 50722
File Encoding         : 65001

Date: 2018-06-22 14:41:22
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for dept
-- ----------------------------
DROP TABLE IF EXISTS `dept`;
CREATE TABLE `dept` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dept
-- ----------------------------
INSERT INTO `dept` VALUES ('1', '删说说');

-- ----------------------------
-- Table structure for emp
-- ----------------------------
DROP TABLE IF EXISTS `emp`;
CREATE TABLE `emp` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(10) DEFAULT NULL,
  `gender` varchar(10) DEFAULT NULL,
  `email` varchar(20) DEFAULT NULL,
  `d_id` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of emp
-- ----------------------------
INSERT INTO `emp` VALUES ('1', '说说', '男', '123123', '1');
INSERT INTO `emp` VALUES ('2', '上大', '女', '2341', '1');
INSERT INTO `emp` VALUES ('3', '双法防', '男', '3412', '1');
INSERT INTO `emp` VALUES ('4', '疯狗式', '男', '123', '1');
INSERT INTO `emp` VALUES ('5', '123', '1', '3123', '1');
INSERT INTO `emp` VALUES ('6', '阿斯顿,123', '1,1', '321,3123', '1');
INSERT INTO `emp` VALUES ('7', '', '1', '123', '1');

-- ----------------------------
-- Table structure for privilege
-- ----------------------------
DROP TABLE IF EXISTS `privilege`;
CREATE TABLE `privilege` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of privilege
-- ----------------------------
INSERT INTO `privilege` VALUES ('1', '查询列表');
INSERT INTO `privilege` VALUES ('2', '修改密码');
INSERT INTO `privilege` VALUES ('3', '删除用户');
INSERT INTO `privilege` VALUES ('4', '添加用户');

-- ----------------------------
-- Table structure for privilegerole
-- ----------------------------
DROP TABLE IF EXISTS `privilegerole`;
CREATE TABLE `privilegerole` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `roleid` int(11) DEFAULT NULL,
  `privilegeid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of privilegerole
-- ----------------------------
INSERT INTO `privilegerole` VALUES ('1', '1', '1');
INSERT INTO `privilegerole` VALUES ('2', '1', '2');
INSERT INTO `privilegerole` VALUES ('3', '1', '3');
INSERT INTO `privilegerole` VALUES ('4', '1', '4');
INSERT INTO `privilegerole` VALUES ('5', '2', '2');
INSERT INTO `privilegerole` VALUES ('6', '3', '3');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', 'admin');
INSERT INTO `role` VALUES ('2', 'user');
INSERT INTO `role` VALUES ('3', 'vip');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL,
  `password` varchar(50) NOT NULL,
  `role` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('27', 'admin', '0192023a7bbd73250516f069df18b500', '1');
INSERT INTO `user` VALUES ('28', 'user', '6ad14ba9986e3615423dfca256d04e3f', '1');
