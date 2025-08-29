package com.wrk.pcap.shared.core.component;

import com.wrk.pcap.shared.core.config.ApplicationProperties;
import com.wrk.pcap.shared.core.data.FindAllAttributesObject;
import org.springframework.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.wrk.pcap.shared.core.constant.General.DEFAULT_SORT_ATTRIBUTE;

@RequiredArgsConstructor
@Component
public class ValidationUtil {
    private final ApplicationProperties applicationProperties;

    public FindAllAttributesObject checkFindAllAttributes(Integer offset,Integer limit,String sort){
        Integer maxLimits = applicationProperties.getMaxRecordLimit();
        Integer useOffset = (offset == null) ? 0:Math.abs(offset);
        Integer useLimits = (limit == null || Math.abs(limit)>maxLimits)? maxLimits:Math.abs(limit);
        String sortProperty = StringUtils.hasText(sort) && "default".equals(sort) ?
                DEFAULT_SORT_ATTRIBUTE:sort;

        if(useOffset%useLimits !=0){
            throw new IllegalArgumentException("Please check your offset and limit value. The offset value must be a multiple of the limit value.");
        }
        return new FindAllAttributesObject(useOffset,useLimits,useOffset/useLimits,sortProperty);
    }
}
