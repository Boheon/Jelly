package org.galapagos.jelly.command;


import org.galapagos.jelly.dao.TravelDao;
import org.galapagos.jelly.dao.TravelOracleDaoImpl;
import org.galapagos.jelly.utils.TravelUtil;
import org.galapagos.jelly.vo.PageRequest;
import org.galapagos.jelly.vo.TravelVO;
import org.galapogos.common.cli.Input;
import org.galapogos.common.cli.common.Command;

import java.util.List;


public class TravelListCommand implements Command {
    final static int COUNT_PER_PAGE= 10;
    TravelDao dao = TravelOracleDaoImpl.getInstance();

    @Override
    public void execute(){
        while (true) {
            // 페이지 선택[1~12]: 3
            int totalCount = dao.getTotalCount();
            int totalPage = (int) Math.ceil(totalCount / (double) COUNT_PER_PAGE);
            String title = String.format("페이지 선택[1~%d]: ", totalPage);
            int page = Input.readInt(title);
            if (page == 0) {
                return;
            }

            // page1: 1~10
            // page2: 11~20
            //oracle
            //int start = (page - 1) * COUNT_PER_PAGE + 1;
            //int end = start + COUNT_PER_PAGE - 1;

            //mysql
            int start = (page-1) * COUNT_PER_PAGE;

            PageRequest pageRequest = new PageRequest(start, COUNT_PER_PAGE);
            List<TravelVO> list = dao.getPage(pageRequest);

            TravelUtil.printTravelList(list);

            System.out.printf("총 %d건 (페이지 : %d/%d)\n", totalCount, page, totalPage);
        }

    }
}
