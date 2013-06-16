package com.example.withpartner.model;

import java.util.ArrayList;

import com.example.withpartner.data.Hokkori;

public interface DetailData {
    
    String getTitle();
    int getImageResourceId();
    ArrayList<Hokkori> getHokkoriList();
    Hokkori addHokkoriList(Hokkori hokkori);
    boolean removeHokkoriList(Hokkori hokkori);
    
}
