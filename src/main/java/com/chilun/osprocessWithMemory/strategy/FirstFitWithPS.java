package com.chilun.osprocessWithMemory.strategy;

import com.chilun.osprocessWithMemory.model.service.OSService;

/**
 * @auther 齿轮
 * @create 2022-11-13-23:04
 */
public class FirstFitWithPS {

    public void nextStep(){
        OSService.ChangeRunningList();
        //接下来要补全队列按优先级排序的方法和有关主存的一系列方法


//        UtilsMethods.ReadyListToRunningList();
//        UtilsMethods.ChangeReadyPriority();
//        UtilsMethods.WaitingListToReadyList();
//        UtilsMethods.NewListToReadyList();
    }
}
