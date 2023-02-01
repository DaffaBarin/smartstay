package com.tritonik.smartstay.service.impl;

import com.tritonik.smartstay.entity.Room;
import com.tritonik.smartstay.helper.Authorization;
import com.tritonik.smartstay.repository.RoomRepository;
import com.tritonik.smartstay.service.RoomService;
import com.tritonik.smartstay.web.model.request.CreateRoomWebRequest;
import com.tritonik.smartstay.web.model.request.UpdateRoomWebRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {


    @Autowired
    private Authorization authorization;

    @Autowired
    private RoomRepository roomRepository;

    @Override
    public Room create(CreateRoomWebRequest request, String role) {
        if (!authorization.isAdmin(role)) throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        Room room = Room.builder().build();
        BeanUtils.copyProperties(request, room);
        return roomRepository.save(room);
    }

    @Override
    public Room updateRoomById(UpdateRoomWebRequest request, String roomId, String role) {
        if (!authorization.isAdmin(role)) throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        if (!roomRepository.existsById(roomId)) throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        Room room = roomRepository.findById(roomId).get();
        BeanUtils.copyProperties(request, room);
        return roomRepository.save(room);
    }

    @Override
    public void deleteRoomById(String roomId, String role) {
        if (!authorization.isAdmin(role)) throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        if (!roomRepository.existsById(roomId)) throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        roomRepository.deleteById(roomId);
    }

    @Override
    public List<Room> getAllRoom() {
        return roomRepository.findAll();
    }
}
