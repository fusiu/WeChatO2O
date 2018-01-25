package com.fusiu.o2o.service;

import com.fusiu.o2o.entity.Area;
import org.springframework.stereotype.Component;

import java.util.List;

public interface AreaService {
    /**
     * 获取区域列表
     * @return Area List
     */
    List<Area> getAreaList();
}
