SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission` (
  `permission_id` int(11) NOT NULL AUTO_INCREMENT,
  `permission_name` varchar(255) DEFAULT NULL,
  `permission_url` varchar(255) DEFAULT NULL,
  `parent_permission_id` int(11) DEFAULT NULL,
  `permission_lv` int(255) DEFAULT '0' COMMENT '1为1级 2为2级',
  PRIMARY KEY (`permission_id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES ('1', '用户管理', null, '0', '1');
INSERT INTO `permission` VALUES ('2', '新增修改', '/user-post', '1', '2');
INSERT INTO `permission` VALUES ('3', '列表查询', '/user-get', '1', '2');
INSERT INTO `permission` VALUES ('4', '删除用户', '/user/{id}-delete', '1', '2');
INSERT INTO `permission` VALUES ('6', '角色管理', null, '0', '1');
INSERT INTO `permission` VALUES ('7', '新增修改', '/role-post', '6', '2');
INSERT INTO `permission` VALUES ('8', '列表查询', '/role-get', '6', '2');
INSERT INTO `permission` VALUES ('9', '删除角色', '/role/{id}-delete', '6', '2');
INSERT INTO `permission` VALUES ('11', '角色授权', '/role-authorization-post', '6', '2');
INSERT INTO `permission` VALUES ('13', '权限管理', '', '0', '1');
INSERT INTO `permission` VALUES ('14', '新增修改', '/permission-post', '13', '2');
INSERT INTO `permission` VALUES ('15', '列表查询', '/permission-get', '13', '2');
INSERT INTO `permission` VALUES ('16', '删除权限', '/permission/{id}-delete', '13', '2');
INSERT INTO `permission` VALUES ('17', '预览权限', '/permission-view-get', '13', '2');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', '用户管理员');
INSERT INTO `role` VALUES ('3', '角色管理员');
INSERT INTO `role` VALUES ('4', '权限管理员');
INSERT INTO `role` VALUES ('5', '超级管理员');

-- ----------------------------
-- Table structure for role_permission
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) DEFAULT NULL,
  `permission_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=94 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role_permission
-- ----------------------------
INSERT INTO `role_permission` VALUES ('43', '3', '7');
INSERT INTO `role_permission` VALUES ('44', '3', '8');
INSERT INTO `role_permission` VALUES ('45', '3', '9');
INSERT INTO `role_permission` VALUES ('46', '3', '11');
INSERT INTO `role_permission` VALUES ('47', '4', '14');
INSERT INTO `role_permission` VALUES ('48', '4', '15');
INSERT INTO `role_permission` VALUES ('49', '4', '16');
INSERT INTO `role_permission` VALUES ('50', '4', '17');
INSERT INTO `role_permission` VALUES ('51', '5', '2');
INSERT INTO `role_permission` VALUES ('52', '5', '3');
INSERT INTO `role_permission` VALUES ('53', '5', '4');
INSERT INTO `role_permission` VALUES ('54', '5', '7');
INSERT INTO `role_permission` VALUES ('55', '5', '8');
INSERT INTO `role_permission` VALUES ('56', '5', '9');
INSERT INTO `role_permission` VALUES ('57', '5', '11');
INSERT INTO `role_permission` VALUES ('58', '5', '14');
INSERT INTO `role_permission` VALUES ('59', '5', '15');
INSERT INTO `role_permission` VALUES ('60', '5', '16');
INSERT INTO `role_permission` VALUES ('61', '5', '17');
INSERT INTO `role_permission` VALUES ('91', '1', '2');
INSERT INTO `role_permission` VALUES ('92', '1', '3');
INSERT INTO `role_permission` VALUES ('93', '1', '4');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) DEFAULT NULL,
  `user_role_names` varchar(255) DEFAULT NULL,
  `user_account` varchar(20) DEFAULT NULL COMMENT '账号',
  `user_password` varchar(150) DEFAULT NULL COMMENT '密码',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('13', '许耀辉', '用户管理员', 'user', '123456');
INSERT INTO `user` VALUES ('14', '许耀辉', '超级管理员', 'admin', '123456');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('5', '13', '1');
INSERT INTO `user_role` VALUES ('7', '14', '5');
SET FOREIGN_KEY_CHECKS=1;
