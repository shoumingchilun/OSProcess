package com.chilun.osprocessWithMemory.controller;

import com.chilun.osprocess.Model.MyProcess;
import com.chilun.osprocess.Model.MyProcessFactory;
import com.chilun.osprocess.MyApplication;
import com.chilun.osprocess.utils.UtilsMethods;
import com.chilun.osprocessWithMemory.model.pojoAndFactory.MemoryFactory;
import com.chilun.osprocessWithMemory.model.pojoAndFactory.Process;
import com.chilun.osprocessWithMemory.model.pojoAndFactory.ProcessFactory;
import com.chilun.osprocessWithMemory.model.service.OSService;
import com.chilun.osprocessWithMemory.strategy.FirstFitWithPS;
import com.chilun.osprocessWithMemory.view.UtilMethods;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

/**
 * @auther 齿轮
 * @create 2022-11-14-20:46
 */

public class MainWindowController {

    private static final FirstFitWithPS fp = new FirstFitWithPS();

    @FXML
    private Button Button_ClearAll;

    @FXML
    private Button Button_CreateCustomProcess;

    @FXML
    private Button Button_CreateManyProcesses;

    @FXML
    private Button Button_NextStep;

    @FXML
    private ListView<Process> ListView_noAllocateTable;

    @FXML
    private TableView<Process> TableView_NEW;

    @FXML
    private TableView<Process> TableView_Ready;

    @FXML
    private TableView<Process> TableView_Running;

    @FXML
    private TableView<Process> TableView_Terminated;

    @FXML
    private AnchorPane PANE_SHAPE;

    @FXML
    void ClearAll(ActionEvent event) {
        OSService.cleanAll();
        updateView();
        OSService.printAll();
    }

    @FXML
    void CreateCustomProcess(ActionEvent event) throws IOException {
        if (!initialed) {
            initialAll();
        }
        OSService.addNew(UtilMethods.createNewProcess());
        updateView();
        OSService.printAll();
    }

    int createdNum = 0;

    @FXML
    void CreateManyProcesses(ActionEvent event) {
        if (!initialed) {
            initialAll();
        }
        for (int i = 0; i < 5; i++) {
            Process process = ProcessFactory.CreateProcess("进程" + createdNum++, (int) (Math.random() * 14) + 5, (int) (Math.random() * 8), (int) (Math.random() * 500) + 1);
            OSService.addNew(process);
        }
        updateView();
        OSService.printAll();
    }

    private void initialAll() {
        initialOne(TableView_NEW);
        initialOne(TableView_Ready);
        initialOne(TableView_Running);
        initialOne(TableView_Terminated);
    }

    private void initialOne(TableView<Process> tableView) {
        tableView.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("pid"));
        tableView.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("priority"));
        tableView.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("beginSite"));
        tableView.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("size"));
        tableView.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("runTime"));
        tableView.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("PCBPtr"));
    }

    @FXML
    void NextStep(ActionEvent event) {
        fp.nextStep();
        updateView();
        OSService.printAll();
    }

    void updateView() {
        UtilMethods.updateAll(TableView_NEW, OSService.getNewList(), TableView_Ready, OSService.getReadyList(),
                TableView_Running, OSService.getRunningList(), TableView_Terminated, OSService.getTerminatedList(),
                ListView_noAllocateTable, MemoryFactory.getMemory(), PANE_SHAPE);
    }

    boolean initialed = false;
}