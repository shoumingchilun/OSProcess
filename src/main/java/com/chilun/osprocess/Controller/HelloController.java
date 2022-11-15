package com.chilun.osprocess.Controller;

import com.chilun.osprocess.Model.MyProcess;
import com.chilun.osprocess.Model.MyProcessFactory;
import com.chilun.osprocess.MyApplication;
import com.chilun.osprocess.utils.UtilsMethods;
import com.chilun.osprocessWithMemory.strategy.FirstFitWithPS;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class HelloController {
    @FXML
    public Button Button_createDaultQueue;
    @FXML
    Button Button_AddNewProcess;
    @FXML
    Button Button_RestartAndChange;
    @FXML
    Button Button_ClearAllQueue;
    @FXML
    Button Button_ClearNewQueue;
    @FXML
    Button Button_NextStep;
    @FXML
    ListView List_HaltQueue;
    @FXML
    ListView List_NewQueue;
    @FXML
    ListView List_ReadyQueue;
    @FXML
    ListView List_WaitingQueue;
    @FXML
    ListView List_RunningQueue;
    @FXML
    ListView List_TerminatedQueue;

    public void AddNewProcess(ActionEvent event) throws IOException {
        MyProcess newProcess = UtilsMethods.getProcessFromCreate();
        //这里要加一个判断，添加到哪个队列
        ListView list;
        List<MyProcess> concreteList;
        switch (newProcess.getState()) {
            case 1:
                list = List_ReadyQueue;
                break;
            case 2:
                list = List_RunningQueue;
                break;
            case 3:
                list = List_WaitingQueue;
                break;
            case 4:
                list = List_TerminatedQueue;
                break;
            default:
                list = List_NewQueue;
        }
        UtilsMethods.addProcessToQueue(newProcess, list);
        UtilsMethods.printAllList();
    }


    public void NextStep(ActionEvent event) {
        //这应该是个大方法，得拆分，new的全到ready，ready的按优先级到running（详细见下一条），running的减时间，时间为0的放到结束队列;
        //waiting队列判断I/O设备有无被占用，被占用的不变，没被占用就到ready
        //注：不用担心running一下到Waiting再到Ready，因为堵塞了才能到Waiting，不堵塞才能到Ready
        //IO使用逻辑：running中已有在使用IO设备的进程时，其他IO进程不许进
        //running中不存在使用IO设备的进程时，其他的全部一起进，等到冲突了再放进waiting队列
        //running中出现IO冲突时，低优先级的进入waiting；同优先级的随便一个进入waiting
        UtilsMethods.OtherTestIO();
        UtilsMethods.ChangeRunningList();
        UtilsMethods.ReadyListToRunningList();
        UtilsMethods.ChangeReadyPriority();
        UtilsMethods.WaitingListToReadyList();
        UtilsMethods.NewListToReadyList();
        UtilsMethods.ClearListView(List_NewQueue);
//        UtilsMethods.RedisplayQueue(List_NewQueue,0);
        UtilsMethods.RedisplayQueue(List_ReadyQueue, 1);
        UtilsMethods.RedisplayQueue(List_RunningQueue, 2);
        UtilsMethods.RedisplayQueue(List_WaitingQueue, 3);
        UtilsMethods.RedisplayQueue(List_TerminatedQueue, 4);
        UtilsMethods.printAllList();
    }

    public void RestartAndShiftDefaultQueue(ActionEvent event) {
        ClearAllQueue(event);
        CreateNewQueue(event);
    }

    public void ClearAllQueue(ActionEvent event) {
        UtilsMethods.ClearListView(List_NewQueue);
        UtilsMethods.ClearListView(List_ReadyQueue);
        UtilsMethods.ClearListView(List_RunningQueue);
        UtilsMethods.ClearListView(List_WaitingQueue);
        UtilsMethods.ClearListView(List_TerminatedQueue);
        UtilsMethods.ClearListView(List_HaltQueue);
        UtilsMethods.ClearConcreteList(0);
        UtilsMethods.ClearConcreteList(1);
        UtilsMethods.ClearConcreteList(2);
        UtilsMethods.ClearConcreteList(3);
        UtilsMethods.ClearConcreteList(4);
        UtilsMethods.ClearConcreteList(5);
    }

    public void ClearNewQueue(ActionEvent event) {
        UtilsMethods.ClearListView(List_NewQueue);
        UtilsMethods.ClearConcreteList(0);
    }

    public void BeginPlay(ActionEvent event) {
    }

    public void ChangeTracks(ActionEvent event) {

    }

    public void CreateNewQueue(ActionEvent event) {
        int count = UtilsMethods.ReturnNumOfAll();
        MyProcess myProcesses[] = new MyProcess[20];
        for (int i = 0; i < 20; i++) {
            myProcesses[i] = MyProcessFactory.newProcess("进程" + (count + i), (int) (Math.random() * 15 + 1), (int) (Math.random() * 8),
                    0, "(自动生成)", (int) (Math.random() + 0.15));
            UtilsMethods.addProcessToQueue(myProcesses[i], List_NewQueue);
        }
        UtilsMethods.printAllList();
    }

    public void ClickedNewToHalt(MouseEvent mouseEvent) {
        UtilsMethods.WholeHalt(0, List_NewQueue, List_HaltQueue);
    }

    public void ClickedReadyToHalt(MouseEvent mouseEvent) {
        UtilsMethods.WholeHalt(1, List_ReadyQueue, List_HaltQueue);
    }

    public void ClickedRunningToHalt(MouseEvent mouseEvent) {
        UtilsMethods.WholeHalt(2, List_RunningQueue, List_HaltQueue);
    }

    public void ClickedWaitingToHalt(MouseEvent mouseEvent) {
        UtilsMethods.WholeHalt(3, List_WaitingQueue, List_HaltQueue);
    }

    public void ClickedHalt(MouseEvent mouseEvent) {
        int itemIndex = List_HaltQueue.getSelectionModel().getSelectedIndex();//获得选中index
        if (UtilsMethods.getLastClickedListIndex() == 5 && UtilsMethods.getLastClickedItemIndex() == itemIndex) {//判断是否为双击
            MyProcess tempProcess = UtilsMethods.getProcessFromHalt(itemIndex);
            int index = tempProcess.getState();
            ListView listView = null;
            if (index == 0) {
                UtilsMethods.ChangeFromHalt(index);
                listView = List_NewQueue;
            } else {
                index = 1;
                UtilsMethods.ChangeFromHalt(index);
                listView = List_ReadyQueue;
            }
            UtilsMethods.RedisplayQueue(listView, index);//重新显示对应listview
            UtilsMethods.RedisplayQueue(List_HaltQueue, 5);//重新显示haltListView
            UtilsMethods.setLastClickedItemIndex(-1);//清空单击记录
            UtilsMethods.setLastClickedListIndex(-1);
        } else {
            UtilsMethods.setLastClickedItemIndex(itemIndex);
            UtilsMethods.setLastClickedListIndex(5);
        }
    }
}