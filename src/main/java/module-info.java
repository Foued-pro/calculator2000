module bts.ciel.binding {
    requires javafx.controls;
    requires javafx.fxml;


    opens bts.ciel.binding to javafx.fxml;
    exports bts.ciel.binding;
}