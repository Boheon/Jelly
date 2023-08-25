package org.galapagos.jelly.dao;

import org.galapagos.jelly.common.JDBCUtil;
import org.galapagos.jelly.vo.Region;
import org.galapagos.jelly.vo.TravelVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TravelDaoImpl implements TravelDao {

    Connection conn = JDBCUtil.getConnection();

    private TravelDaoImpl() {
    }

    private static TravelDaoImpl dao = new TravelDaoImpl();

    public static TravelDao getInstance() {
        return dao;
    }

    @Override
    public int getTotalCount() {
        String sql = "SELECT COUNT(*) TOTAL_COUNT FROM TRAVEL";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            try (ResultSet rs = stmt.executeQuery()) {
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public List<TravelVO> getPage(int start, int end) {
        String sql = "SELECT * FROM TRAVEL_VIEW WHERE SEQ BETWEEN ? AND ?";
        List<TravelVO> list = new ArrayList<>();

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            //부분 설정
            stmt.setInt(1, start);
            stmt.setInt(2, end);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    TravelVO travel = TravelVO.builder()
                            .no(rs.getInt("no"))
                            .region(rs.getString("region"))
                            .title(rs.getString("title"))
                            .description(rs.getString("description"))
                            .address(rs.getString("address"))
                            .phone(rs.getString("phone"))
                            .build();
                    list.add(travel);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public List<Region> getRegions() {
        String sql = "SELECT REGION, COUNT(*) COUNT FROM TRAVEL GROUP BY REGION ORDER BY COUNT(*)";

        List<Region> list = new ArrayList<>();
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Region region = new Region();
                    region.setRegion(rs.getString("region"));
                    region.setCount(rs.getInt("count"));
                    list.add(region);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<TravelVO> getSpots(String region) {
        String sql = "SELECT * FROM TRAVEL WHERE REGION = ? ORDER BY TITLE";
        List<TravelVO> list = new ArrayList<>();

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            //부분 설정
            stmt.setString(1, region);
            mapList(list, stmt);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<TravelVO> search(String keyword) {
        String sql = "SELECT * FROM TRAVEL WHERE TITLE LIKE ? or DESCRIPTION LIKE ? ORDER BY REGION, TITLE";
        List<TravelVO> list = new ArrayList<>();

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            //부분 설정
            stmt.setString(1, keyword);
            mapList(list, stmt);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public TravelVO findById(int no) {
        String sql = "SELECT * FROM TRAVEL WHERE NO = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
           stmt.setInt(1, no);
           try(ResultSet rs = stmt.executeQuery()){
               if(rs.next()) return map(rs);
           }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private TravelVO map(ResultSet rs) throws SQLException {
        TravelVO travel = TravelVO.builder()
                .no(rs.getInt("no"))
                .region(rs.getString("region"))
                .title(rs.getString("title"))
                .description(rs.getString("description"))
                .address(rs.getString("address"))
                .phone(rs.getString("phone"))
                .build();

        return travel;
    }

    private void mapList(List<TravelVO> list, PreparedStatement stmt) throws SQLException {
        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                TravelVO travel = map(rs);
                list.add(travel);
            }
        }
    }
}
