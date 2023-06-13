module hr.algebra.bookify.frontend {
    requires javafx.controls;
    requires javafx.fxml;
    requires spring.web;


    opens hr.algebra.bookify.javafxapp to javafx.fxml;
    exports hr.algebra.bookify.javafxapp;
}