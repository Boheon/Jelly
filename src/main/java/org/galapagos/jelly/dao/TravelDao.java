package org.galapagos.jelly.dao;

import org.galapagos.jelly.vo.Region;
import org.galapagos.jelly.vo.TravelVO;

import java.util.List;

public interface TravelDao {
    //전체 데이터 건수
    int getTotalCount();

    //페이지 목록
    List<TravelVO> getPage(int start, int end);

    //지역 목록얻기
    List<Region> getRegions();

    //선택 권역의 관광지 목록 얻기
    List<TravelVO> getSpots(String region);

    //검색
    List<TravelVO> search(String keyword);

    //상세보기
    TravelVO findById(int no);




}
