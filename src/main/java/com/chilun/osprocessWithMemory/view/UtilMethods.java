package com.chilun.osprocessWithMemory.view;

import com.chilun.osprocessWithMemory.NewApplication;
import com.chilun.osprocessWithMemory.model.pojoAndFactory.Memory;
import com.chilun.osprocessWithMemory.model.pojoAndFactory.NoAllocateItem;
import com.chilun.osprocessWithMemory.model.pojoAndFactory.Process;
import com.chilun.osprocessWithMemory.model.pojoAndFactory.ProcessFactory;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @auther 齿轮
 * @create 2022-11-14-21:49
 */
public class UtilMethods {
    private static final Stack<Process> TempStorageForNewProcess = new Stack<>();

    public static void updateAll(TableView<Process> tableView_new, List<Process> newList, TableView<Process> tableView_ready, List<Process> readyList, TableView<Process> tableView_running, List<Process> runningList, TableView<Process> tableView_terminated, List<Process> terminatedList, ListView<Process> listView_noAllocateTable, Memory memory, AnchorPane PANE_SHAPE) {
        update(tableView_new, newList);
        update(tableView_ready, readyList);
        update(tableView_running, runningList);
        update(tableView_terminated, terminatedList);
        update(listView_noAllocateTable, memory);
        update(PANE_SHAPE, memory);
    }

    private static void update(AnchorPane pane_shape, Memory memory) {

    }

    public static void update(TableView<Process> tableView, List<Process> list) {
        tableView.getItems().clear();
        for (Process process : list) {
            tableView.getItems().add(process);
        }
    }

    public static void update(ListView<Process> listView, Memory memory) {
        memory.order();
        listView.getItems().clear();
        List<NoAllocateItem> noAllocateTable = memory.getNoAllocateTable();
        List strings = new ArrayList();
        for (NoAllocateItem item : noAllocateTable) {
            strings.add(item.toString() + "\t\tsize:" + (item.getSize()));
        }
        listView.setItems(FXCollections.observableList(strings));
    }

    public static Process createNewProcess() throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(NewApplication.class.getResource("NewProcess.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 360, 290);
        Stage stage = new Stage();
        stage.setTitle("创建新进程");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
        return getFromNewStack();
    }
    public static Process getFromNewStack() {
        return TempStorageForNewProcess.pop();
    }
    public static void addToNewStack(Process process) {
        TempStorageForNewProcess.push(process);
    }
}
