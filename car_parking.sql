/*
 Navicat MySQL Data Transfer

 Source Server         : mywin10
 Source Server Version : 50611
 Source Host           : 10.4.21.211
 Source Database       : car_parking

 Target Server Version : 50611
 File Encoding         : utf-8

 Date: 04/12/2016 16:47:16 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `appointment`
-- ----------------------------
DROP TABLE IF EXISTS `appointment`;
CREATE TABLE `appointment` (
  `id` int(20) unsigned NOT NULL AUTO_INCREMENT,
  `userid` int(15) NOT NULL,
  `time` datetime NOT NULL,
  `parkingid` int(15) NOT NULL,
  `start_time` datetime DEFAULT NULL,
  `end_time` datetime DEFAULT NULL,
  `certificate` varchar(100) NOT NULL,
  `money` float DEFAULT NULL,
  `state` varchar(10) NOT NULL,
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `appointment`
-- ----------------------------
BEGIN;
INSERT INTO `appointment` VALUES ('11', '15', '2014-10-05 20:20:15', '2', '2016-03-24 15:48:22', '2016-03-24 15:59:04', 'fTu1sIW5CsnYC4hwP0XF0w==', '10', '已消费', '2016-03-17 17:28:50'), ('12', '15', '2016-03-20 10:10:10', '2', null, null, 'Z2Cd7Jk9fgLpF4cYdydcEg==', '10', '未支付', '2016-03-20 10:56:21'), ('13', '15', '2016-03-26 12:28:00', '2', null, null, 'vF6IxmMdlAQzYtfO+ERDQQ==', '10', '未支付', '2016-03-26 09:28:42'), ('14', '15', '2016-03-26 10:36:00', '2', null, null, 'e6S06vOP3OeFsHYnohS75Q==', '5', '未支付', '2016-03-26 09:37:03'), ('15', '15', '2016-03-26 11:39:00', '2', null, null, 'Pl4o1ZWm7gLE+Za36s6APw==', '10', '未支付', '2016-03-26 10:39:42'), ('16', '15', '2016-03-28 09:00:00', '2', null, null, 'rbsi1tpndo9nohAqr9Ay7g==', '5', '未支付', '2016-03-28 20:07:00'), ('17', '15', '2016-03-28 09:00:00', '2', null, null, '/ijnwqpuMg0HH2dFcAvGpQ==', '10', '超时自动取消', '2016-03-28 20:09:56');
COMMIT;

-- ----------------------------
--  Table structure for `daysales`
-- ----------------------------
DROP TABLE IF EXISTS `daysales`;
CREATE TABLE `daysales` (
  `puserid` int(11) NOT NULL,
  `income` int(10) DEFAULT NULL,
  `orders` int(10) DEFAULT NULL,
  `date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `daysales`
-- ----------------------------
BEGIN;
INSERT INTO `daysales` VALUES ('2', '396', '41', '2016-04-02'), ('2', '432', '49', '2016-04-03'), ('2', '378', '37', '2016-04-01'), ('1', '390', '30', '2016-04-10'), ('2', '423', '40', '2016-04-04'), ('2', '433', '49', '2016-04-05'), ('2', '456', '53', '2016-04-06'), ('2', '450', '51', '2016-04-07');
COMMIT;

-- ----------------------------
--  Table structure for `parking`
-- ----------------------------
DROP TABLE IF EXISTS `parking`;
CREATE TABLE `parking` (
  `id` int(15) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `latitude` double NOT NULL,
  `longitude` double NOT NULL,
  `address` varchar(100) NOT NULL,
  `price` float DEFAULT NULL,
  `amount` int(10) NOT NULL,
  `available` int(10) NOT NULL,
  `image` text NOT NULL,
  `state` text NOT NULL,
  `puserid` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `parking`
-- ----------------------------
BEGIN;
INSERT INTO `parking` VALUES ('2', '中国人民大学地上停车场', '39.969556', '116.316762', '中关村大街59号', '10', '177', '49', 'http://images.juheapi.com/park/5135.jpg', 'P1003.png', '2'), ('3', '三环社区地上停车场', '39.966805', '116.317872', '北三环西路45号', '10', '82', '0', 'http://images.juheapi.com/park/51141.jpg', 'P1005.png', '1'), ('4', '北京同仁堂地上停车场', '39.967377', '116.32027', '北三环西路47号院乙1号', '10', '24', '0', 'http://images.juheapi.com/park/51140.jpg', 'P1005.png', '0'), ('5', '友谊社区地上停车场', '39.966148', '116.315383', '北三环西路47号', '10', '120', '0', 'http://images.juheapi.com/park/51142.jpg', 'P1005.png', '0'), ('6', '海淀大药房地上停车场', '39.969601', '116.320737', '海淀区中关村大街42号(人民大学东门对面)', '10', '24', '3', 'http://images.juheapi.com/park/5308.jpg', 'P1005.png', '0');
COMMIT;

-- ----------------------------
--  Table structure for `puser`
-- ----------------------------
DROP TABLE IF EXISTS `puser`;
CREATE TABLE `puser` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `puser`
-- ----------------------------
BEGIN;
INSERT INTO `puser` VALUES ('1', 'test2', 'test2'), ('2', 'test', 'test');
COMMIT;

-- ----------------------------
--  Table structure for `puserinfo`
-- ----------------------------
DROP TABLE IF EXISTS `puserinfo`;
CREATE TABLE `puserinfo` (
  `puserid` int(11) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`puserid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `puserinfo`
-- ----------------------------
BEGIN;
INSERT INTO `puserinfo` VALUES ('1', 'hf136', '13299996666', 'profile1');
COMMIT;

-- ----------------------------
--  Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(15) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(100) NOT NULL,
  `password` varchar(100) DEFAULT NULL,
  `platform` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `user`
-- ----------------------------
BEGIN;
INSERT INTO `user` VALUES ('8', 'adsafaf', null, 'weibo'), ('9', 'abc', null, 'weibo'), ('10', 'adsafadsadf', null, 'weibo'), ('11', 'as', null, 'weibo'), ('12', 'sfafadfaf', null, 'weibo'), ('13', '2179629643', null, 'weibo_sina'), ('15', 'test', 'test', 'local');
COMMIT;

-- ----------------------------
--  Table structure for `userinfo`
-- ----------------------------
DROP TABLE IF EXISTS `userinfo`;
CREATE TABLE `userinfo` (
  `userid` int(15) NOT NULL,
  `nickname` varchar(30) NOT NULL,
  `avatar` mediumtext,
  `sex` varchar(2) DEFAULT NULL,
  `phone` varchar(15) NOT NULL,
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `userinfo`
-- ----------------------------
BEGIN;
INSERT INTO `userinfo` VALUES ('15', 'haha test', null, '男', '13288889999');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
