package com.tritonik.smartstay.web.model.request;

import com.tritonik.smartstay.enums.FacilityType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateFacilityWebRequest {

    private FacilityType facilityType;

    private Integer price;
}
