package org.galapagos.jelly.utils;

import org.galapagos.jelly.vo.TravelVO;

import java.util.List;

public class TravelUtil {
    public static void printTravelList(List<TravelVO> list) {
        System.out.println("#   [권역명] 관광지명");
        System.out.println("==================================");
        for(TravelVO travel : list){
            System.out.printf("%4d [%s] %s\n", travel.getNo(), travel.getRegion(), travel.getTitle());
        }
        System.out.println("==================================");
    }
}
