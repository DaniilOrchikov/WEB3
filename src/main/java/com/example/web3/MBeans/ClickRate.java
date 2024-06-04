package com.example.web3.MBeans;

import javax.management.Notification;
import javax.management.NotificationBroadcasterSupport;
import java.io.Serializable;

public class ClickRate extends NotificationBroadcasterSupport implements Serializable, ClickRateMBean {
    
    int totalClicks = 0;

    int hitClicks = 0;

    int notificationCounter = 0;

    @Override
    public void click(boolean hit) {
        totalClicks++;
        if(totalClicks % 10 == 0) {
            sendNotification(new Notification("Число выстрелов теперь кратно 10.", this, notificationCounter++));
        }
        if(hit) {
            hitClicks++;
        }
    }

    @Override
    public int getResult() {
        return totalClicks;
    }

    @Override
    public int getHit() {
        return hitClicks;
    }
}
