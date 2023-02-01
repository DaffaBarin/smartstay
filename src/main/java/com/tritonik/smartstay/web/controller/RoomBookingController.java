package com.tritonik.smartstay.web.controller;

import com.tritonik.smartstay.entity.RoomBooking;
import com.tritonik.smartstay.service.RoomBookingService;
import com.tritonik.smartstay.web.model.Response;
import com.tritonik.smartstay.web.model.request.CreateRoomBookingWebRequest;
import com.tritonik.smartstay.web.model.request.UpdateRoomBookingWebRequest;
import com.tritonik.smartstay.web.model.response.RoomBookingWebResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Arrays;

@Validated
@RestController
@RequestMapping("/booking")
public class RoomBookingController {

    @Autowired
    private RoomBookingService roomBookingService;

    private KafkaTemplate<String, String> kafkaTemplate;

    public RoomBookingController(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Response<RoomBookingWebResponse> create(@RequestBody CreateRoomBookingWebRequest request) throws IOException {
        RoomBooking roomBooking = roomBookingService.create(request);
        kafkaTemplate.send("smartstay-room-booking-save", toResponse(roomBooking).toString());
        return Response.<RoomBookingWebResponse>builder()
                .status(HttpStatus.OK.value())
                .data(toResponse(roomBooking))
                .build();
    }

    @PutMapping(
            path = "/{roomBookingId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Response<RoomBookingWebResponse> update(@RequestBody UpdateRoomBookingWebRequest request, @PathVariable String roomBookingId) throws IOException {
        RoomBooking roomBooking = roomBookingService.updateRoomBookingStatus(request, roomBookingId);
        kafkaTemplate.send("smartstay-room-booking-save", toResponse(roomBooking).toString());
        return Response.<RoomBookingWebResponse>builder()
                .status(HttpStatus.OK.value())
                .data(toResponse(roomBooking))
                .build();
    }

    private RoomBookingWebResponse toResponse(RoomBooking roomBooking) {
        RoomBookingWebResponse response = RoomBookingWebResponse.builder().build();
        BeanUtils.copyProperties(roomBooking, response);
        response.setFacilityIds(Arrays.asList(roomBooking.getFacilityIds().split("\\s*,\\s*")));
        return response;
    }
}
