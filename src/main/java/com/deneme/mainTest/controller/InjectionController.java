package com.deneme.mainTest.controller;

import com.deneme.mainTest.model.Data;
import com.deneme.mainTest.service.BaglantiServis;
import com.deneme.mainTest.service.BaglantiServisImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class InjectionController {

    @Autowired
    private BaglantiServis baglantiServisImpl;


    @GetMapping(path = "/normal/{name}")
    public List<Data> sqlController(@PathVariable String name) {
        return baglantiServisImpl.calisanlariGetir(name);
    }

    @GetMapping(path = "/prepared/{name}")
    public List<Data> preparedController(@PathVariable int name) {
        return baglantiServisImpl.preparedCalisanlariGetir(name);
    }
}
