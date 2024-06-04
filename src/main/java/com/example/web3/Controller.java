package com.example.web3;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.management.MBeanServer;
import javax.management.ObjectName;

import com.example.web3.MBeans.ClickRate;
import com.example.web3.MBeans.ClickRateMBean;
import com.example.web3.MBeans.HitRate;
import com.example.web3.MBeans.HitRateMBean;

import java.io.IOException;
import java.io.Serializable;
import java.lang.management.ManagementFactory;

@ApplicationScoped
@Named
public class Controller implements Serializable {
    private double x;
    private double y;
    private double r = 1;
    @Inject
    private CollectionBean collectionBean;
    private final HitRateMBean hitRate;
    private final ClickRateMBean clickRate;
    private final MBeanServer mBeanServer;

    public Controller() {
        hitRate = new HitRate();
        clickRate = new ClickRate();
        mBeanServer = ManagementFactory.getPlatformMBeanServer();
        try {
            mBeanServer.registerMBean(clickRate, new ObjectName("KanekiKen:name=click"));
            mBeanServer.registerMBean(hitRate, new ObjectName("Shizlo:name=hit"));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Ты что ахуел?");
        }
    }

    public void submit() {
        Point point = new Point();
        point.setX(x);
        point.setY(y);
        point.setR(r);
        point.setHit(hit(x, y, r));
        collectionBean.addPoint(point);

        clickRate.click(hit(x, y, r));
        hitRate.click(hit(x, y, r));

        System.out.println("У меня есть " + clickRate.getResult() + " демонов в голове, из них " + clickRate.getHit() + " ещё шизанутые");
        System.out.println(hitRate.getPercentage());
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Point added", null);
        FacesContext.getCurrentInstance().addMessage(null, message);

        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        try {
            ec.redirect(ec.getRequestContextPath() + "/main.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public double getR() {
        return r;
    }

    public void setR(double number) {
        this.r = number;
    }

    public double getX() {
        return x;
    }

    public void setX(double number) {
        this.x = number;
    }

    public double getY() {
        return y;
    }

    public void setY(double number) {
        this.y = number;
    }

    private static boolean hit(double x, double y, double r) {
        double angle = Math.atan2(y, x);
        if (angle > 0 && angle <= Math.PI / 2) return x <= r && y <= r;
        if (angle > Math.PI / 2 && angle <= Math.PI) return x > (y - r) / 2;
        if (angle < Math.PI / -2 && angle >= -Math.PI) return Math.pow(x, 2.0) + Math.pow(y, 2.0) <= Math.pow(r / 2, 2.0);
        return false;
    }
}
