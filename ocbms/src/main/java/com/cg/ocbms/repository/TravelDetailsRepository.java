package com.cg.ocbms.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cg.ocbms.dto.TravelDetails;

public interface TravelDetailsRepository extends JpaRepository<TravelDetails, Long>{
	@Query("Select source from TravelDetails")
	public List<String> findBySource();
//
//	public List<String> findByDestination();

}
