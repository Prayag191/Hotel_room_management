package com.advantal.roomify.repository;

import com.advantal.roomify.model.Guests;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GuestsRepository extends JpaRepository<Guests, Integer> {

    List<Guests> findByStatus(Integer status);

    List<Guests> findByStatusNot(Integer status);
}