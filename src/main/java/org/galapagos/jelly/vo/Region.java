package org.galapagos.jelly.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Region {
    private String region;
    private int count;

    @Override
    public String toString() {
        return String.format("%s(%d)", region, count);
    }
}
