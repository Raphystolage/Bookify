package hr.algebra.bookify.webapp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.LongStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class WebAppApplicationTests {

    @Autowired
    private BookService bookService;

    private final static List<Book> BOOKS = Arrays.asList(
            new Book(null,"Title 1", "Author 1", BookType.NOVEL, LocalDate.of(2001,12,20), 10.99),
            new Book(null,"Title 2", "Author 1", BookType.ESSAY, LocalDate.of(2001, 5,16), 12.99),
            new Book(null, "Title 3", "Author 2", BookType.POETRY, LocalDate.of(1997,12,20), 9.99),
            new Book(null, "Title 4", "Author 2", BookType.MANGA, LocalDate.of(1986, 5,16), 6.99)
    );

    @Test
    public void testCalculateTotalPriceByAuthor() {
        double totalPrice = bookService.calculateTotalPrice(BOOKS);
        assertEquals(40.96, totalPrice, 0.01);
    }

    @Test
    public void testFilterBooksByAuthor() {
        List<Book> filteredBooksByAuthor = bookService.filterBooksByAuthor(BOOKS, "Author 1");

        assertTrue(filteredBooksByAuthor.stream().allMatch(book -> Objects.equals(book.getAuthor(), "Author 1")));
        assertEquals(2, filteredBooksByAuthor.size());
    }

    @Test
    public void testSortBooksByReleaseDate() {
        long[] dates = bookService.sortBooksByReleaseDate(BOOKS).stream().mapToLong(book -> book.getReleaseDate().getLong(ChronoField.EPOCH_DAY)).toArray();
        boolean isSorted = LongStream.range(0, dates.length - 1).allMatch(i -> dates[(int) i] <= dates[(int) (i + 1)]);

        assertTrue(isSorted);
    }

    @Test
    public void testCreateBookIntegration() {
        Book book = new Book(null,"Title","Author",BookType.OTHER,LocalDate.now(),7.50);

        Book addedBook = bookService.getById(bookService.create(book).getId());
        assertEquals(book.getTitle(), addedBook.getTitle());
        assertEquals(book.getAuthor(), addedBook.getAuthor());
        assertEquals(book.getType(), addedBook.getType());
        assertEquals(book.getReleaseDate(), addedBook.getReleaseDate());
        assertEquals(book.getPrice(), addedBook.getPrice());
        bookService.deleteById(addedBook.getId());
    }



}
