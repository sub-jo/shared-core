package com.wrk.pcap.shared.core.data;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FindAllAttributesObject {
    private Integer offset;
    private Integer limit;
    private Integer page;
    private String sort;
}
