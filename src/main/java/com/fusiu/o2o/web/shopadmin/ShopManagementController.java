package com.fusiu.o2o.web.shopadmin;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
@RequestMapping("/shopadmin")
public class ShopManagementController {

    @RequestMapping(value = "/registershop",method = RequestMethod.POST)
    @ResponseBody
    private Map<String,Object> registerShop(HttpServletRequest request){
        //1.接收并转化相应的参数，包括店铺信息和图片信息
        //2.注册店铺
        //3.返回结果
        return null;
    }
}
