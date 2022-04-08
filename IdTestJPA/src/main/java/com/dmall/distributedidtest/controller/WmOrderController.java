/**
 * @Author beiping.pei
 * @data 2020.03.05 15:37
 */
package com.dmall.distributedidtest.controller;


import com.dmall.distributedidtest.domain.first.FirstUser;
import com.dmall.distributedidtest.domain.second.SecondUser;
import com.dmall.distributedidtest.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@RestController
public class WmOrderController {
    Logger logger = LoggerFactory.getLogger(WmOrderController.class);
    @Autowired
    private UserService userService;

    //插入单行2DB
    @RequestMapping(value = "/insertUser", produces = "application/json;charset=UTF-8")
    public @ResponseBody
    String insertUser() {
        userService.saveUser();
        return "{ok}";
    }

    @RequestMapping(value = "/insertListAndCheck", produces = "application/json;charset=UTF-8")
    public @ResponseBody
    String insertListAndCheck() {
        ArrayList<FirstUser> list = new ArrayList<>();

        long orderId = System.nanoTime() + Thread.currentThread().getId();
        for (int i = 0; i < 1000; i++) {
            FirstUser order = new FirstUser();
            order.setName(Thread.currentThread().getName() + System.nanoTime()  + UUID.randomUUID());
            order.setOrderId(orderId);
            list.add(order);
            try {
                Thread.sleep((long) 1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        userService.insertListBatch(list);

        List<FirstUser> resultList = userService.selectByOrderId(orderId);

        boolean returnResult = true;
        for (int i = 0; i < resultList.size(); i++) {
            FirstUser userInput =  list.get(i);
            FirstUser userResult =  resultList.get(i);
            if(!userInput.getId().equals(userResult.getId()) ||
                    !userInput.getName().equals(userResult.getName()) ||
                    !userInput.getOrderId().equals(userResult.getOrderId())  ){
                returnResult = false;
            }
        }
        return  returnResult?"ok":"not ok";
    }

    @RequestMapping(value = "/saveUserUpdate", produces = "application/json;charset=UTF-8")
    public @ResponseBody
    String saveUserUpdate() {
        userService.saveUserUpdate();
        return "{ok}";
    }

    @RequestMapping(value = "/saveUserDelete", produces = "application/json;charset=UTF-8")
    public @ResponseBody
    String saveUserDelete() {
        userService.saveUserDelete();
        return "{ok}";
    }

    @RequestMapping(value = "/jdbcTemp", produces = "application/json;charset=UTF-8")
    public @ResponseBody
    String jdbcTmp() {
        long count  = userService.jdbcTemp();
        return "{ok}" + count;
    }

    @RequestMapping(value = "/saveUserList", produces = "application/json;charset=UTF-8")
    public @ResponseBody
    String saveUserList() {
        userService.saveUserList();
        return "{ok}";
    }

    @RequestMapping(value = "/findSecondAll", produces = "application/json;charset=UTF-8")
    public @ResponseBody
    List<SecondUser> findSecondAll(){
        return userService.findSecondAll();
    }

    @RequestMapping(value = "/findFirstAll", produces = "application/json;charset=UTF-8")
    public @ResponseBody
    List<FirstUser> findFirstAll(){
        return userService.findFirstAll();
    }
}