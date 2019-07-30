package com.deneme.mainTest.service;

import com.deneme.mainTest.model.Data;

import java.util.List;

public interface BaglantiServis  {
     List<Data> calisanlariGetir(String parametre);
     List<Data> preparedCalisanlariGetir(int id) ;
}
