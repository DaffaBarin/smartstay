package com.tritonik.smartstay.service;

import com.tritonik.smartstay.entity.Facility;
import com.tritonik.smartstay.entity.Room;
import com.tritonik.smartstay.web.model.request.CreateFacilityWebRequest;
import com.tritonik.smartstay.web.model.request.CreateRoomWebRequest;
import com.tritonik.smartstay.web.model.request.UpdateFacilityWebRequest;
import com.tritonik.smartstay.web.model.request.UpdateRoomWebRequest;

import java.util.List;

public interface FacilityService {

    Facility create(CreateFacilityWebRequest request, String role);

    Facility updateFacilityById(UpdateFacilityWebRequest request, String facilityId, String role);

    void deleteFacilityById(String facilityId, String role);

    List<Facility> getAllFacility();
}
