package com.chilun.osprocessWithMemory.controller;

import com.chilun.osprocess.Model.MyProcessFactory;
import com.chilun.osprocess.MyApplication;
import com.chilun.osprocess.utils.UtilsMethods;
import com.chilun.osprocessWithMemory.model.pojoAndFactory.ProcessFactory;
import com.chilun.osprocessWithMemory.view.UtilMethods;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * @auther 齿轮
 * @create 2022-11-15-18:32
 * NewProcessController
 */

public class NewProcessController {
    @FXML
    public Button Button_Create;
    @FXML
    public ComboBox<String> Combo_Priority;
    @FXML
    private TextField Text_Size;
    @FXML
    public TextField Text_Runtime;
    @FXML
    public TextField Text_PID;
    @FXML
    public TextField Text_PCBPtr;

    public void Create(ActionEvent event) {
        String pid = Text_PID.getText();
        String runtime = Text_Runtime.getText();
        String size = Text_Size.getText();
        if(pid.equals("")||runtime.equals("")||size.equals("")){
            return;
        }
        int priority = Combo_Priority.getSelectionModel().getSelectedIndex();//0~7
        UtilMethods.addToNewStack(ProcessFactory.CreateProcess(pid,Integer.parseInt(runtime),priority,Integer.parseInt(size)));
        ((Stage)(Button_Create.getScene().getWindow())).close();
    }
}