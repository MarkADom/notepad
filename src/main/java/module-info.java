module com.myproj.notepad {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.myproj.notepad to javafx.fxml;
    exports com.myproj.notepad;
}