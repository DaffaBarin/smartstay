package com.tritonik.smartstay.web.model.request;

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
public class CreateRoomBookingWebRequest {

    private String roomId;

    private List<String> facilityIds;
}
