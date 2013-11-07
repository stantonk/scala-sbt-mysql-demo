CREATE TABLE `messages` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `message` text,
  `created_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `messages` (message, created_time)
VALUES
    ("first post!", "2013-11-01 00:41:00"),
    ("2nd post", "2013-11-02 00:41:00"),
    ("3nd post", "2013-11-03 00:41:00");