/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 50715
 Source Host           : localhost
 Source Database       : ssm

 Target Server Type    : MySQL
 Target Server Version : 50715
 File Encoding         : utf-8

 Date: 09/24/2016 20:32:21 PM
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `userName` varchar(255) DEFAULT NULL COMMENT '用户名',
  `userPhone` varchar(20) DEFAULT NULL COMMENT '用户手机号',
  `userEmail` varchar(255) DEFAULT NULL COMMENT '用户邮箱',
  `password` varchar(255) DEFAULT NULL COMMENT '密码',
  `createTime` bigint(12) DEFAULT NULL,
  `modifyTime` bigint(20) DEFAULT NULL,
  `userHeadImg` varchar(255) DEFAULT NULL COMMENT '用户头像地址',
  `isDelete` tinyint(4) DEFAULT NULL COMMENT '用户是否删除  1-删除  0-未删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
