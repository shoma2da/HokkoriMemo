package com.tech_tec.withpartner.data;

public class Hokkori {
    private String str;
    private long time;
    private int id;
    
    public Hokkori(int id, String str, long time) {
        this.id = id;
        this.str = str;
        this.time = time;
    }
    
    public Hokkori(String str, long time) {
        this(-1, str, time);
    }
    
    public String getText() {
        return this.str;
    }
    
    public long getTime() {
        return this.time;
    }
    
    public int getId() {
        return this.id;
    }
}
