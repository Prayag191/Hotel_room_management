package com.advantal.roomify.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.advantal.roomify.model.Rooms;

@Repository
public interface RoomsRepository extends JpaRepository<Rooms, Integer> {
	
	List<Rooms> findByStatus(Integer status);

}
