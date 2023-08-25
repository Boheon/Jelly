package org.galapagos.jelly.vo;

//import static org.junit.jupiter.api.Assertions.*;

class TravelVOTest {
    public static void main(String[] args) {
        TravelVO travelVO = TravelVO.builder()
                .phone("010-2805-23423")
                .title("멀티캠")
                .address("부산")
                .region("광역시")
                .build();
        System.out.println(travelVO);
    }

}