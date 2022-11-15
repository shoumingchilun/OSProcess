package com.chilun.osprocessWithMemory.view;

import com.chilun.osprocessWithMemory.model.pojoAndFactory.Memory;
import com.chilun.osprocessWithMemory.model.pojoAndFactory.NoAllocateItem;
import com.chilun.osprocessWithMemory.model.pojoAndFactory.Process;
import com.chilun.osprocessWithMemory.model.pojoAndFactory.ProcessFactory;
import javafx.collections.FXCollections;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;
import java.util.List;

/**
 * @auther 齿轮
 * @create 2022-11-14-21:49
 */
public class UtilMethods {

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
}
