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

    @Override
    public RoomsResponseDto createRoom(RoomsRequestDto requestDto) {
        Rooms room = new Rooms();
        room.setRoomNumber(requestDto.getRoomNumber());
        room.setRoomType(requestDto.getRoomType());
        room.setPricePerDay(requestDto.getPricePerDay());
        room.setStatus(1); // active

        Rooms savedRoom = roomsRepository.save(room);
        return mapToResponseDto(savedRoom);
    }

    @Override
    public List<RoomsResponseDto> getAllRooms(String sortBy, String direction) {
        Sort sort = direction.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();

        return roomsRepository.findAll(sort)
                .stream()
                .filter(room -> room.getStatus() != 3) // soft delete
                .map(this::mapToResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public void softDeleteRoom(Integer id) {
        Rooms room = roomsRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("Room not found with id: " + id));

        room.setStatus(3); // deleted
        roomsRepository.save(room);
    }


    private RoomsResponseDto mapToResponseDto(Rooms room) {
        RoomsResponseDto dto = new RoomsResponseDto();
        dto.setId(room.getId());
        dto.setRoomNumber(room.getRoomNumber());
        dto.setRoomType(room.getRoomType());
        dto.setPricePerDay(room.getPricePerDay());
        dto.setStatus(room.getStatus());
        return dto;
    }
}
