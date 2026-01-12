package com.advantal.roomify.service.impl;

import com.advantal.roomify.dto.RoomsRequestDto;
import com.advantal.roomify.dto.RoomsResponseDto;
import com.advantal.roomify.model.Rooms;
import com.advantal.roomify.repository.RoomsRepository;
import com.advantal.roomify.service.RoomsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoomsServiceImpl implements RoomsService {

    @Autowired
    private RoomsRepository roomsRepository;

    // CREATE
    @Override
    public RoomsResponseDto createRoom(RoomsRequestDto requestDto) {

        Rooms room = new Rooms();
        room.setRoomNumber(requestDto.getRoomNumber());
        room.setRoomType(requestDto.getRoomType());
        room.setPricePerDay(requestDto.getPricePerDay());
        room.setStatus(1);

        Rooms savedRoom = roomsRepository.save(room);
        return mapToDto(savedRoom);
    }

    // GET BY ID
    @Override
    public RoomsResponseDto getRoomById(Integer id) {

        Rooms room = roomsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Room not found with id: " + id));

        return mapToDto(room);
    }

    // UPDATE
    @Override
    public RoomsResponseDto updateRoom(Integer id, RoomsRequestDto requestDto) {

        Rooms room = roomsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Room not found with id: " + id));

        System.out.println(room);
        room.setRoomNumber(requestDto.getRoomNumber());   
        room.setRoomType(requestDto.getRoomType());
        room.setPricePerDay(requestDto.getPricePerDay());

        Rooms updated = roomsRepository.save(room);
        return mapToDto(updated);
    }

    // GET ALL WITH SORTING
    @Override
    public List<RoomsResponseDto> getAllRooms(String sortBy, String direction) {

        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        return roomsRepository.findAll(sort)
                .stream()
                .filter(room -> room.getStatus() != 3)
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    // FILTER BY STATUS
    @Override
    public List<RoomsResponseDto> getRoomsByStatus(Integer status) {

        List<Rooms> rooms;

        if (status == null) {
            rooms = roomsRepository.findByStatusNot(3);
        } else {
            rooms = roomsRepository.findByStatus(status);
        }

        return rooms.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    // ACTIVATE
    @Override
    public void activateRoom(Integer id) {

        Rooms room = roomsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Room not found"));

        room.setStatus(1);
        roomsRepository.save(room);
    }

    // DEACTIVATE
    @Override
    public void deactivateRoom(Integer id) {

        Rooms room = roomsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Room not found"));

        room.setStatus(2);
        roomsRepository.save(room);
    }

    // SOFT DELETE
    @Override
    public void softDeleteRoom(Integer id) {

        Rooms room = roomsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Room not found"));

        room.setStatus(3);
        roomsRepository.save(room);
    }

    // MAPPER
    private RoomsResponseDto mapToDto(Rooms room) {

        RoomsResponseDto dto = new RoomsResponseDto();
        dto.setId(room.getId());
        dto.setRoomNumber(room.getRoomNumber());
        dto.setRoomType(room.getRoomType());
        dto.setPricePerDay(room.getPricePerDay());
        dto.setStatus(room.getStatus());
        dto.setCreatedAt(room.getCreatedAt());

        return dto;
    }
}