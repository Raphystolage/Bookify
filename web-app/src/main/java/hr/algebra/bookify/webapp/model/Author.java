package hr.algebra.bookify.webapp.model;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Objects;

public class Author {

    private String name;
    private String surname;
    private LocalDate dateOfBirth;

    public Author(String name, String surname, LocalDate dateOfBirth) {
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
    }

    public Author() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public String toString() {
        return "Author{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                '}';
    }

    public static void printAuthorList(List<Author> authors) {
        int i = 0;
        while(i < authors.size()) {
            System.out.println(authors.get(i));
            i++;
        }
    }

}
