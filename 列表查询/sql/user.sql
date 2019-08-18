/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50721
Source Host           : localhost:3306
Source Database       : ada

Target Server Type    : MYSQL
Target Server Version : 50721
File Encoding         : 65001

Date: 2019-08-18 20:05:35
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `gender` varchar(5) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `address` varchar(32) DEFAULT NULL,
  `qq` varchar(20) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=1031 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1001', '张三', '女', '27', '广西', '123', '123@qq,com');
INSERT INTO `user` VALUES ('1002', '李四', '男', '21', '广东', '123', '123@qq,com');
INSERT INTO `user` VALUES ('1020', '少华', '女', '21', '湖南', '123445234', '1232323@qq.com');
INSERT INTO `user` VALUES ('1021', '12', '男', '12', '广东', '121223', '123312');
INSERT INTO `user` VALUES ('1023', '12', '男', '12', '广东', '121223', '123312');
INSERT INTO `user` VALUES ('1024', '12', '男', '12', '广东', '121223', '123312');
INSERT INTO `user` VALUES ('1025', '12', '男', '12', '广东', '121223', '123312');
INSERT INTO `user` VALUES ('1026', '1234', '男', '12', '广西', '1232331', '23123');
INSERT INTO `user` VALUES ('1030', '是', '女', '12', '广东', '1212332', '1234@qq.com');
