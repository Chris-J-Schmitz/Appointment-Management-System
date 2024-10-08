module com.example.helloworldjfxtemplate {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;



    // Open the Main package
    opens com.example.helloworldjfxtemplate.Main to javafx.fxml;
    exports com.example.helloworldjfxtemplate.Main;
    // Open Controller package
    opens com.example.helloworldjfxtemplate.controller to javafx.fxml;
    exports com.example.helloworldjfxtemplate.controller;
    // Open DAO package
    opens com.example.helloworldjfxtemplate.DAO to javafx.fxml;
    exports com.example.helloworldjfxtemplate.DAO;
    // Open Model package
    opens com.example.helloworldjfxtemplate.model to javafx.fxml;
    exports com.example.helloworldjfxtemplate.model;
}
