package com.example.web3;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@ApplicationScoped
public class CollectionBean implements Serializable {

    private final List<Point> points = new ArrayList<>();

    private final PointDAO pointDAO = new PointDAO();

    public CollectionBean(){
        points.addAll(pointDAO.getAllPoints());
    }

    public List<Point> getPoints() {
        return points;
    }

    public void clearCollection(){
        pointDAO.deleteAllPoints();
        points.clear();
    }

    public void addPoint(Point point){
        pointDAO.addPoint(point);
        points.add(point);
    }
}
