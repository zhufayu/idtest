package com.dmall.Controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dmall.Enum.Color;

import com.dmall.Servce.impl.WmOrderService;
import com.dmall.bean.JsonDemo;
import com.dmall.bean.WmOrderEntity;
import com.dmall.util.Time;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Controller
//@RequestMapping("/index")
public class WmOrderController {
    @Autowired
    private WmOrderService wmOrderService;


    Logger logger= LoggerFactory.getLogger(WmOrderController.class);

    Map<Long,Long> map=new ConcurrentHashMap<Long, Long>();

    @RequestMapping(value = "/hello")
    public @ResponseBody  String HelloWorld() {
        return "hello world in controller" ;
    }

    //批量插入1个库
    @RequestMapping(value = "/insertWmOrderList", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public @ResponseBody
    String insertWmOrderList(@RequestBody List<WmOrderEntity> orderlist) {
        int counter = wmOrderService.insertList(orderlist);
        return "{count: " + counter + "}";
    }


    @RequestMapping(value = "/SqlType", method = RequestMethod.POST ,produces = "application/json;charset=UTF-8")
    public  @ResponseBody String SaveTest(@RequestBody WmOrderEntity wmOrderEntity) {

        Time time = new Time();
        //sql datatime转换
        wmOrderEntity.setCreated(time.datatime());
        wmOrderEntity.setModified(time.datatime());
        //sql data  转换
        wmOrderEntity.setDatatest(time.datatest());
        //sql time转换
        wmOrderEntity.setTimetest(time.timetest());
        //sql year转换
        wmOrderEntity.setYeartest(time.yeartest());
        //sql Timestamp转换
        wmOrderEntity.setTimestamptest(time.timestamptest());
        //sql BINARY转换
        wmOrderEntity.setBianyrtest(time.bytetest(wmOrderEntity.getBianyrtest2()));
        //sql VARBINARY转换
        wmOrderEntity.setBianyrtest(time.bytetest(wmOrderEntity.getOffline_bar_code2()));
        //sql GEOMETRY 转换
        //sql enum转换
        wmOrderEntity.setPromotion_sub_type(Color.RED);


        //json转化，emoji
        JsonDemo jsonDemo = new JsonDemo();
        jsonDemo.setProperties(wmOrderEntity.getJsonDemo().getProperties());
        jsonDemo.setEmoji(wmOrderEntity.getJsonDemo().getEmoji());
        jsonDemo.setJsonDemo2(wmOrderEntity.getJsonDemo().getJsonDemo2());
        String jsonstring = JSON.toJSONString(jsonDemo);
        logger.info(jsonstring);
        wmOrderEntity.setDmall_coupon_detail_new(jsonstring);

        //double转BigDecimal类型
        wmOrderEntity.setOffline_num(time.getOffline_num(wmOrderEntity.getOffline_num1()));
        //String转byte

        wmOrderEntity.setLongblobtest(time.bytetest(wmOrderEntity.getLongblob1()));
        //sql inter
        wmOrderService.insert(wmOrderEntity);

        //获取inter分布式ID
        Long id = wmOrderEntity.getId();
        //并发inter获取所有主键id和Order_id
       map.put(wmOrderEntity.getOrder_id(), wmOrderEntity.getId());
        logger.info("全部主键id和Order_id的集合" + map.toString());

        //inter语句Order_id字段查询主键id
        List<WmOrderEntity> wmOrderiatEntitiesList = new ArrayList<WmOrderEntity>();
        wmOrderiatEntitiesList = wmOrderService.selectId(wmOrderEntity.getOrder_id());

        //inter主键和sql，返回的主键对比
        for (WmOrderEntity wmOrderEntity1 : wmOrderiatEntitiesList) {
            Long id2 = wmOrderEntity1.getId();
            String Properties = wmOrderEntity1.getDmall_coupon_detail_new();
            // 打印Emoji表情是否成功inter
            JsonDemo p1 = JSONObject.parseObject(Properties, JsonDemo.class);
            //logger.info("打印Emoji表情" + p1.getEmoji() + "  返回ID" + wmOrderEntity.getOrder_id());

            if (id.equals(id2)) {
                logger.info("测试成功,interId和QueryId完全一致");
            } else {
                logger.info("测试失败" + "Order_id" + wmOrderEntity.getOrder_id() + " " + "tabid:" + id2 + " " + "interid:" + id);
            }

        }
        return "成功";
    }



    }