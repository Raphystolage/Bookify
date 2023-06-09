package hr.algebra.bookify.frontend;

import java.time.LocalDate;

public class Book {

    private Long id;
    private String title;
    private String author;
    private BookType type;
    private LocalDate releaseDate;
    private Double price;

    public Book() {}
    public Book(Long id, String title, String author, BookType type, LocalDate releaseDate, Double price) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.type = type;
        this.releaseDate = releaseDate;
        this.price = price;
    }

    public Long getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public String getAuthor() {
        return author;
    }
    public BookType getType() {
        return type;
    }
    public LocalDate getReleaseDate() {
        return releaseDate;
    }
    public Double getPrice() {
        return price;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public void setType(BookType type) {
        this.type = type;
    }
    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }
    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "================================\n" +
                "Book id: " + this.getId() + "\n" +
                "Book title: " + this.getTitle() + "\n" +
                "Book author: " + this.getAuthor() + "\n" +
                "Book type: " + this.getType() + "\n" +
                "Book release date: " + this.getReleaseDate() + "\n" +
                "Book price: " + this.getPrice();
    }
}
