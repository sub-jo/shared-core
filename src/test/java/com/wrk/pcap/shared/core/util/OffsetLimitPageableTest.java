package com.wrk.pcap.shared.core.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import static org.junit.jupiter.api.Assertions.*;

class OffsetLimitPageableTest {

    @Test
    void givenOffsetMinus_whenConstructor_thenThrows() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new OffsetLimitPageable(-1,0,0,null));
    }
    @Test
    void givenNegativeSize_whenConstructor_thenThrows() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new OffsetLimitPageable(0,0,-1,null));
    }
    @Test
    void givenCorrectValues_whenOf_thenFieldsCorrect(){
        Sort sort = Mockito.mock(Sort.class);
        OffsetLimitPageable offsetLimitPageable = new OffsetLimitPageable(0,1,1,sort);
        Assertions.assertEquals(0,offsetLimitPageable.getOffset());
        Assertions.assertEquals(1,offsetLimitPageable.getPageNumber());
        Assertions.assertEquals(1,offsetLimitPageable.getPageSize());
        Assertions.assertEquals(sort,offsetLimitPageable.getSort());
    }
    @Test
    void givenCorrectValues_whenNext_thenFieldsCorrectAndSort(){
        Sort sort = Mockito.mock(Sort.class);
        OffsetLimitPageable offsetLimitPageable = new OffsetLimitPageable(0,0,1,sort);
        Pageable pageable = offsetLimitPageable.next();
        Assertions.assertEquals(0,pageable.getOffset());
        Assertions.assertEquals(1,pageable.getPageNumber());
        Assertions.assertEquals(1,pageable.getPageSize());
        Assertions.assertEquals(sort,pageable.getSort());
    }

}