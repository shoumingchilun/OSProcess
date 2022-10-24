package com.chilun.osprocess.Controller;

/**
 * @auther 齿轮
 * @create 2022-10-09-16:14
 */

import com.chilun.osprocess.Model.MyProcessFactory;
import com.chilun.osprocess.MyApplication;
import com.chilun.osprocess.utils.UtilsMethods;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class NewProcessController {
    @FXML
    public Button Button_Create;
    @FXML
    public ComboBox<String> Combo_State;
    @FXML
    public ComboBox<String> Combo_Priority;
    @FXML
    public TextField Text_Runtime;
    @FXML
    public TextField Text_PID;
    @FXML
    public TextField Text_PCBPtr;
    @FXML
    public ComboBox<String> Combo_IO;//默认为cpu型


    public void Create(ActionEvent event) {
        String pid = Text_PID.getText();
        String runtime = Text_Runtime.getText();
        String pcbPtr = Text_PCBPtr.getText();
        if(pid.equals("")||runtime.equals("")||pcbPtr.equals("")){
            return;
        }
        int State = Combo_State.getSelectionModel().getSelectedIndex();//0~4，不能直接放在挂起队列中
        int priority = Combo_Priority.getSelectionModel().getSelectedIndex();//0~7
        int IO = Combo_IO.getSelectionModel().getSelectedIndex();//0~1
        UtilsMethods.addToNewStack(MyProcessFactory.newProcess(Text_PID.getText(), Integer.parseInt(Text_Runtime.getText()), priority, State, Text_PCBPtr.getText(),IO));
        ((Stage)(Button_Create.getScene().getWindow())).close();
    }
}
