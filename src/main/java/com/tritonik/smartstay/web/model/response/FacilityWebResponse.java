package com.tritonik.smartstay.web.model.response;

import com.tritonik.smartstay.enums.FacilityType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FacilityWebResponse {

    private String id;

    private FacilityType facilityType;

    private Integer price;
}
