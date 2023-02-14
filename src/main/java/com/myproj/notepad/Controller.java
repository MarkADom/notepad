package com.myproj.notepad;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    private FileChooser fileChooser = new FileChooser();
    private File file;

    @FXML
    private TextArea textpane;

    @FXML
    private MenuItem newItem;

    @FXML
    protected void newFile(ActionEvent event) {
        textpane.clear();
        Stage stage = (Stage) textpane.getScene().getWindow();
        stage.setTitle("Untitled - Notepad");
        file = null;
    }

    @FXML
    protected void openFile(ActionEvent event) {
        file = fileChooser.showOpenDialog(null);
        if (file != null) {
            Stage stage = (Stage) textpane.getScene().getWindow();
            stage.setTitle(file.getName() + " - Notepad");
            BufferedReader br = null;
            try {
                String sCurrentLine;
                br = new BufferedReader(new FileReader(file));
                while ((sCurrentLine = br.readLine()) != null) {
                    textpane.appendText(sCurrentLine + "\n");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    protected void saveFile(ActionEvent event) {
        String content = textpane.getText();
        if (file != null) {
            try {
                //if file doesn't exist, then create it
                if (!file.exists()){
                    file.createNewFile();
                }
                FileWriter fw = new FileWriter(file.getAbsoluteFile());
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(content);
                bw.close();
            }catch (Exception e) {
                e.printStackTrace();
            }
        }else {
            //open the dialog box
            file = fileChooser.showSaveDialog(null);
            if (file!= null){
                Stage stage = (Stage) textpane.getScene().getWindow();
                stage.setTitle(file.getName() + " - Notepad");
                try {
                    //if doesn't exist, then create it
                    if (!file.exists()){
                        file.createNewFile();
                    }
                    FileWriter fw  = new FileWriter(file.getAbsoluteFile());
                    BufferedWriter bw = new BufferedWriter(fw);
                    bw.write(content);
                    bw.close();
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @FXML
    protected void saveAsFile(ActionEvent event) {
        file = fileChooser.showSaveDialog(null);

        String content = textpane.getText();
        if (file != null) {
            Stage stage = (Stage) textpane.getScene().getWindow();
            stage.setTitle(file.getName() + " - Notepad");
            try {
                //if doesn't exist created it
                if (!file.exists()){
                    file.createNewFile();
                }
                FileWriter fw = new FileWriter(file.getAbsoluteFile());
                BufferedWriter bw =  new BufferedWriter(fw);
                bw.write(content);
                bw.close();
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    protected void exitApp(ActionEvent event) {
        Platform.exit();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}