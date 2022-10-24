package com.chilun.osprocess.utils;

import com.chilun.osprocess.Model.MyProcess;
import com.chilun.osprocess.MyApplication;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @auther 齿轮
 * @create 2022-10-09-17:19
 */
public class UtilsMethods {
    private static final Stack<MyProcess> TempStorageForNewProcess;
    private static List<MyProcess> NewList;
    private static List<MyProcess> ReadyList;
    private static List<MyProcess> RunningList;
    private static List<MyProcess> WaitingList;
    private static List<MyProcess> TerminatedList;
    private static List<MyProcess> HaltList;
    private static boolean isBlocking;
    private static MyProcess RunningIO;
    public static int tracks;
    private static int LastClickedItemIndex;
    private static int LastClickedListIndex;

    static {
        TempStorageForNewProcess = new Stack<MyProcess>();
        tracks = 8;
        NewList = new ArrayList<>();
        ReadyList = new ArrayList<>();
        RunningList = new ArrayList<>();
        WaitingList = new ArrayList<>();
        TerminatedList = new ArrayList<>();
        HaltList = new ArrayList<>();
        isBlocking = false;
        RunningIO = null;
        LastClickedItemIndex = -1;
        LastClickedListIndex = -1;
    }

    public static int getLastClickedItemIndex() {
        return LastClickedItemIndex;
    }

    public static void setLastClickedItemIndex(int lastClickedItemIndex) {
        LastClickedItemIndex = lastClickedItemIndex;
    }

    public static int getLastClickedListIndex() {
        return LastClickedListIndex;
    }

    public static void setLastClickedListIndex(int lastClickedListIndex) {
        LastClickedListIndex = lastClickedListIndex;
    }

    public static void addProcessToQueue(MyProcess process, ListView listView) {
        int state = process.getState();
        if (state == 2) {
            if (listView.getItems().size() >= UtilsMethods.tracks) {
                System.out.println("满了！");
                return;
            }
        }
        switch (state) {
            case 0:
                NewList.add(process);
                Collections.sort(NewList);
                break;
            case 1:
                ReadyList.add(process);
                Collections.sort(ReadyList);
                break;
            case 2:
                RunningList.add(process);
                Collections.sort(RunningList);
                break;
            case 3:
                WaitingList.add(process);
                Collections.sort(WaitingList);
                break;
            case 4:
                TerminatedList.add(process);
                Collections.sort(TerminatedList);
                break;
        }
        RedisplayQueue(listView, state);
    }

    public static MyProcess getProcessFromCreate() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MyApplication.class.getResource("NewProcess.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 362, 288);
        Stage stage = new Stage();
        stage.setTitle("CreateNewProcess");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
        return getFromNewStack();
    }

    public static void addToNewStack(MyProcess process) {
        TempStorageForNewProcess.push(process);
    }

    public static MyProcess getFromNewStack() {
        return TempStorageForNewProcess.pop();
    }

    public static void NewListToReadyList() {
        ReadyList.addAll(NewList);
        NewList.forEach((P) -> {
            P.setState(1);
        });
        NewList.clear();
    }

    public static void ClearListView(ListView listView) {
        listView.getItems().clear();
    }

    public static void RedisplayQueue(ListView listView, int index) {//index用来指明刷新显示那个queue
        ClearListView(listView);
        List list = new ArrayList();
        switch (index) {
            case 0:
                NewList.forEach((P) -> {
                    list.add(P.toString());
                });
                listView.setItems(FXCollections.observableList(list));
                break;
            case 1:
                ReadyList.forEach((P) -> {
                    list.add(P.toString());
                });
                listView.setItems(FXCollections.observableList(list));
                break;
            case 2:
                RunningList.forEach((P) -> {
                    list.add(P.toString());
                });
                listView.setItems(FXCollections.observableList(list));
                break;
            case 3:
                WaitingList.forEach((P) -> {
                    list.add(P.toString());
                });
                listView.setItems(FXCollections.observableList(list));
                break;
            case 4:
                TerminatedList.forEach((P) -> {
                    list.add(P.toString());
                });
                listView.setItems(FXCollections.observableList(list));
                break;
            case 5:
                HaltList.forEach((P) -> {
                    list.add(P.toString());
                });
                listView.setItems(FXCollections.observableList(list));
                break;
        }

    }

