package com.tritonik.smartstay.service.impl;

import com.tritonik.smartstay.entity.Facility;
import com.tritonik.smartstay.helper.Authorization;
import com.tritonik.smartstay.repository.FacilityRepository;
import com.tritonik.smartstay.service.FacilityService;
import com.tritonik.smartstay.web.model.request.CreateFacilityWebRequest;
import com.tritonik.smartstay.web.model.request.UpdateFacilityWebRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class FacilityServiceImpl implements FacilityService {

    @Autowired
    private Authorization authorization;

    @Autowired
    private FacilityRepository facilityRepository;

    @Override
    public Facility create(CreateFacilityWebRequest request, String role) {
        if (!authorization.isAdmin(role)) throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        Facility facility = Facility.builder().build();
        BeanUtils.copyProperties(request, facility);
        return facilityRepository.save(facility);
    }

    @Override
    public Facility updateFacilityById(UpdateFacilityWebRequest request, String facilityId, String role) {
        if (!authorization.isAdmin(role)) throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        if (!facilityRepository.existsById(facilityId)) throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        Facility facility = facilityRepository.findById(facilityId).get();
        BeanUtils.copyProperties(request, facility);
        return facilityRepository.save(facility);
    }

    @Override
    public void deleteFacilityById(String facilityId, String role) {
        if (!authorization.isAdmin(role)) throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        if (!facilityRepository.existsById(facilityId)) throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        facilityRepository.deleteById(facilityId);
    }

    @Override
    public List<Facility> getAllFacility() {
        return facilityRepository.findAll();
    }
}
