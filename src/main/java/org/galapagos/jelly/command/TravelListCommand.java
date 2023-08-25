package org.galapagos.jelly.command;

import org.galapagos.jelly.TravelApp;
import org.galapagos.jelly.dao.TravelDao;
import org.galapagos.jelly.dao.TravelDaoImpl;

import static jdk.internal.org.jline.reader.impl.LineReaderImpl.CompletionType.List;

public class TravelListCommand implements Command{
    final static double COUNT_PER_PAGE= 10.;
    TravelDao dao = TravelDaoImpl.getInstance();

    @Override
    public void execute(){
        int totalCount = dao.getTotalCount();
        int totalPage = (int) Math.ceil(totalCount / COUNT_PER_PAGE);//올림 메소드
        String title = String.format("페이지 선택[1~%d]", totalPage);
        int page = Input.readInt(title);
        int start = (page - 1)*COUNT_PER_PAGE + 1;
        int end = (page - 1) * COUNT_PER_PAGE + 1;


    }
}