    public static void ReadyListToRunningList() {
        if (!isBlocking) {
            ReadyListToRunningListWithoutIO();
        } else {
            ReadyListToRunningListWithIO();
        }
        //逻辑：running中已有在使用IO设备的进程时，其他IO进程不许进
        //running中不存在使用IO设备的进程时，其他的全部一起进，等到冲突了再放进waiting队列
    }

    private static void ReadyListToRunningListWithIO() {
        int numOfRest = UtilsMethods.tracks - RunningList.size();
        List<MyProcess> tempList = new ArrayList<>();
        AtomicInteger count = new AtomicInteger();
        for (int i = 0; i <= 7; i++) {
            int finalI = i;
            ReadyList.forEach((P) -> {
                if (P.getPriority() == finalI && count.get() != numOfRest && P.getIO() == 0) {
                    tempList.add(P);
                    count.getAndIncrement();
                }
            });
            if (count.get() == numOfRest) break;
        }

        ReadyList.removeAll(tempList);
        RunningList.addAll(tempList);
        tempList.forEach((P) -> {
            P.setState(2);
        });
        Collections.sort(RunningList);
    }

    private static void ReadyListToRunningListWithoutIO() {
        int numOfRest = UtilsMethods.tracks - RunningList.size();
        List<MyProcess> tempList = new ArrayList<>();
        AtomicInteger count = new AtomicInteger();
        for (int i = 0; i <= 7; i++) {
            int finalI = i;
            ReadyList.forEach((P) -> {
                if (P.getPriority() == finalI && count.get() != numOfRest) {
                    tempList.add(P);
                    count.getAndIncrement();
                }
            });
            if (count.get() == numOfRest) break;
        }

        ReadyList.removeAll(tempList);
        RunningList.addAll(tempList);
        tempList.forEach((P) -> {
            P.setState(2);
        });
        Collections.sort(RunningList);
    }

    public static void ChangeRunningList() {
        List<MyProcess> IOProcesses = new ArrayList<>();
        RunningList.forEach((P) -> {
            if (P.getIO() == 1) {
                IOProcesses.add(P);
            }
        });
        if (IOProcesses.size() == 0) {
            ChangeRunningListWith01IO();
        } else if (IOProcesses.size() == 1) {
            RunningIO = IOProcesses.get(0);
            isBlocking = true;
            ChangeRunningListWith01IO();
        } else {
            for (int i = 0; i <= 7; i++) {
                if (RunningIO != null) break;
                for (MyProcess ioProcess : IOProcesses) {
                    if (ioProcess.getPriority() == i) {
                        RunningIO = ioProcess;
                        isBlocking = true;
                        break;
                    }
                }
            }
            IOProcesses.remove(RunningIO);
            RunningList.removeAll(IOProcesses);
            WaitingList.addAll(IOProcesses);
            IOProcesses.forEach((P) -> {
                P.setState(3);
            });
            Collections.sort(WaitingList);
            ChangeRunningListWith01IO();
        }
    }

    private static void ChangeRunningListWith01IO() {
        MyProcess tempProcess;
        for (int i = RunningList.size() - 1; i >= 0; i--) {
            tempProcess = RunningList.get(i);
            tempProcess.setRunTime(tempProcess.getRunTime() - 1);
            if (tempProcess.getRunTime() <= 0) {
                RunningList.remove(i);
                TerminatedList.add(tempProcess);
                tempProcess.setState(4);
                Collections.sort(TerminatedList);
                if (tempProcess.getIO() == 1) {
                    isBlocking = false;
                    RunningIO = null;
                }
            }
        }
    }

