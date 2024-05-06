package com.example.web3;

import javax.inject.Named;
import javax.faces.view.ViewScoped;
import java.io.Serializable;

@Named
@ViewScoped
public class PointBean implements Serializable {
    private double R;
    private int X;
    private double Y;

    private double result = 1;

    public double getR() {
        return R;
    }

    public void setR(double number) {
        this.R = number;
    }

    public int getX() {
        return X;
    }

    public void setX(int number) {
        this.X = number;
    }

    public double getY() {
        return Y;
    }

    public void setY(double number) {
        this.Y = number;
    }

    public String submit() {
        result = X + Y + R;
        return null;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double number) {
        result = number;
    }
}
