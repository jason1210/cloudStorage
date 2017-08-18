/*
Navicat MySQL Data Transfer

Source Server         : mams-master
Source Server Version : 50631
Source Host           : 183.131.15.25:3306
Source Database       : cmes

Target Server Type    : MYSQL
Target Server Version : 50631
File Encoding         : 65001

Date: 2017-08-18 10:03:46
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for cloud_disk_producer
-- ----------------------------
DROP TABLE IF EXISTS `cloud_disk_producer`;
CREATE TABLE `cloud_disk_producer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account` varchar(64) NOT NULL COMMENT '云盘账号',
  `password` varchar(64) NOT NULL COMMENT '云盘密码',
  `open_id_producer` varchar(64) NOT NULL COMMENT '提供者openId，用户的唯一标识，根据APPID以及天翼账号用户标识生成',
  `access_token` varchar(255) DEFAULT NULL COMMENT '访问令牌，是调用平台能力接口的通行证',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `cloud_disk_producer_index` (`open_id_producer`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='天翼云盘视频提供者信息';

-- ----------------------------
-- Records of cloud_disk_producer
-- ----------------------------
INSERT INTO `cloud_disk_producer` VALUES ('1', '15168411120', '0okm9ijn', '9876011026603208542472941', '1f5c39fbcc9f83ce2f9e5424cc0d8d531502784137310', '2017-08-15 18:26:55');
INSERT INTO `cloud_disk_producer` VALUES ('2', '15925621887', 'jason1210', '9876011026633604032992941', 'feced99f8b33be4bf668d7574164babc1502187075438', '2017-08-17 15:31:06');

-- ----------------------------
-- Table structure for cloud_disk_user_collect
-- ----------------------------
DROP TABLE IF EXISTS `cloud_disk_user_collect`;
CREATE TABLE `cloud_disk_user_collect` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `open_id_collector` varchar(64) NOT NULL COMMENT '收藏者openId，用户的唯一标识，根据APPID以及天翼账号用户标识生成',
  `open_id_producer` varchar(64) NOT NULL COMMENT '提供者openId，用户的唯一标识，根据APPID以及天翼账号用户标识生成',
  `file_id` varchar(64) NOT NULL COMMENT '文件id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `flag` tinyint(1) DEFAULT '1' COMMENT '1=收藏；0=未收藏',
  PRIMARY KEY (`id`),
  KEY `cloud_disk_user_collect_index` (`open_id_collector`,`open_id_producer`,`file_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COMMENT='天翼云盘用户收藏表';

-- ----------------------------
-- Records of cloud_disk_user_collect
-- ----------------------------
INSERT INTO `cloud_disk_user_collect` VALUES ('11', '9876011026633503907912941', '9876011026633604032992941', '8152221691807270', '2017-08-17 15:44:03', '2017-08-17 15:44:03', '1');
INSERT INTO `cloud_disk_user_collect` VALUES ('13', '9876011026633503907912941', '9876011026633604032992941', '4130531686684327', '2017-08-17 15:45:46', '2017-08-17 15:45:46', '1');
INSERT INTO `cloud_disk_user_collect` VALUES ('14', '9876011026633604032992941', '9876011026633604032992941', '8152221691807270', '2017-08-17 17:27:43', '2017-08-17 17:27:43', '1');
