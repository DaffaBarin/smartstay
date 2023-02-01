package com.tritonik.smartstay.web.model.response;

import com.tritonik.smartstay.enums.RoomBookingStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoomBookingWebResponse {

    private String id;

    private String roomId;

    private List<String> facilityIds;

    private Integer totalPrice;

    private RoomBookingStatus status;
}
