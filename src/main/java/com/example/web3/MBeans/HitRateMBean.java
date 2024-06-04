package com.example.web3.MBeans;

public interface HitRateMBean {
    
    default void click(boolean hit) {}

    double getPercentage();
    
    double getResult();

    double getHit();
}
