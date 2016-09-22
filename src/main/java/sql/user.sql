/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50715
Source Host           : localhost:3306
Source Database       : ssm

Target Server Type    : MYSQL
Target Server Version : 50715
File Encoding         : 65001

Date: 2016-09-21 16:49:49
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(12) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `userName` varchar(255) DEFAULT NULL COMMENT '用户名',
  `userPhone` varchar(20) DEFAULT NULL COMMENT '用户手机号',
  `userEmail` varchar(255) DEFAULT NULL COMMENT '用户邮箱',
  `password` varchar(255) DEFAULT NULL COMMENT '密码',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `modifyTime` datetime DEFAULT NULL COMMENT '最后修改时间',
  `isDelete` tinyint(4) DEFAULT NULL COMMENT '是否被删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
