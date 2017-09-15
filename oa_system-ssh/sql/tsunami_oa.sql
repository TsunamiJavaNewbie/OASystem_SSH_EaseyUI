/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50553
Source Host           : localhost:3306
Source Database       : togogo_oa

Target Server Type    : MYSQL
Target Server Version : 50553
File Encoding         : 65001

Date: 2017-03-06 10:16:47
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_department
-- ----------------------------
DROP TABLE IF EXISTS `sys_department`;
CREATE TABLE `sys_department` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `department_name` varchar(20) DEFAULT NULL COMMENT '部门名称',
  `pid` int(11) DEFAULT NULL COMMENT '上级部门',
  `addr` varchar(250) DEFAULT NULL COMMENT '地址',
  `status` int(1) DEFAULT NULL COMMENT '状态',
  `create_time` date DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_department
-- ----------------------------
INSERT INTO `sys_department` VALUES ('1', '总公司', '0', '333', '1', '2016-08-10');
INSERT INTO `sys_department` VALUES ('10', '广州分公司', '1', '天河路118号', '1', '2016-08-10');
INSERT INTO `sys_department` VALUES ('12', '上海分公司', '1', '浦东区', '1', '2016-08-10');
INSERT INTO `sys_department` VALUES ('16', '中国总公司', '0', 'BBB', '1', '2016-08-11');
INSERT INTO `sys_department` VALUES ('22', '中文', '10', '中文', '0', null);

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `dept_name` varchar(20) DEFAULT NULL COMMENT '部门名称',
  `pid` int(11) DEFAULT NULL COMMENT '上级部门',
  `addr` varchar(250) DEFAULT NULL COMMENT '地址',
  `status` char(1) DEFAULT NULL COMMENT '状态',
  `create_time` date DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_dept
-- ----------------------------

-- ----------------------------
-- Table structure for sys_job
-- ----------------------------
DROP TABLE IF EXISTS `sys_job`;
CREATE TABLE `sys_job` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `job_name` varchar(50) DEFAULT NULL COMMENT '职位名称',
  `jon_code` varchar(20) DEFAULT NULL COMMENT '职位编码',
  `status` char(1) DEFAULT NULL COMMENT '状态',
  `create_time` date DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_job
-- ----------------------------

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `menu_name` varchar(50) DEFAULT NULL COMMENT '菜单名称',
  `url` varchar(200) DEFAULT NULL COMMENT '路径',
  `pid` int(11) DEFAULT NULL COMMENT '上级ID',
  `status` char(1) DEFAULT NULL COMMENT '状态',
  `create_time` date DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1', '系统管理', null, '0', '1', '2016-08-09');
INSERT INTO `sys_menu` VALUES ('2', '用户管理', '/system/user', '1', '1', '2016-08-09');
INSERT INTO `sys_menu` VALUES ('3', '部门管理', '/system/department', '1', '1', null);
INSERT INTO `sys_menu` VALUES ('4', '角色管理', '/system/role', '1', '1', '2016-08-09');
INSERT INTO `sys_menu` VALUES ('5', '菜单管理', '/system/menu', '1', '1', '2016-08-09');
INSERT INTO `sys_menu` VALUES ('17', '业务功能', null, '0', '1', '2016-08-09');
INSERT INTO `sys_menu` VALUES ('18', '新闻管理', '', '17', '1', '2016-08-09');
INSERT INTO `sys_menu` VALUES ('21', '请假流程', '', '17', '1', '2016-08-09');
INSERT INTO `sys_menu` VALUES ('22', '上传文件测试', '/system/fileAjaxUpload', '17', '1', '2016-08-10');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_name` varchar(50) DEFAULT NULL COMMENT '角色名称',
  `role_code` varchar(20) DEFAULT NULL COMMENT '角色编码',
  `status` int(1) DEFAULT NULL COMMENT '状态',
  `create_time` date DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('52', '系统管理员', 'ROLE_ADMIN', '1', null);
INSERT INTO `sys_role` VALUES ('56', '公司总经理', 'ROLE_MANAGER_GENERAL', '1', '2016-08-08');
INSERT INTO `sys_role` VALUES ('57', '部门经理', 'ROLE_MANAGER', '1', '2016-08-08');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_id` int(11) DEFAULT NULL COMMENT '角色ID',
  `menu_id` int(11) DEFAULT NULL COMMENT '菜单ID',
  `create_time` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=90 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES ('79', '56', '3', null);
