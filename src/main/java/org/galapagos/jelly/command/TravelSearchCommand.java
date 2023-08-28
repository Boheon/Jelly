package org.galapagos.jelly.command;

import org.galapagos.jelly.dao.TravelDao;
import org.galapagos.jelly.dao.TravelMySQLDaoImpl;
import org.galapagos.jelly.utils.TravelUtil;
import org.galapagos.jelly.vo.TravelVO;
import org.galapogos.common.cli.Input;
import org.galapogos.common.cli.common.Command;

import java.util.List;

public class TravelSearchCommand implements Command {

    TravelDao dao = TravelMySQLDaoImpl.getInstance();
    @Override
    public void execute() {
        //검색어:
        //검색 결과 출력
        String keyword = Input.read("검색어: ");

        //DAO로 검색
        List<TravelVO> list = dao.search("%" + keyword +"%");

        //검색 결과 출력
        TravelUtil.printTravelList(list);
        System.out.printf("총 %d건\n", list.size());
    }
}
