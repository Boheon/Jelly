package org.galapagos.jelly.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageRequest {
    private int start;
    private int perCount;//한 페이지의 사이즈

    public int getEnd(){
        return start+perCount-1;
    }


}
