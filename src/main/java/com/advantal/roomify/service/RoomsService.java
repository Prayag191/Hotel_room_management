package com.advantal.roomify.service;

import com.advantal.roomify.dto.RoomsResponseDto;
import com.advantal.roomify.dto.RoomsRequestDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface RoomsService {

    RoomsResponseDto createRoom(RoomsRequestDto requestDto);

    RoomsResponseDto getRoomById(Integer id);

    RoomsResponseDto updateRoom(Integer id, RoomsRequestDto requestDto);

    List<RoomsResponseDto> getAllRooms(String sortBy, String direction);

    void activateRoom(Integer id);

    void deactivateRoom(Integer id);
    
    void softDeleteRoom(Integer id);
    
    List<RoomsResponseDto> getRoomsByStatus(Integer status);

    Page<RoomsResponseDto> getAllRoomsPaginated(
            int page,
            int size,
            String sortBy,
            String direction
    );

}
