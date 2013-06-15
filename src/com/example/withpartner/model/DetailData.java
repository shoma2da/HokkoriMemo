package com.example.withpartner.model;

import java.util.ArrayList;

public interface DetailData {
    
    String getTitle();
    int getImageResourceId();
    ArrayList<String> getHokkoriList();
    boolean addHokkoriList(String hokkori);
    boolean removeHokkoriList(String hokkori);
    
}
