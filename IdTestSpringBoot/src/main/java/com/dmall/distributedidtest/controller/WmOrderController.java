/**
 * @Author beiping.pei
 * @data 2020.03.05 15:37
 */
package com.dmall.distributedidtest.controller;


import com.alibaba.fastjson.JSONObject;
import com.dmall.distributedidtest.bean.JsonDemo;
import com.dmall.distributedidtest.bean.WmOrderEntity;
import com.dmall.distributedidtest.service.WmOrderService;
import com.dmall.distributedidtest.util.Time;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;


@RestController
public class WmOrderController {
    @Autowired
    private WmOrderService wmOrderServce;
    public int count;


    Logger logger = LoggerFactory.getLogger(WmOrderController.class);

    Map<Long, Long> map = new ConcurrentHashMap<Long, Long>();

    //http://localhost:8080/insertWmOrder
    //插入单行进单库（主库）不带·符号的插入
    @RequestMapping(value = "/insertWmOrder", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public @ResponseBody
    String insertWmOrder(@RequestBody WmOrderEntity wmOrderEntity) {
        wmOrderServce.insertWmOrder(wmOrderEntity);
        Long id = wmOrderEntity.getId();
        return "{id: " + id + "}";
    }

    @RequestMapping(value = "/queryWmOrder")
    public @ResponseBody
    List<WmOrderEntity> querySonarProject() {
        List<WmOrderEntity> result = wmOrderServce.selectByOrderId(null);
        return result;
    }


    //插入单行进单2个库1个事务
    @RequestMapping(value = "/insertOrder2DB", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public @ResponseBody
    String insertOrder2DB(@RequestBody WmOrderEntity wmOrderEntity) {
        wmOrderServce.insertOrder2DB(wmOrderEntity);
        Long id = wmOrderEntity.getId();
        return "{id: " + id + "}";
    }

    @RequestMapping(value = "/insertWmOrder5", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public @ResponseBody
    String insertWmOrder5(@RequestBody WmOrderEntity wmOrderEntity) {
        ArrayList<WmOrderEntity> list = new ArrayList();
        for (int i = 0; i < 5; i++) {
            list.add(wmOrderEntity);
        }
        wmOrderServce.insertWmOrderList(list);
        return "{ok}";
    }

    @RequestMapping(value = "/insertWmOrder400", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public @ResponseBody
    String insertWmOrder400(@RequestBody WmOrderEntity wmOrderEntity) {
        ArrayList<WmOrderEntity> list = new ArrayList();
        for (int i = 0; i < 400; i++) {
            list.add(wmOrderEntity);
        }
        wmOrderServce.insertWmOrderList(list);
        return "{ok}";
    }

    //需要运行jmap 检测ConcurrentLinkedQueue$Node是否持续增加
    @RequestMapping(value = "/insertWmOrder70", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public @ResponseBody
    String insertWmOrder70(@RequestBody WmOrderEntity wmOrderEntity) {
        for(int k=0;k<20;k++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 10000; j++) {
                        ArrayList<WmOrderEntity> list = new ArrayList();
                        for (int i = 0; i < 40; i++) {
                            list.add(wmOrderEntity);
                        }
                        wmOrderServce.insertWmOrderList(list);
                    }
                }
            }).start();
        }

        return "{ok}";
    }

    //批量插入1个库
    @RequestMapping(value = "/insertWmOrderList", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public @ResponseBody
    String insertWmOrderList(@RequestBody List<WmOrderEntity> orderlist) {
        int counter = wmOrderServce.insertOrderList(orderlist);
        return "{count: " + counter + "}";
    }

    //批量插入2个库2个事务
    @RequestMapping(value = "/insertList", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public @ResponseBody
    String insertList(@RequestBody List<WmOrderEntity> orderlist) {

        int counter = wmOrderServce.insertOrderList(orderlist);
        int counter2 = wmOrderServce.insertWmOrderList(orderlist);


        return "{count: " + counter + ", count2: " + counter2 + "}";
    }

    //批量插入2个库1个事务
    @RequestMapping(value = "/insertList2DB", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public @ResponseBody
    String insertWmList(@RequestBody List<WmOrderEntity> orderlist) {
        int counter = wmOrderServce.insertList2DB(orderlist);
        return "{count: " + counter + "}";
    }

    //批量插入并检查数据一致
    @RequestMapping(value = "/insertListAndCheck", produces = "application/json;charset=UTF-8")
    public @ResponseBody
    String insertListAndCheck() {
        ArrayList<WmOrderEntity> list = new ArrayList();
        long orderId = System.nanoTime() + Thread.currentThread().getId();

        for (int i = 0; i < 1000; i++) {
            WmOrderEntity orderEntity = new WmOrderEntity();
            orderEntity.setWare_name(Thread.currentThread().getName() + System.nanoTime() + UUID.randomUUID());
            orderEntity.setOrder_id(orderId);
            list.add(orderEntity);
            try {
                Thread.sleep((long) 1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        wmOrderServce.insertWmOrderList(list);

        WmOrderEntity queryEntity = new WmOrderEntity();
        queryEntity.setOrder_id(orderId);
        List<WmOrderEntity> resultList = wmOrderServce.selectByOrderId(queryEntity);

        boolean returnResult = true;
        for (int i = 0; i < resultList.size(); i++) {
            WmOrderEntity orderEntityInput = list.get(i);
            WmOrderEntity orderEntityResult = resultList.get(i);
            if (!orderEntityInput.getId().equals(orderEntityResult.getId()) ||
                    !orderEntityInput.getWare_name().equals(orderEntityResult.getWare_name()) ||
                    !orderEntityInput.getOrder_id().equals(orderEntityResult.getOrder_id())) {
                returnResult = false;
            }
        }
        return returnResult ? "ok" : "not ok";
    }

    //单行插入并更新，排除agent对update的影响
    @RequestMapping(value = "/insertWmOrderAndUpdate", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public @ResponseBody
    String insertWmOrderAndUpdate(@RequestBody WmOrderEntity wmOrderEntity) {

        wmOrderServce.insertWmOrder(wmOrderEntity);
        Long id = wmOrderEntity.getId();

        WmOrderEntity newEntity = new WmOrderEntity();
        newEntity.setId(id);
        newEntity.setSku_id(0);
        newEntity.setWare_name("new ware name by program");
        newEntity.setItem_num("00000");

        wmOrderServce.updatewmorder(newEntity);
        return "{id:" + id + "}";
    }

    //单行插入并删除，排除agent对Delete的影响
    @RequestMapping(value = "/insertWmOrderAndDelete", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public @ResponseBody
    String insertWmOrderAndDelete(@RequestBody WmOrderEntity wmOrderEntity) {
        //插入
        wmOrderServce.insertWmOrder(wmOrderEntity);
        Long id = wmOrderEntity.getId();
        //删除
        wmOrderServce.delete(id);
        return "{id: " + id + "}";
    }


    @RequestMapping(value = "/SqlType", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public @ResponseBody
    String SaveTest(@RequestBody WmOrderEntity wmOrderEntity) {
        //sql inter
        wmOrderServce.insert(wmOrderEntity);
        //获取inter 回写ID
        Long id = wmOrderEntity.getId();
        //并发inter获取所有主键id和Order_id
        count++;

        logger.info("统计count数据" + count);
        //打印id
        System.out.println("打印id回写：" + id);
        map.put(wmOrderEntity.getOrder_id(), wmOrderEntity.getId());

        logger.info("全部主键id和Order_id的集合" + map.toString());
        //inter语句Order_id字段查询主键id
        List<WmOrderEntity> wmOrderiatEntitiesList = new ArrayList<WmOrderEntity>();
        wmOrderiatEntitiesList = wmOrderServce.selectByOrderId(wmOrderEntity);
        //查询orderid和主键id一对一
        if (wmOrderiatEntitiesList.size() == 1) {
            for (WmOrderEntity wmOrderEntity1 : wmOrderiatEntitiesList) {
                Long id1 = wmOrderEntity1.getId();
                String Properties = wmOrderEntity1.getDmall_coupon_detail_new();
                logger.info(Properties);
                // 打印Emoji表情是否成功inter
                JsonDemo p1 = JSONObject.parseObject(Properties, JsonDemo.class);
                logger.info("打印Emoji表情" + p1.getEmoji() + "  返回orderID:" + wmOrderEntity.getOrder_id());

                if (id.equals(id1)) {
                    logger.info("测试成功,interId和QueryId完全一致");
                } else {
                    logger.info("测试失败" + "Order_id:" + wmOrderEntity.getOrder_id() + " " + "interid:" + " " + id + "queryid:" + id1);
                }
            }
            //查询orderid和主键id一对多
        } else {
            List<Long> list = new ArrayList<Long>();
            for (int x = 0; x < wmOrderiatEntitiesList.size(); x++) {
                Long id2 = wmOrderiatEntitiesList.get(x).getId();
                list.add(id2);
            }
            if (list.contains(id)) {
                logger.info("测试成功,interId和QueryId完全一致");
            } else {
                logger.info("测试失败" + "Order_id:" + wmOrderEntity.getOrder_id() + " " + "interid:" + " " + id + "queryid:" + list.toString());
            }
        }
        return "成功";
    }

    //1.测试ID是否重复，2.一定范围顺序递增
    @RequestMapping(value = "/queryId")
    public void QueryIdTest() {
        List<WmOrderEntity> wmOrderiatEntitiesListAll = new ArrayList<WmOrderEntity>();
        List<Long> SortList = new ArrayList<Long>();
        List<Long> AllId = new ArrayList<Long>();
        //set去重
        HashSet<Long> hashSet = new HashSet<>(AllId);
        //判断元素是否唯一
        if (hashSet.size() != AllId.size()) {
            logger.info("存在重复元素");
        } else {
            logger.info("分布式ID发号唯一");
        }

        wmOrderiatEntitiesListAll = wmOrderServce.selectAllId();
        wmOrderiatEntitiesListAll.forEach(wmOrderEntityId -> {
            AllId.add(wmOrderEntityId.getId());

        });
        //排序，测试一定范围id顺序递增
        SortList = AllId.subList(AllId.size() - 1, AllId.size());
        System.out.println(SortList.toString());
        for (int i = 0; i < SortList.size() - 10; i++) {
            Long one = SortList.get(i + 1) - SortList.get(i);
            if (one == 1) {
                logger.info("一定范围顺序递增");
            } else {
                logger.info("增测试失败");
            }

        }
    }


    //测试inter和query字段一致
    @RequestMapping(value = "/queryType", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public @ResponseBody
    String QueryTest(@RequestBody WmOrderEntity wmOrderEntity) {
        //sql inter
        wmOrderServce.insert(wmOrderEntity);
        //sql query 字段
        List<WmOrderEntity> wmOrderiatEntitiesList = new ArrayList<WmOrderEntity>();
        wmOrderiatEntitiesList = wmOrderServce.selectByOrderId(wmOrderEntity);
        Time test = new Time();

        // inter字段和quey字段比较是否一致
        //比较order_id
        if (wmOrderEntity.getOrder_id().equals(wmOrderiatEntitiesList.get(0).getOrder_id())) {
            logger.info("order_id：inter和query一致");
        } else {
            logger.info("不一致：" + "q_order_id:" + wmOrderiatEntitiesList.get(0).getOrder_id() + " " + "I_orderid:" + wmOrderEntity.getOrder_id());
        }
        //比较skuid
        if (wmOrderEntity.getSku_id() == wmOrderiatEntitiesList.get(0).getSku_id()) {
            logger.info("sku_id：inter和query一致");
        } else {
            logger.info("不一致：" + "q_skuid:" + wmOrderiatEntitiesList.get(0).getSku_id() + " " + "i_sku_id:" + wmOrderEntity.getSku_id());
        }
        //比较ware_name
        if (wmOrderEntity.getWare_name().equals(wmOrderiatEntitiesList.get(0).getWare_name())) {
            logger.info("I_warename：inter和query一致");
        } else {
            logger.info("不一致：" + "q_warename:" + wmOrderiatEntitiesList.get(0).getWare_name() + " " + "i_Ware_name:" + wmOrderEntity.getWare_name());
        }
        //比较create,返回时间会丢掉最好微妙，测试时间忽略，下次存储用时间戳
        if (test.datatime().equals(wmOrderiatEntitiesList.get(0).getCreated())) {
            logger.info("I_created：inter和query一致");
        } else {
            logger.info("不一致：" + "q_created:" + wmOrderiatEntitiesList.get(0).getCreated() + " " + "I_created:" + wmOrderEntity.getCreated());
        }
        //比较yn
        if (wmOrderEntity.isYn() == wmOrderiatEntitiesList.get(0).isYn()) {
            logger.info("yn：inter和query一致");
        } else {
            logger.info("不一致：" + "q_yn:" + wmOrderiatEntitiesList.get(0).isYn() + " " + "I_yn:" + wmOrderEntity.isYn());
        }
        //比较wm_sku_id
        if (wmOrderEntity.isWm_sku_id() == wmOrderiatEntitiesList.get(0).isWm_sku_id()) {
            logger.info("wm_sku_id：inter和query一致");
        } else {
            logger.info("不一致：" + "q_yn:" + wmOrderiatEntitiesList.get(0).isWm_sku_id() + "\\n" + "I_yn:" + wmOrderEntity.isWm_sku_id());
        }
        //比较json
        String Dmall_coupon_detail_new = wmOrderiatEntitiesList.get(0).getDmall_coupon_detail_new().replace(" ", "");
        if (wmOrderEntity.getDmall_coupon_detail_new().replace(" ", "").equals(Dmall_coupon_detail_new)) {
            logger.info("Dmall_coupon_detail_new：inter和query一致");
        } else {
            logger.info("不一致：" + "q_yn:" + Dmall_coupon_detail_new + "\n" + "I_yn:" + wmOrderEntity.getDmall_coupon_detail_new().replace(" ", ""));
        }
        //比较matkl
        if (wmOrderEntity.getMatkl().equals(wmOrderiatEntitiesList.get(0).getMatkl())) {
            logger.info("Matkl：inter和query一致");
        } else {
            logger.info("不一致：" + "q_id" + wmOrderEntity.getMatkl() + "i_in" + wmOrderiatEntitiesList.get(0).getMatkl());
        }
        //比较meblob
        if (wmOrderEntity.getMeblob().equals(wmOrderiatEntitiesList.get(0).getMeblob())) {
            logger.info("meblob：inter和query一致");
        } else {
            logger.info("不一致：" + "q_id" + wmOrderEntity.getMeblob() + "i_in" + wmOrderiatEntitiesList.get(0).getMeblob());
        }

        return "成功";
    }

    //测试更新和删除正常
    @RequestMapping(value = "/udsql", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public @ResponseBody
    String UDTest(@RequestBody WmOrderEntity wmOrderEntity) {
        wmOrderServce.insert(wmOrderEntity);
        Long uid = wmOrderEntity.getId();
        System.out.println(uid);
        WmOrderEntity upwmOrderEntity = new WmOrderEntity();
        //更新数据
        upwmOrderEntity.setId(uid);

        upwmOrderEntity.setOrder_id(7810311L);
        upwmOrderEntity.setSku_id(-79);
        wmOrderServce.updatewmorder(upwmOrderEntity);
        //查询更新后的数据
        List<WmOrderEntity> wmOrderiatEntitiesList = new ArrayList<WmOrderEntity>();
        wmOrderiatEntitiesList = wmOrderServce.selectByOrderId(upwmOrderEntity);
        //比较更orderid是否成功
        if (upwmOrderEntity.getOrder_id().equals(wmOrderiatEntitiesList.get(0).getOrder_id())) {
            logger.info("orderid更新成功");
        } else {
            logger.info("更新失败：" + "u_order_id:" + upwmOrderEntity.getOrder_id() + " " + "q_orderid:" + wmOrderiatEntitiesList.get(0).getOrder_id());
        }
        //比较id是否一致
        List<Long> list = new ArrayList<Long>();
        for (int x = 0; x < wmOrderiatEntitiesList.size(); x++) {
            Long id2 = wmOrderiatEntitiesList.get(x).getId();
            list.add(id2);
        }
        if (list.contains(upwmOrderEntity.getId())) {
            logger.info("id保持不变");
        } else {
            logger.info("更新前id:" + upwmOrderEntity.getId() + "" + "更新后的id:" + list.toString());
        }

        wmOrderServce.delete(uid);
        List<WmOrderEntity> dwmOrderiatEntitiesList = new ArrayList<WmOrderEntity>();
        dwmOrderiatEntitiesList = wmOrderServce.selectByOrderId(upwmOrderEntity);
        System.out.println(dwmOrderiatEntitiesList.size());
        System.out.println(dwmOrderiatEntitiesList.toString());
        if (dwmOrderiatEntitiesList.size() == 0) {
            logger.info("删除成功");
        } else {
            logger.info("删除失败");
        }
        return "成功";
    }

    //测试mybatis $ insert
    @RequestMapping(value = "/SqlType$", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public @ResponseBody
    String Save2Test(@RequestBody WmOrderEntity wmOrderEntity) {
        //sql inter
        wmOrderServce.insert$(wmOrderEntity);
        //获取inter分布式ID
        Long id = wmOrderEntity.getId();
        System.out.println("打印返回id:" + id);
        //并发inter获取所有主键id和Order_id
        //map.put(wmOrderEntity.getOrder_id(), wmOrderEntity.getId());
        logger.info("全部主键id和Order_id的集合" + map.toString());

        return "成功";
    }
}