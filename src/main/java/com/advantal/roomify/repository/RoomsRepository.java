package com.advantal.roomify.repository;

import com.advantal.roomify.model.Rooms;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomsRepository extends JpaRepository<Rooms, Integer> {

    List<Rooms> findByStatus(Integer status);

    List<Rooms> findByStatusNot(Integer status);
}