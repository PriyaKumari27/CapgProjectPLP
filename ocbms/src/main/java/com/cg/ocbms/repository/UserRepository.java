package com.cg.ocbms.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.ocbms.dto.User;
@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	User findByUserId(Long userId);

	//public List<User> findAllNotAdmindAndNotDeleted();

	

}
