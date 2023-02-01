package com.tritonik.smartstay.web.controller;

import com.tritonik.smartstay.entity.Facility;
import com.tritonik.smartstay.service.FacilityService;
import com.tritonik.smartstay.web.model.Response;
import com.tritonik.smartstay.web.model.request.CreateFacilityWebRequest;
import com.tritonik.smartstay.web.model.request.UpdateFacilityWebRequest;
import com.tritonik.smartstay.web.model.response.FacilityWebResponse;
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
@RequestMapping("/facility")
public class FacilityController {

    @Autowired
    private FacilityService facilityService;

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Response<FacilityWebResponse> create(@RequestBody CreateFacilityWebRequest request,
                                            @RequestHeader(value = "Authorization") String role) throws IOException {
        Facility facility = facilityService.create(request, role);
        return Response.<FacilityWebResponse>builder()
                .status(HttpStatus.OK.value())
                .data(toResponse(facility))
                .build();
    }

    @PutMapping(
            path = "/{facilityId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Response<FacilityWebResponse> update(@RequestBody UpdateFacilityWebRequest request,
                                            @RequestHeader(value = "Authorization") String role, @PathVariable String facilityId) throws IOException {
        Facility facility = facilityService.updateFacilityById(request, facilityId , role);
        return Response.<FacilityWebResponse>builder()
                .status(HttpStatus.OK.value())
                .data(toResponse(facility))
                .build();
    }

    @DeleteMapping(
            path = "/{facilityId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Response<Boolean> delete(@RequestHeader(value = "Authorization") String role, @PathVariable String facilityId) throws IOException {
        facilityService.deleteFacilityById(facilityId , role);
        return Response.<Boolean>builder()
                .status(HttpStatus.OK.value())
                .data(true)
                .build();
    }

    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Response<List<FacilityWebResponse>> getAllFacility() {
        List<Facility> facilities = facilityService.getAllFacility();
        return Response.<List<FacilityWebResponse>>builder()
                .status(HttpStatus.OK.value())
                .data(toListResponse(facilities))
                .build();
    }

    private FacilityWebResponse toResponse(Facility facility){
        FacilityWebResponse response = FacilityWebResponse.builder().build();
        BeanUtils.copyProperties(facility, response);
        return response;
    }

    private List<FacilityWebResponse> toListResponse(List<Facility> facilitys) {
        List<FacilityWebResponse> facilityWebResponses = new ArrayList<>();
        for (Facility facility : facilitys) {
            facilityWebResponses.add(toResponse(facility));
        }
        return facilityWebResponses;
    }
}
