package com.fusiu.o2o.service;

import com.fusiu.o2o.BaseTest;
import com.fusiu.o2o.entity.Area;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class AreaServiceTest extends BaseTest {

    @Autowired
    private AreaService areaService;

    @Test
    public void AreaServiceTest() {
        List<Area> areaList = areaService.getAreaList();
        assertEquals("E栋", areaList.get(1).getAreaName());
    }
}
