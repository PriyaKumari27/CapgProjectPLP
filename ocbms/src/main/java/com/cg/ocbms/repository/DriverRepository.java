package com.cg.ocbms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.ocbms.dto.Driver;
import com.cg.ocbms.dto.User;
@Repository
public interface DriverRepository extends JpaRepository<Driver, Long>{

	Driver findByDriverId(Long driverId);

}
