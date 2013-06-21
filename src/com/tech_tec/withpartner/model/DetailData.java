package com.tech_tec.withpartner.model;

import java.util.ArrayList;

import com.tech_tec.withpartner.data.Hokkori;

public interface DetailData {
    
    String getTitle();
    int getImageResourceId();
    ArrayList<Hokkori> getHokkoriList();
    Hokkori addHokkoriList(Hokkori hokkori);
    boolean removeHokkoriList(Hokkori hokkori);
    
}
