package com.tritonik.smartstay.web.model.request;

import com.tritonik.smartstay.enums.RoomBookingStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateRoomBookingWebRequest {

    private RoomBookingStatus status;
}
