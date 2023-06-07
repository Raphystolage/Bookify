package hr.algebra.bookify.backend.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "Book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String author;
    @Enumerated(value = EnumType.STRING)
    private BookType type;
    private LocalDate releaseDate;
    private double price;

    public Book() {}
    public Book(Long id, String title, String author, BookType type, LocalDate releaseDate, double price) {
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
    public double getPrice() {
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
    public void setPrice(double price) {
        this.price = price;
    }

}
