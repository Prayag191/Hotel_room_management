package com.advantal.roomify.service.impl;

import com.advantal.roomify.dto.RoomsRequestDto;
import com.advantal.roomify.dto.RoomsResponseDto;
import com.advantal.roomify.model.Rooms;
import com.advantal.roomify.repository.RoomsRepository;
import com.advantal.roomify.service.RoomsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;     
import java.util.stream.Collectors;

@Service
public class RoomsServiceImpl implements RoomsService {

    @Autowired
    private RoomsRepository roomsRepository; 

    @Override
    public RoomsResponseDto createRoom(RoomsRequestDto requestDto) {       // controller se aaya hua data yaha aata h 
   
    	Rooms room = new Rooms();                                          // new room entity Object create 
        room.setRoomNumber(requestDto.getRoomNumber());                    // Client ka data entity m set kiya
        room.setRoomType(requestDto.getRoomType());
        room.setPricePerDay(requestDto.getPricePerDay());
        room.setStatus(1); // active

        Rooms savedRoom = roomsRepository.save(room); // JPA automatically insert Query chalayega or ID generate karega
        return mapToResponseDto(savedRoom);           // Entity directly client ko nhi bhejte, DTO k through safe response 
    }

    @Override
    public List<RoomsResponseDto> getAllRooms(String sortBy, String direction) { // Controller se sorting parameter aate hai
    	
        Sort sort = direction.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
                                                                    // agar DESC hua toh descending nhi toh ascending
        
        return roomsRepository.findAll(sort)                        // DB se saare room fetch honge
                .stream()                                           // List ko Stream m convert kiya
                .filter(room -> room.getStatus() != 3)              // soft delete
                .map(this::mapToResponseDto)                        // Har room ko DTO m convert kiya
                .collect(Collectors.toList());                      // Stream ko final List m convert kiya
    }

    @Override
    public void softDeleteRoom(Integer id) {                        // Controller se room ID aate hai
        Rooms room = roomsRepository.findById(id)                   // DB m room search hota hai
                .orElseThrow(() ->
                        new IllegalArgumentException("Room not found with id: " + id));

        room.setStatus(3);                                          // deleted
        roomsRepository.save(room);                                 // Update query run hoti hai
    }
    

    @Override
    public RoomsResponseDto getRoomById(Integer id) {

        Rooms room = roomsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Room not found with id : " + id));

        return mapToResponseDto(room);
    }
    
    @Override
    public RoomsResponseDto updateRoom(Integer id, RoomsRequestDto requestDto) {

        Rooms room = roomsRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Room not found with id: " + id));

        // update fields
        room.setRoomNumber(requestDto.getRoomNumber());
        room.setRoomType(requestDto.getRoomType());
        room.setPricePerDay(requestDto.getPricePerDay());

        Rooms updatedRoom = roomsRepository.save(room);

        return mapToResponseDto(updatedRoom);
    }


    @Override
    public void activateRoom(Integer id) {

        Rooms room = roomsRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Room not found with id: " + id));

        room.setStatus(1); // ACTIVE
        roomsRepository.save(room);
    }

    @Override
    public void deactivateRoom(Integer id) {

        Rooms room = roomsRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Room not found with id: " + id));

        room.setStatus(2); // INACTIVE
        roomsRepository.save(room);
    }


    @Override
    public List<RoomsResponseDto> getRoomsByStatus(Integer status) {

        List<Rooms> rooms;

        if (status == null) {
            // ACTIVE + INACTIVE
            rooms = roomsRepository.findByStatusNot(3);
        } else {
            if (status == 3) {
                throw new IllegalArgumentException("Deleted rooms are not accessible");
            }
            rooms = roomsRepository.findByStatus(status);
        }

        return rooms.stream()
                .map(this::mapToResponseDto)
                .collect(Collectors.toList());
    }

    

    @Override
    public Page<RoomsResponseDto> getAllRoomsPaginated(
            int page,
            int size,
            String sortBy,
            String direction) {

        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        return roomsRepository.findAll(pageable)
                .map(this::mapToResponseDto);
    }    
    
    private RoomsResponseDto mapToResponseDto(Rooms room) {

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
