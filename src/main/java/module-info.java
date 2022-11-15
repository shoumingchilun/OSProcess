//module com.chilun.osprocess {
//    requires javafx.controls;
//    requires javafx.fxml;
//    requires javafx.graphics;
//
//
//    opens com.chilun.osprocess to javafx.fxml;
//    exports com.chilun.osprocess;
//    exports com.chilun.osprocess.Controller;
//    opens com.chilun.osprocess.Controller to javafx.fxml;
//}
module com.chilun.osprocessWithMemory{
        requires javafx.controls;
        requires javafx.fxml;
        requires javafx.graphics;


        opens com.chilun.osprocessWithMemory to javafx.fxml;
        exports com.chilun.osprocessWithMemory;
        exports com.chilun.osprocessWithMemory.controller;
        opens com.chilun.osprocessWithMemory.controller to javafx.fxml;
        opens com.chilun.osprocessWithMemory.model.pojoAndFactory to javafx.base;
}
