package com.example.web3;

import static com.example.web3.HibernateUtil.createSession;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.hibernate.Session;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;

public class PointDAOTest {
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15-alpine");
    PointDAO pointDAO;

    @BeforeAll
    static void beforeAll() {
        postgres.start();
    }

    @AfterAll
    static void afterAll() {
        postgres.stop();
    }

    @BeforeEach
    void setUp() {
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
    void shouldAddPoint() {
        int size = pointDAO.getAllPoints().size();
        Point point = new Point(1, 2, 3, true);
        pointDAO.addPoint(point);
        assertEquals(size + 1, pointDAO.getAllPoints().size());
    }

    @Test
    void shouldDeleteAllPoints() {
        pointDAO.deleteAllPoints();
        assertEquals(0, pointDAO.getAllPoints().size());
    }
}
