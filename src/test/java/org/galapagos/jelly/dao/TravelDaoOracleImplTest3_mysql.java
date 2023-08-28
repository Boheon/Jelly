package org.galapagos.jelly.dao;

import junit.framework.TestCase;
import org.galapagos.jelly.vo.PageRequest;
import org.galapagos.jelly.vo.Region;
import org.galapagos.jelly.vo.TravelVO;

import java.util.List;

public class TravelDaoOracleImplTest3_mysql extends TestCase {

    TravelDao travelDao = TravelDaoOracleImpl.getInstance();
    public void testGetInstance() {
    }

    public void testGetTotalCount() {
        int totalCount = travelDao.getTotalCount();

        assertTrue(totalCount == 112);
    }

    public void testGetPage() {
        PageRequest pageRequest = new PageRequest(10,10);

        List<TravelVO> list = travelDao
    }

    public void testGetRegions() {
        List<Region> list = travelDao.getRegions();
        assertEquals(6, list.size());
    }

    public void testGetSpots() {
        List<TravelVO> list = travelDao.search("%해수욕장%");
        assertEquals(6, list.size());
    }

    public void testSearch() {
    }

    public void testFindById() {
    }
}