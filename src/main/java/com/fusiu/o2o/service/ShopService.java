package com.fusiu.o2o.service;

import com.fusiu.o2o.dto.ShopExecution;
import com.fusiu.o2o.entity.Shop;

import java.io.File;

public interface ShopService {
    ShopExecution addShop(Shop shop, File shopImg);
}

