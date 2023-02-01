package com.tritonik.smartstay.repository;

import com.tritonik.smartstay.entity.RoomBooking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomBookingRepository extends JpaRepository<RoomBooking, String> {
}
