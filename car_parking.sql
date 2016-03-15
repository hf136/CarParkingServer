/*
 Navicat MySQL Data Transfer

 Source Server         : mywin10
 Source Server Version : 50611
 Source Host           : 10.4.21.211
 Source Database       : car_parking

 Target Server Version : 50611
 File Encoding         : utf-8

 Date: 03/15/2016 11:26:47 AM
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
  `start_time` datetime NOT NULL,
  `end_time` datetime NOT NULL,
  `certificate` varchar(100) NOT NULL,
  `money` float NOT NULL,
  `state` varchar(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `parking`
-- ----------------------------
BEGIN;
INSERT INTO `parking` VALUES ('2', '中国人民大学地上停车场', '39.969556', '116.316762', '中关村大街59号', '0', '177', '22', '5135.jpg', 'P1003.png'), ('3', '三环社区地上停车场', '39.966805', '116.317872', '北三环西路45号', '0', '82', '0', '51141.jpg', 'P1005.png'), ('4', '北京同仁堂地上停车场', '39.967377', '116.32027', '北三环西路47号院乙1号', '0', '24', '0', '51140.jpg', 'P1005.png'), ('5', '友谊社区地上停车场', '39.966148', '116.315383', '北三环西路47号', '0', '120', '0', '51142.jpg', 'P1005.png'), ('6', '海淀大药房地上停车场', '39.969601', '116.320737', '海淀区中关村大街42号(人民大学东门对面)', '0', '24', '3', '5308.jpg', 'P1005.png');
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
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `user`
-- ----------------------------
BEGIN;
INSERT INTO `user` VALUES ('1', 'test', '', 'test'), ('8', 'adsafaf', null, 'weibo'), ('9', 'abc', null, 'weibo'), ('10', 'adsafadsadf', null, 'weibo'), ('11', 'as', null, 'weibo'), ('12', 'sfafadfaf', null, 'weibo'), ('13', '2179629643', null, 'weibo_sina'), ('14', 'test', null, 'weibo');
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

SET FOREIGN_KEY_CHECKS = 1;
