CREATE TABLE IF NOT EXISTS `Book` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `title` VARCHAR(200) NOT NULL,
    `author` VARCHAR(100) NOT NULL,
    `type` VARCHAR(50) NOT NULL,
    `releaseDate` DATE NOT NULL,
    `price` FLOAT NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `BookifyUser` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `username` VARCHAR(100) NOT NULL,
    `password` VARCHAR(100) NOT NULL,
    `authority` VARCHAR(50) NOT NULL,
    PRIMARY KEY (`id`)
);