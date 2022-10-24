module com.chilun.osprocess {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;


    opens com.chilun.osprocess to javafx.fxml;
    exports com.chilun.osprocess;
    exports com.chilun.osprocess.Controller;
    opens com.chilun.osprocess.Controller to javafx.fxml;
}