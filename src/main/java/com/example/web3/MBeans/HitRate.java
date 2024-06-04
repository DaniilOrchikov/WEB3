package com.example.web3.MBeans;

import java.io.Serializable;

public class HitRate implements Serializable, HitRateMBean {

    private double totalClicks = 0.0;
    
    private double hitClicks = 0;

    @Override
    public void click(boolean hit) {
        totalClicks++;
        if(hit) {
            hitClicks++;
        }
    } 

    @Override
    public double getPercentage() {
        if(totalClicks == 0) {
            return 0;
        }
        return hitClicks / totalClicks * 100;
    }

    @Override
    public double getResult() {
        return totalClicks;
    }

    @Override
    public double getHit() {
        return hitClicks;
    }
}
