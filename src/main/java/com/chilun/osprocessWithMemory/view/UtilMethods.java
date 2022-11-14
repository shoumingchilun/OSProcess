package com.chilun.osprocessWithMemory.view;

import com.chilun.osprocessWithMemory.model.pojoAndFactory.Memory;
import com.chilun.osprocessWithMemory.model.pojoAndFactory.Process;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;

import java.util.List;

/**
 * @auther 齿轮
 * @create 2022-11-14-21:49
 */
public class UtilMethods {
    public static void cleanTableView(TableView<?> tableView_new) {

    }

    public static void cleanViewList(ListView<?> listView_noAllocateTable) {

    }


    public static void updateAll(TableView<?> tableView_new, List<Process> newList, TableView<?> tableView_ready, List<Process> readyList, TableView<?> tableView_running, List<Process> runningList, TableView<?> tableView_terminated, List<Process> terminatedList, ListView<?> listView_noAllocateTable, Memory memory) {
    }
}
