package com.tritonik.smartstay.repository;

import com.tritonik.smartstay.entity.Facility;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FacilityRepository extends JpaRepository<Facility,String> {

    List<Facility> findByIdIn(List<String> ids);
}
