module hr.algebra.bookify.frontend {
    requires javafx.controls;
    requires javafx.fxml;
    requires spring.web;


    opens hr.algebra.bookify.frontend to javafx.fxml;
    exports hr.algebra.bookify.frontend;
}