package com.tritonik.smartstay.web.controller;

import com.tritonik.smartstay.entity.Room;
import com.tritonik.smartstay.service.RoomService;
import com.tritonik.smartstay.web.model.Response;
import com.tritonik.smartstay.web.model.request.CreateRoomWebRequest;
import com.tritonik.smartstay.web.model.request.UpdateRoomWebRequest;
import com.tritonik.smartstay.web.model.response.RoomWebResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Validated
@RestController
@RequestMapping("/room")
/**
 * Controller for Attendance
 */
@Slf4j
public class RoomController {

    @Autowired
    private RoomService roomService;

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Response<RoomWebResponse> create(@RequestBody CreateRoomWebRequest request,
                                            @RequestHeader(value = "Authorization") String role) throws IOException {
        Room room = roomService.create(request, role);
        return Response.<RoomWebResponse>builder()
                .status(HttpStatus.OK.value())
                .data(toResponse(room))
                .build();
    }

    @PutMapping(
            path = "/{roomId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Response<RoomWebResponse> update(@RequestBody UpdateRoomWebRequest request,
                                            @RequestHeader(value = "Authorization") String role, @PathVariable String roomId) throws IOException {
        Room room = roomService.updateRoomById(request, roomId , role);
        return Response.<RoomWebResponse>builder()
                .status(HttpStatus.OK.value())
                .data(toResponse(room))
                .build();
    }

    @DeleteMapping(
            path = "/{roomId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Response<Boolean> delete(@RequestHeader(value = "Authorization") String role, @PathVariable String roomId) throws IOException {
        roomService.deleteRoomById(roomId , role);
        return Response.<Boolean>builder()
                .status(HttpStatus.OK.value())
                .data(true)
                .build();
    }

    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Response<List<RoomWebResponse>> getAllRoom() {
        List<Room> rooms = roomService.getAllRoom();
        return Response.<List<RoomWebResponse>>builder()
                .status(HttpStatus.OK.value())
                .data(toListResponse(rooms))
                .build();
    }

    private RoomWebResponse toResponse(Room room){
        RoomWebResponse response = RoomWebResponse.builder().build();
        BeanUtils.copyProperties(room, response);
        return response;
    }

    private List<RoomWebResponse> toListResponse(List<Room> rooms) {
        List<RoomWebResponse> roomWebResponses = new ArrayList<>();
        for (Room room : rooms) {
            roomWebResponses.add(toResponse(room));
        }
        return roomWebResponses;
    }
}
