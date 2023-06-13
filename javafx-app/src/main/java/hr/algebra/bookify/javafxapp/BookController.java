package hr.algebra.bookify.javafxapp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class BookController implements Initializable {

    private final BookService bookService = new BookService();

    private final Book selectedBook = null;

    @FXML
    private TextField getByIdTextField;

    @FXML
    private TableView<Book> bookTable;

    @FXML
    private TableColumn<Book, Long> idColumn;

    @FXML
    private TableColumn<Book, String> titleColumn;

    @FXML
    private TableColumn<Book, String> authorColumn;

    @FXML
    private TableColumn<Book, BookType> typeColumn;

    @FXML
    private TableColumn<Book, LocalDate> releaseDateColumn;

    @FXML
    private TableColumn<Book, String> priceColumn;

    @FXML
    protected void onGetAllButtonClick() {
        List<Book> books = bookService.getAll();

        for(Book book : books) {
            System.out.println(book);
        }

        ObservableList<Book> bookList = FXCollections.observableArrayList();
        bookList.addAll(books);

        bookTable.setItems(bookList);
    }

    @FXML
    protected void onGetByIdButtonClick() {
        long id;
        try {
            id = Long.parseLong(getByIdTextField.getText());
            Book book = bookService.getById(id);
            ObservableList<Book> bookList = FXCollections.observableArrayList();
            bookList.add(book);
            bookTable.setItems(bookList);
            System.out.println(book);
        } catch(NumberFormatException nfe) {
            return;
        }
    }

    protected void openSaveView(Book... book) throws IOException {
        Stage saveStage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(BookifyApplication.class.getResource("save-view.fxml"));
        if(book.length>0) {
            SaveController sc = new SaveController(book[0]);
            fxmlLoader.setController(sc);
        } else {
            fxmlLoader.setController(new SaveController());
        }
        Scene scene = new Scene(fxmlLoader.load());
        saveStage.setTitle("Add/Edit Book");
        saveStage.setScene(scene);
        saveStage.show();
    }

    @FXML
    protected void addBook() throws IOException {
        this.openSaveView();
    }

    @FXML
    protected void updateBook() throws IOException {
        boolean selected = !bookTable.getSelectionModel().isEmpty();
        Book selectedBook = bookTable.getSelectionModel().getSelectedItem();

        if(selected) {
            this.openSaveView(selectedBook);
        }
    }

    @FXML
    protected void deleteBook() {
        boolean selected = !bookTable.getSelectionModel().isEmpty();
        Book selectedBook = bookTable.getSelectionModel().getSelectedItem();
        if(selected) {
            bookService.deleteById(selectedBook.getId());
            this.onGetAllButtonClick();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        releaseDateColumn.setCellValueFactory(new PropertyValueFactory<>("releaseDate"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

}
