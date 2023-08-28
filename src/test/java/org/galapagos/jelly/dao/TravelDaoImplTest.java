package org.galapagos.jelly.dao;

import org.galapagos.jelly.vo.PageRequest;
import org.galapagos.jelly.vo.Region;
import org.galapagos.jelly.vo.TravelVO;
import org.junit.Test;


import java.util.List;

import static org.junit.Assert.*;


class TravelDaoImplTest {
    TravelDao dao = TravelOracleDaoImpl.getInstance();

    @Test
    void getInstance() {
        TravelDao dao2 = TravelOracleDaoImpl.getInstance();
       assertSame(dao, dao2);
    }

    @Test
    void getTotalCount() {
        int totalCount = dao.getTotalCount();

        assertTrue(totalCount == 112);
    }

    @Test
    void getPage() {
        PageRequest pageRequest = new PageRequest(10, 10);

        List<TravelVO> list = dao.getPage(pageRequest);
        assertTrue(list.size() == 10);

        pageRequest.setStart(110);
        list = dao.getPage(pageRequest);
        assertTrue(list.size() == 2);
        assertEquals(2, list.size());
    }

    @Test
    void getRegions() {
        List<Region> list = dao.getRegions();
        assertEquals(6, list.size());
    }

    @Test
    void getSpots() {
        List<TravelVO> list = dao.getSpots("수도권");
        assertEquals(13, list.size());
    }

    @Test
    void search() {
        List<TravelVO> list = dao.search("%해수욕장%");
        assertEquals(9, list.size());
    }

    @Test
    void findById() {
        TravelVO travel = dao.findById(10);
        assertNotNull(travel);

        travel = dao.findById(210);
        assertNull(travel);
    }
}