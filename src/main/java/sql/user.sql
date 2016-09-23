/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50715
Source Host           : localhost:3306
Source Database       : ssm

Target Server Type    : MYSQL
Target Server Version : 50715
File Encoding         : 65001

Date: 2016-09-23 16:54:29
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
  `createTime` bigint(12) DEFAULT NULL COMMENT '创建时间',
  `modifyTime` bigint(12) DEFAULT NULL COMMENT '最后修改时间',
  `isDelete` tinyint(4) DEFAULT NULL COMMENT '是否被删除',
  `userHeadImg` varchar(255) DEFAULT NULL COMMENT '用户头像保存地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('7', '今晚打老虎', '15349274650', 'zhuchunyao164488421@hotmail.com', '123456', '1474612916701', '1474616334562', '0', null);
