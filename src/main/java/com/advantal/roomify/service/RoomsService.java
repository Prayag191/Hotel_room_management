package com.advantal.roomify.service;

import com.advantal.roomify.dto.RoomsRequestDto;
import com.advantal.roomify.dto.RoomsResponseDto;

import java.util.List;

public interface RoomsService {

    RoomsResponseDto createRoom(RoomsRequestDto requestDto);

    RoomsResponseDto getRoomById(Integer id);

    RoomsResponseDto updateRoom(Integer id, RoomsRequestDto requestDto);

    List<RoomsResponseDto> getAllRooms(String sortBy, String direction);

    List<RoomsResponseDto> getRoomsByStatus(Integer status);

    void activateRoom(Integer id);

    void deactivateRoom(Integer id);

    void softDeleteRoom(Integer id);
}

