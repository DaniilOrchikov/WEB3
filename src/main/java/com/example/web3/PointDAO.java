package com.example.web3;

import org.hibernate.Session;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;

public class PointDAO {
    public Collection getAllPoints() {
        Session session = null;
        List points = new ArrayList<>();
        try {
            session = HibernateUtil.createSession();
            points = session.createCriteria(Point.class).list();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка getAll ", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return points;
    }

    public void runFunc(Consumer<Session> func){
        Session session = null;
        try {
            session = HibernateUtil.createSession();
            session.beginTransaction();
            func.accept(session);
            session.getTransaction().commit();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка ", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public void deleteAllPoints(){
        runFunc((session) -> session.createSQLQuery("truncate table points").executeUpdate());
    }

    public void addPoint(Point point){
        runFunc((session) -> session.save(point));
    }
}
