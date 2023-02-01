package com.tritonik.smartstay.service;

import com.tritonik.smartstay.entity.Room;
import com.tritonik.smartstay.web.model.request.CreateRoomWebRequest;
import com.tritonik.smartstay.web.model.request.UpdateRoomWebRequest;

import java.util.List;

public interface RoomService {

    Room create(CreateRoomWebRequest request, String role);

    Room updateRoomById(UpdateRoomWebRequest request, String roomId, String role);

    void deleteRoomById(String roomId, String role);

    List<Room> getAllRoom();
}
