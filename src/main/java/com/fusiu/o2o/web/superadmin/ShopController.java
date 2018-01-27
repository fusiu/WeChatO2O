package com.fusiu.o2o.web.superadmin;

import com.fusiu.o2o.dto.ShopExecution;
import com.fusiu.o2o.entity.Area;
import com.fusiu.o2o.entity.PersonInfo;
import com.fusiu.o2o.entity.Shop;
import com.fusiu.o2o.entity.ShopCategory;
import com.fusiu.o2o.enums.ShopStateEnum;
import com.fusiu.o2o.service.ShopService;
import com.fusiu.o2o.service.impl.ShopServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.Date;

@RestController
@RequestMapping("/shop")
public class ShopController {

    @Autowired
    private ShopService shopService;


    @GetMapping(value = "/shopinsert")
    public String shopinsert(){

        return "Success";
    }
}
