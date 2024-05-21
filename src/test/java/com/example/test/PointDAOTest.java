package com.example.test;

import static com.example.web3.HibernateUtil.createSession;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.example.web3.Point;
import com.example.web3.PointDAO;
import org.hibernate.Session;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class PointDAOTest {
    static PointDAO pointDAO;

    @BeforeClass
    public static void beforeClass() {
        pointDAO = new PointDAO();
    }

    @AfterClass
    public static void afterClass() {
        pointDAO.deleteAllPoints();
    }

    @Before
    public void setUp() {
        pointDAO = new PointDAO();
    }

    @Test
    public void shouldGetSession() {
        try (Session session = createSession()) {
            assertTrue(session.isConnected());
            assertTrue(session.isOpen());
        }
    }

    @Test
    public void shouldAddPoint() {
        int size = pointDAO.getAllPoints().size();
        Point point = new Point(1, 2, 3, true);
        pointDAO.addPoint(point);
        assertEquals(size + 1, pointDAO.getAllPoints().size());
    }

    @Test
    public void shouldDeleteAllPoints() {
        pointDAO.deleteAllPoints();
        assertEquals(0, pointDAO.getAllPoints().size());
    }
}
