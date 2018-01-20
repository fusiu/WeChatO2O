package com.fusiu.o2o.web.superadmin;

import com.fusiu.o2o.entity.Area;
import com.fusiu.o2o.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/superadmin")
public class AreaController {

    @Autowired
    private AreaService areaService;

    @GetMapping(value = "/listarea")
    public Map<String,Object> listArea(){
        Map<String,Object> modelMap = new HashMap<>();
        List<Area> areaList = areaService.getAreaList();

        modelMap.put("rows",areaList);
        modelMap.put("total",areaList.size());
        return modelMap;
    }
}