    public static void WaitingListToReadyList() {
        if (!isBlocking) {
            ReadyList.addAll(WaitingList);
            WaitingList.forEach((P) -> {
                P.setState(1);
            });
            Collections.sort(ReadyList);
            WaitingList.clear();
        }
    }

    public static void printAllList() {
        System.out.println("NewList");
        NewList.forEach(System.out::println);
        System.out.println("ReadyList:");
        ReadyList.forEach(System.out::println);
        System.out.println("RunningList:");
        RunningList.forEach(System.out::println);
        System.out.println("WaitingList:");
        WaitingList.forEach(System.out::println);
        System.out.println("TerminatedList:");
        TerminatedList.forEach(System.out::println);
    }

    public static void ClearConcreteList(int index) {
        switch (index) {
            case 0:
                NewList.clear();
                break;
            case 1:
                ReadyList.clear();
                break;
            case 2:
                RunningList.clear();
                break;
            case 3:
                WaitingList.clear();
                break;
            case 4:
                TerminatedList.clear();
                break;
            case 5:
                HaltList.clear();
                break;
        }
    }

    public static void ChangeReadyPriority() {

        ReadyList.forEach((P) -> {
            int tempP = P.getPriority();
            if (tempP != 0)
                P.setPriority(tempP - 1);
        });
    }

    public static int ReturnNumOfAll() {
        return NewList.size() + ReadyList.size() + RunningList.size() + WaitingList.size() + TerminatedList.size() + HaltList.size();
    }


    public static void ChangeToHalt(int listIndex, int itemIndex) {
        List<MyProcess> list;
        switch (listIndex) {
            case 0:
                list = NewList;
                break;
            case 1:
                list = ReadyList;
                break;
            case 2:
                list = RunningList;
                break;
            case 3:
                list = WaitingList;
                break;
            default:
                System.out.println("useless halt");
                return;
        }
        MyProcess temp = list.get(itemIndex);
        list.remove(itemIndex);
        HaltList.add(temp);
        Collections.sort(HaltList);
    }

    public static void ChangeFromHalt(int index) {
        if (index == 0) {
            MyProcess temp = HaltList.get(LastClickedItemIndex);
            HaltList.remove(LastClickedItemIndex);
            NewList.add(temp);
            Collections.sort(NewList);
        } else {
            MyProcess temp = HaltList.get(LastClickedItemIndex);
            temp.setState(1);
            HaltList.remove(LastClickedItemIndex);
            ReadyList.add(temp);
            Collections.sort(ReadyList);
        }
    }

    public static void WholeHalt(int listIndex, ListView listView, ListView Halt) {
        int itemIndex = listView.getSelectionModel().getSelectedIndex();//获得选中index
        if (UtilsMethods.getLastClickedListIndex() == listIndex && UtilsMethods.getLastClickedItemIndex() == itemIndex) {//判断是否为双击
            if (listIndex == 2 && UtilsMethods.ClickedIsIO()) {//如果为running队列且为IO类型，取消堵塞
                isBlocking = false;
                RunningIO = null;
                System.out.println("确认清除");
            }
            UtilsMethods.ChangeToHalt(listIndex, itemIndex);//内部更改具体list
            UtilsMethods.RedisplayQueue(listView, listIndex);//重新显示对应listview
            UtilsMethods.RedisplayQueue(Halt, 5);//重新显示haltListView
            UtilsMethods.setLastClickedItemIndex(-1);//清空单击记录
            UtilsMethods.setLastClickedListIndex(-1);
        } else {//记录单击
            UtilsMethods.setLastClickedItemIndex(itemIndex);
            UtilsMethods.setLastClickedListIndex(listIndex);
        }
    }

    private static boolean ClickedIsIO() {
        return RunningList.get(LastClickedItemIndex) == RunningIO;
    }

    public static MyProcess getProcessFromHalt(int itemIndex) {
        return HaltList.get(itemIndex);
    }


    public static void OtherTestIO() {
        for (MyProcess process : RunningList) {
            if (process.getIO() == 1) {
                return;
            }
        }
        isBlocking = false;
    }
}
