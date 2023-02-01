package com.tritonik.smartstay.service.impl;

import com.tritonik.smartstay.entity.Facility;
import com.tritonik.smartstay.entity.Room;
import com.tritonik.smartstay.entity.RoomBooking;
import com.tritonik.smartstay.enums.RoomBookingStatus;
import com.tritonik.smartstay.repository.FacilityRepository;
import com.tritonik.smartstay.repository.RoomBookingRepository;
import com.tritonik.smartstay.repository.RoomRepository;
import com.tritonik.smartstay.service.RoomBookingService;
import com.tritonik.smartstay.web.model.request.CreateRoomBookingWebRequest;
import com.tritonik.smartstay.web.model.request.UpdateRoomBookingWebRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoomBookingServiceImpl implements RoomBookingService {

    @Autowired
    private RoomBookingRepository roomBookingRepository;

    @Autowired
    private FacilityRepository facilityRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Override
    public RoomBooking create(CreateRoomBookingWebRequest request) {
        if (!roomRepository.existsById(request.getRoomId())) throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        RoomBooking roomBooking = RoomBooking
                .builder()
                .roomId(request.getRoomId())
                .facilityIds(toFacilityIds(request.getFacilityIds()))
                .totalPrice(toTotalPrice(request.getRoomId(),request.getFacilityIds()))
                .status(RoomBookingStatus.CREATED)
                .build();
        return roomBookingRepository.save(roomBooking);
    }

    private String toFacilityIds(List<String> facilityIds) {
        return facilityIds.stream().collect(Collectors.joining(","));
    }

    private Integer toTotalPrice(String roomId, List<String> facilityIds) {
        List<Facility> facilities = facilityRepository.findByIdIn(facilityIds);
        Room room = roomRepository.findById(roomId).get();
        return room.getPrice() + facilities.stream().mapToInt(Facility::getPrice).sum();
    }

    @Override
    public RoomBooking updateRoomBookingStatus(UpdateRoomBookingWebRequest request, String roomBookingId) {
        if (!roomBookingRepository.existsById(roomBookingId)) throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        RoomBooking roomBooking = roomBookingRepository.findById(roomBookingId).get();
        roomBooking.setStatus(request.getStatus());
        return roomBookingRepository.save(roomBooking);
    }
}
