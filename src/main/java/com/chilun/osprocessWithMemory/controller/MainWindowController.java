package com.chilun.osprocessWithMemory.controller;

import com.chilun.osprocess.Model.MyProcess;
import com.chilun.osprocess.Model.MyProcessFactory;
import com.chilun.osprocess.MyApplication;
import com.chilun.osprocess.utils.UtilsMethods;
import com.chilun.osprocessWithMemory.model.pojoAndFactory.MemoryFactory;
import com.chilun.osprocessWithMemory.model.pojoAndFactory.Process;
import com.chilun.osprocessWithMemory.model.pojoAndFactory.ProcessFactory;
import com.chilun.osprocessWithMemory.model.service.OSService;
import com.chilun.osprocessWithMemory.view.UtilMethods;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

/**
 * @auther 齿轮
 * @create 2022-11-14-20:46
 */

public class MainWindowController {
    @FXML
    private Button Button_ClearAll;

    @FXML
    private Button Button_CreateCustomProcess;

    @FXML
    private Button Button_CreateManyProcesses;

    @FXML
    private Button Button_CreateRandomProcess;

    @FXML
    private Button Button_NextStep;

    @FXML
    private ListView<?> ListView_noAllocateTable;

    @FXML
    private TableView<?> TableView_NEW;

    @FXML
    private TableView<?> TableView_Ready;

    @FXML
    private TableView<?> TableView_Running;

    @FXML
    private TableView<?> TableView_Terminated;

    @FXML
    void ClearAll(ActionEvent event) {
        OSService.cleanAll();
        UtilMethods.cleanTableView(TableView_NEW);
        UtilMethods.cleanTableView(TableView_Ready);
        UtilMethods.cleanTableView(TableView_Running);
        UtilMethods.cleanTableView(TableView_Terminated);
        UtilMethods.cleanViewList(ListView_noAllocateTable);
    }

    @FXML
    void CreateCustomProcess(ActionEvent event) {
        System.out.println("CreateCustomProcess");
    }

    @FXML
    void CreateManyProcesses(ActionEvent event) {
        System.out.println("CreateManyProcesses");
    }

    @FXML
    void CreateRandomProcess(ActionEvent event) {
        double pid = Math.random() * 100 + 100;
        Process process = ProcessFactory.CreateProcess("" + pid, (int) (Math.random() * 14) + 5, (int) (Math.random() * 8), (int) (Math.random() * 500) + 1);
        OSService.addNew(process);
        updateView();
        OSService.printAll();
    }

    @FXML
    void NextStep(ActionEvent event) {
        System.out.println("NextStep");
    }

    void updateView(){
        UtilMethods.updateAll(TableView_NEW,OSService.getNewList(),TableView_Ready,OSService.getReadyList(),
                TableView_Running,OSService.getRunningList(),TableView_Terminated,OSService.getTerminatedList(),
                ListView_noAllocateTable, MemoryFactory.getMemory());
    }
}