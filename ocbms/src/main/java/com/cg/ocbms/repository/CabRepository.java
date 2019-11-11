package com.cg.ocbms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.ocbms.dto.Cab;
@Repository
public interface CabRepository extends JpaRepository<Cab, Long>{

	Cab findByCabId(Long cabId);

}
