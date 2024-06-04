package com.example.web3;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;

@ApplicationScoped
@Named
public class Controller implements Serializable {
    private double x;
    private double y;
    private double r = 1;
    @Inject
    private CollectionBean collectionBean;

    public void submit() {
        Point point = new Point();
        point.setX(x);
        point.setY(y);
        point.setR(r);
        point.setHit(hit(x, y, r));
        collectionBean.addPoint(point);

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
