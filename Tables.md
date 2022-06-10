# 数据表

创建乘客user数据表：

```
CREATE TABLE `user` (
  `id` int NOT NULL COMMENT '用户id',
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `money` int DEFAULT NULL COMMENT '金额',
  `card_type` varchar(255) DEFAULT NULL COMMENT 'Card类型',
  `card_money` int DEFAULT '0' COMMENT 'Card余额',
  `loss_times` int DEFAULT NULL COMMENT '失信次数',
  `is_black` int DEFAULT NULL COMMENT '是否为失信黑名单用户',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
```

创建单程卡single_card数据表：

```
CREATE TABLE `single_card` (
  `id` int NOT NULL COMMENT '乘客id',
  `start_node` int DEFAULT NULL COMMENT '起点id',
  `end_node` int DEFAULT NULL COMMENT '终点id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
```

创建地铁线subway_line数据表：

```
CREATE TABLE `subwayline` (
  `id` int NOT NULL COMMENT 'id',
  `name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL COMMENT '名称',
  `stations` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL COMMENT '地铁站点ID集合，以 : 分割',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
```

创建地铁站点subway_station数据表：

```
CREATE TABLE `subway_station` (
  `id` int NOT NULL COMMENT 'id',
  `name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL COMMENT '名称',
  `weight` int DEFAULT NULL COMMENT '权值（1~3）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
```

创建乘坐历史记录history数据表：

```
CREATE TABLE `history` (
  `id` int NOT NULL COMMENT 'ID',
  `time` datetime DEFAULT NULL COMMENT '时间',
  `start_node` varchar(255) DEFAULT NULL COMMENT '起点',
  `end_node` varchar(255) DEFAULT NULL COMMENT '终点',
  `black` bit(1) DEFAULT NULL COMMENT '失信记录'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
```
