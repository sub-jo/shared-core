package com.wrk.pcap.shared.core.component;

import com.wrk.pcap.shared.core.config.ApplicationProperties;
import com.wrk.pcap.shared.core.data.FindAllAttributesObject;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;

class ValidationUtilTest extends TestBase{
    ValidationUtil validationUtil;

    @BeforeAll
    public void setup() {
        validationUtil = new ValidationUtil(new ApplicationProperties());
    }

    @Test
    void givenNullValuesForAllAttributesObject_thenGetFindAllAttributesObject(){
        Integer offset=null;
        Integer limit=null;
        String sort=null;
        FindAllAttributesObject findAllAttributesObject = validationUtil.checkFindAllAttributes(offset,limit,sort);
        Assertions.assertEquals(0,findAllAttributesObject.getOffset());
        Assertions.assertEquals(100,findAllAttributesObject.getLimit());
        Assertions.assertNull(null,findAllAttributesObject.getSort());
    }

    @Test
    void givenValidValuesForAllAttributesObject_thenGetFindAllAttributesObject(){
        Integer offset=0;
        Integer limit=50;
        String sort="default";
        FindAllAttributesObject findAllAttributesObject = validationUtil.checkFindAllAttributes(offset,limit,sort);
        Assertions.assertEquals(0,findAllAttributesObject.getOffset());
        Assertions.assertEquals(50,findAllAttributesObject.getLimit());
        Assertions.assertEquals("name",findAllAttributesObject.getSort());
    }

    @Test
    void givenNegativeValuesForOffsetAndLimitAttributesObject_thenGetFindAllAttributesObject(){
        Integer offset=-1;
        Integer limit=-1;
        String sort=null;
        FindAllAttributesObject findAllAttributesObject = validationUtil.checkFindAllAttributes(offset,limit,sort);
        Assertions.assertEquals(1,findAllAttributesObject.getOffset());
        Assertions.assertEquals(1,findAllAttributesObject.getLimit());
        Assertions.assertNull(null,findAllAttributesObject.getSort());
    }
    @Override
    public void tearDown() {

    }
}