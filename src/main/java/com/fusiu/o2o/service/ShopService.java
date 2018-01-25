package com.fusiu.o2o.service;

import com.fusiu.o2o.dto.ShopExecution;
import com.fusiu.o2o.entity.Shop;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.File;

public interface ShopService {
    ShopExecution addShop(Shop shop, File shopImg);
}

