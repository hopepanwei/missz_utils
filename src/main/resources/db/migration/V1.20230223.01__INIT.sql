CREATE TABLE `cmn_short_url`
(
    `ID`          bigint(20) NOT NULL AUTO_INCREMENT,
    `SHORTS`      varchar(100)        DEFAULT NULL,
    `LONG_URL`    text                DEFAULT NULL,
    `SHORT_URL`   varchar(100)        DEFAULT NULL,
    `CREATE_TIME` datetime   NOT NULL DEFAULT current_timestamp(),
    `ISVALID`     char(2)             DEFAULT '1',
    `UPDATE_TIME` datetime            DEFAULT NULL,
    PRIMARY KEY (`ID`),
    KEY `cmn_short_url_SHORTS_index` (`SHORTS`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;

CREATE TABLE `cmn_md5`
(
    `ID`         bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `MD5_KEY`    varchar(100) DEFAULT NULL,
    `VALUE`      varchar(100) DEFAULT NULL,
    `KEY_LENGTH` int(11)      DEFAULT NULL,
    PRIMARY KEY (`ID`),
    KEY `cmn_md5_MD5_KEY_IDX` (`MD5_KEY`) USING BTREE,
    KEY `cmn_md5_VALUE_IDX` (`VALUE`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci
    PARTITION BY HASH (ID) PARTITIONS 10;