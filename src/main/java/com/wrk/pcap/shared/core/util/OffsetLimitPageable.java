package com.wrk.pcap.shared.core.util;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Pageable;

public class OffsetLimitPageable implements Pageable {
    private final int offset;
    private final int page;
    private final int size;
    private final Sort sort;

    public OffsetLimitPageable(int offset, int page, int size, Sort sort) {
        if(offset<0){
            throw new IllegalArgumentException("Offset must not be less than zero!");
        }
        if(page< 0){
            throw new IllegalArgumentException("Page index must not be less than zero!");
        }
        if(size <1){
            throw new IllegalArgumentException("Page size must not be less than one!");
        }
        this.offset = offset;
        this.page = page;
        this.size = size;
        this.sort = sort;
    }
    public static OffsetLimitPageable of(int offset,int page,int size,Sort sort){
        return new OffsetLimitPageable(offset,page,size,sort);
    }

    @Override
    public int getPageNumber() {
        return page;
    }

    @Override
    public int getPageSize() {
        return size;
    }

    @Override
    public long getOffset() {
        return offset;
    }

    @Override
    public Sort getSort() {
        return sort;
    }

    @Override
    public Pageable next() {
        return of(offset, page + 1, size, sort);
    }

    @Override
    public Pageable previousOrFirst() {
        return hasPrevious()? of(offset, page-1, size, sort): first();
    }

    @Override
    public Pageable first() {
        return of(offset, 0, size, sort);
    }

    @Override
    public Pageable withPage(int pageNumber) {
        return of(offset, pageNumber, size, sort);
    }

    @Override
    public boolean hasPrevious() {
        return page>0;
    }
}
