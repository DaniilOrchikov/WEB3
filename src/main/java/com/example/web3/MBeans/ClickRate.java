package com.example.web3.MBeans;

import javax.management.Notification;
import javax.management.NotificationBroadcasterSupport;
import java.io.Serializable;
import java.sql.SQLOutput;

public class ClickRate extends NotificationBroadcasterSupport implements Serializable, ClickRateMBean {
    
    int totalClicks = 0;

    int hitClicks = 0;

    int notificationCounter = 0;

    @Override
    public void click(boolean hit) {
        totalClicks++;
        if(hit) {
            hitClicks++;
        }
        if(totalClicks % 10 == 0) {
            System.out.println("10!");
            Notification notification = new Notification("10-кратные выстрелы", this, notificationCounter++, System.currentTimeMillis(), "Число выстрелов теперь кратно 10.");
            sendNotification(notification);
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
