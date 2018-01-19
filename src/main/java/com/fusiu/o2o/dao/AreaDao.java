package com.fusiu.o2o.dao;

import com.fusiu.o2o.entity.Area;

import java.util.List;

public interface AreaDao {
    /**
     * 查询 Area 列表
     * @return areaList
     */
    List<Area> queryArea();
}
