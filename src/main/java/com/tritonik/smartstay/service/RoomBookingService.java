package com.tritonik.smartstay.service;

import com.tritonik.smartstay.entity.RoomBooking;
import com.tritonik.smartstay.web.model.request.CreateRoomBookingWebRequest;
import com.tritonik.smartstay.web.model.request.UpdateRoomBookingWebRequest;

public interface RoomBookingService {

    RoomBooking create(CreateRoomBookingWebRequest request);

    RoomBooking updateRoomBookingStatus(UpdateRoomBookingWebRequest request, String roomBookingId);
}