INSERT INTO `sys_role_menu` VALUES ('80', '56', '21', null);
INSERT INTO `sys_role_menu` VALUES ('81', '52', '1', null);
INSERT INTO `sys_role_menu` VALUES ('82', '52', '2', null);
INSERT INTO `sys_role_menu` VALUES ('83', '52', '3', null);
INSERT INTO `sys_role_menu` VALUES ('84', '52', '4', null);
INSERT INTO `sys_role_menu` VALUES ('85', '52', '5', null);
INSERT INTO `sys_role_menu` VALUES ('86', '52', '17', null);
INSERT INTO `sys_role_menu` VALUES ('87', '52', '18', null);
INSERT INTO `sys_role_menu` VALUES ('88', '52', '21', null);
INSERT INTO `sys_role_menu` VALUES ('89', '52', '22', null);

-- ----------------------------
-- Table structure for sys_role_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_user`;
CREATE TABLE `sys_role_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_id` int(11) DEFAULT NULL COMMENT '角色ID',
  `user_id` int(11) DEFAULT NULL COMMENT '用户ID',
  `create_time` date DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role_user
-- ----------------------------
INSERT INTO `sys_role_user` VALUES ('11', '1', '56', null);

-- ----------------------------
-- Table structure for sys_type
-- ----------------------------
DROP TABLE IF EXISTS `sys_type`;
CREATE TABLE `sys_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type_name` varchar(50) DEFAULT NULL COMMENT '类型名称',
  `type_code` varchar(20) DEFAULT NULL COMMENT '类型编码',
  `pid` int(11) DEFAULT NULL COMMENT '上级ID',
  `status` char(1) DEFAULT NULL COMMENT '状态',
  `create_time` date DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_type
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(50) DEFAULT NULL COMMENT '用户名',
  `password` varchar(50) DEFAULT NULL COMMENT '密码',
  `department_id` int(11) DEFAULT NULL COMMENT '部门ID',
  `job_id` int(11) DEFAULT NULL COMMENT '职位',
  `mobile` varchar(20) DEFAULT NULL COMMENT '手机',
  `sex` char(4) DEFAULT NULL COMMENT '性别',
  `carid` varchar(20) DEFAULT NULL COMMENT '身份证',
  `url_photo` varchar(200) DEFAULT NULL COMMENT '照片',
  `url_certificate` varchar(200) DEFAULT NULL COMMENT '证书',
  `status` char(1) DEFAULT NULL COMMENT '状态',
  `create_time` date DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', '张三', '1111', '10', '0', '1802222222', '男', 'null', 'G:	ogogo课程servletproject.metadata.pluginsorg.eclipse.wst.server.core	mp2wtpwebappsoaupload20160811/20160811-030035.txt', '', '1', '2016-08-11');
INSERT INTO `sys_user` VALUES ('4', '李四', '12321', '13', '0', '21321321', '男', '12222', '/upload/20160811/20160811-031022.txt', '/upload/20160811/20160811-031022.txt', '1', '2016-08-11');
INSERT INTO `sys_user` VALUES ('11', '111', '312', null, null, '111', '?', '1', null, null, '1', null);
INSERT INTO `sys_user` VALUES ('12', '1111', '2222', null, null, '111', '?', '111', null, null, '1', '2017-01-03');
INSERT INTO `sys_user` VALUES ('13', '1111', '2222', null, null, '111', '?', '111', null, null, '1', '2017-01-03');
INSERT INTO `sys_user` VALUES ('14', '111', '22', null, null, '111', '?', '111', null, null, '1', '2017-01-03');
INSERT INTO `sys_user` VALUES ('15', '1111', '222', null, null, '222', '?', '11111', null, null, '1', '2017-01-03');
INSERT INTO `sys_user` VALUES ('16', '1111', '11222', null, null, '11', '?', '111', null, null, '1', '2017-01-03');

-- ----------------------------
-- Table structure for t_girl
-- ----------------------------
DROP TABLE IF EXISTS `t_girl`;
CREATE TABLE `t_girl` (
  `girl_id` int(11) NOT NULL AUTO_INCREMENT,
  `girl_name` varchar(255) DEFAULT NULL,
  `create_time` date DEFAULT NULL,
  PRIMARY KEY (`girl_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_girl
-- ----------------------------
INSERT INTO `t_girl` VALUES ('1', null, '2016-12-30');
INSERT INTO `t_girl` VALUES ('2', 'Tim', '2016-12-30');
INSERT INTO `t_girl` VALUES ('3', '4444', '2016-12-30');
INSERT INTO `t_girl` VALUES ('4', 'Tim3333', '2016-12-30');
INSERT INTO `t_girl` VALUES ('5', 'xxxxxxx', '2016-12-30');
INSERT INTO `t_girl` VALUES ('6', 'Tim', '2016-12-30');
INSERT INTO `t_girl` VALUES ('7', 'Tim', '2016-12-30');
INSERT INTO `t_girl` VALUES ('8', null, '2016-12-30');
INSERT INTO `t_girl` VALUES ('9', '33333', null);
SET FOREIGN_KEY_CHECKS=1;
