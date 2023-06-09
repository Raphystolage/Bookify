package hr.algebra.bookify.frontend;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SaveController implements Initializable {

    public final BookService bookService = new BookService();

    public Book selectedBook;

    @FXML
    private Label errorMessage;

    @FXML
    private TextField bookTitle;

    @FXML
    private TextField bookAuthor;

    @FXML
    private TextField bookType;

    @FXML
    private DatePicker bookReleaseDate;

    @FXML
    private ComboBox<String> typeComboBox;

    @FXML
    private TextField bookPrice;

    @FXML
    private Button saveButton;

    @FXML
    public void onSaveButtonClick() {

        String title = bookTitle.getText(), author = bookAuthor.getText(), type = typeComboBox.getValue(), priceString = bookPrice.getText();
        LocalDate releaseDate = bookReleaseDate.getValue();
        Long id = selectedBook!=null ? selectedBook.getId() : null;
        Double price = null;

        if(title.isBlank()||author.isBlank()||type==null||priceString.isBlank()||releaseDate==null) {
            errorMessage.setText("Empty field");
            errorMessage.setTextFill(Paint.valueOf("#FF0000"));
            return;
        }

        try {
            price = Double.parseDouble(priceString);
        } catch(NumberFormatException nfe) {
            errorMessage.setText("Unexpected price format (xxx.xx)");
            errorMessage.setTextFill(Paint.valueOf("#FF0000"));
        }

        Book book = new Book(id, title, author, BookType.valueOf(type), releaseDate, price);
        if (selectedBook!=null) {
            bookService.update(book);
        } else {
            bookService.create(book);
        }
        Stage currentStage = (Stage) saveButton.getScene().getWindow();
        currentStage.close();
    }

    public SaveController() {}
    public SaveController(Book selectedBook) {
        this.selectedBook = selectedBook;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        saveButton.setOnAction(e -> this.onSaveButtonClick());
        typeComboBox.setItems(FXCollections.observableArrayList(Stream.of(BookType.values()).map(BookType::name).collect(Collectors.toList())));
        if(this.selectedBook!=null) {
            bookTitle.setText(selectedBook.getTitle());
            bookAuthor.setText(selectedBook.getAuthor());
            bookReleaseDate.setValue(selectedBook.getReleaseDate());
            typeComboBox.setValue(selectedBook.getType().toString());
            bookPrice.setText(selectedBook.getPrice().toString());
        }
    }

}
