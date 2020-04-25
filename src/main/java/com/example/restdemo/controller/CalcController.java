package com.example.restdemo.controller;

import java.util.HashMap;
import java.util.Map;

import com.example.restdemo.dto.CalcDto;
import com.example.restdemo.service.CalcService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/calc")
public class CalcController {

    @Autowired
    private CalcService calcService;
    
    @RequestMapping(method = RequestMethod.GET, value = "/hello")
    public Object getHello() {
        Map<String, String> map = new HashMap<>();
        map.put("key", "こんにちは");
        return map;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/add")
    public Object add(@RequestBody CalcDto calcDto) {
        // int add = calcDto.getNum1() + calcDto.getNum2();
        int add = calcService.add(calcDto.getNum1(), calcDto.getNum2());
        Map<String, Integer> map = new HashMap<>();
        map.put("add", add);
        return map;
    }

    @RequestMapping(method = RequestMethod.POST, value="/sub")
    public Object sub(@RequestBody CalcDto calcDto) {
        int sub = calcService.sub(calcDto.getNum1(), calcDto.getNum2());
        Map<String, Integer> map = new HashMap<>();
        map.put("sub", sub);
        return map;
    }
}