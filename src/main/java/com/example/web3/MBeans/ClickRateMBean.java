package com.example.web3.MBeans;

public interface ClickRateMBean {
    
    default void click(boolean hit) {};

    int getResult();

    int getHit();
}
