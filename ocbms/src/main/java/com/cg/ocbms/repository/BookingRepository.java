package com.cg.ocbms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.ocbms.dto.Booking;
import com.cg.ocbms.dto.User;
@Repository
public interface BookingRepository extends JpaRepository<Booking, Long>{

	public List<Booking> findByUser(User user);

}
