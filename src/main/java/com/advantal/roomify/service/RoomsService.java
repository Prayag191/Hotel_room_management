package com.advantal.roomify.service;

import com.advantal.roomify.dto.RoomsRequestDto;
import com.advantal.roomify.dto.RoomsResponseDto;
import java.util.List;

public interface RoomsService {

    RoomsResponseDto createRoom(RoomsRequestDto requestDto);

    List<RoomsResponseDto> getAllRooms(String sortBy, String direction);

    void softDeleteRoom(Integer id);
}
