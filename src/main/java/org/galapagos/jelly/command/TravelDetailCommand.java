package org.galapagos.jelly.command;

import org.galapagos.jelly.dao.TravelDao;
import org.galapagos.jelly.dao.TravelDaoOracleImpl;
import org.galapagos.jelly.vo.TravelVO;
import org.galapogos.common.cli.Input;
import org.galapogos.common.cli.common.Command;

public class TravelDetailCommand implements Command {

    TravelDao dao = TravelDaoOracleImpl.getInstance();

    @Override
    public void execute() {
        int no = Input.readInt("No : ");

        TravelVO travel = dao.findById(no);
        if(travel == null){
            System.out.println("잘못된 번호입니다.");
            return;
        }
        // 권역: ????
        // 관광지명: ????
        // 주소: ????
        // 전화번호: ????
        // ???????????...(description)
        System.out.println("====================================");
        System.out.println("권역: " + travel.getRegion());
        System.out.println("관광지명: " + travel.getTitle());
        System.out.println("주소: " + travel.getAddress());
        System.out.println("전화번호: " + travel.getPhone());
        System.out.println(travel.getDescription().replace(". ", ".\n"));
        System.out.println("====================================");
    }
}
