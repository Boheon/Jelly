package org.galapagos.jelly.dao;

import org.galapagos.jelly.vo.Region;
import org.galapagos.jelly.vo.TravelVO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

class TravelDaoImplTest {
    TravelDao dao = TravelDaoImpl.getInstance();

    @org.junit.jupiter.api.Test
    void getInstance() {
        TravelDao dao2 = TravelDaoImpl.getInstance();
       assertSame(dao, dao2);
    }

    @org.junit.jupiter.api.Test
    void getTotalCount() {
        int totalCount = dao.getTotalCount();

        assertTrue(totalCount == 120);
    }

    @org.junit.jupiter.api.Test
    void getPage() {
        List<TravelVO> list = dao.getPage(11, 20);
        assertTrue(list.size() == 10);

        list = dao.getPage(111,120);
        assertTrue(list.size() == 2);
    }

    @org.junit.jupiter.api.Test
    void getRegions() {
        List<Region> list = dao.getRegions();
        assertEquals(6, list.size());
    }

    @org.junit.jupiter.api.Test
    void getSpots() {
        List<TravelVO> list = dao.getSpots("수도권");
        assertEquals(13, list.size());
    }

    @org.junit.jupiter.api.Test
    void search() {
        List<TravelVO> list = dao.search("%해수욕장%");
        assertEquals(8, list.size());
    }

    @org.junit.jupiter.api.Test
    void findById() {
        TravelVO travel = dao.findById(10);
        assertNotNull(travel);

        travel = dao.findById(210);
        assertNull(travel);
    }
}