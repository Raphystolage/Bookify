DELETE FROM `Book`;
INSERT INTO `Book` (title, author, type, releaseDate, price)
VALUES
    ('Harry Potter and the Philosopher’s Stone', 'J. K. Rowling', 'NOVEL', '1997-06-26', 22.50),
    ('The Lord of the Rings: The Fellowship of the Ring', 'J. R. R. Tolkien', 'NOVEL', '1954-07-29', 19.99),
    ('A Song of Ice and Fire: A Game of Thrones', 'George R. R. Martin', 'NOVEL', '1996-08-01', 18.50),
    ('One Piece, Vol. 1: Romance Dawn', 'Eiichiro Oda', 'MANGA', '1997-12-24', 6.99),
    ('The Contemplations', 'Victor Hugo', 'POETRY', '1856-01-01', 13.03);
