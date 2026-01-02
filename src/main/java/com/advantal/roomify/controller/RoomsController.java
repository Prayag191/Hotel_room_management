package com.advantal.roomify.controller;

import com.advantal.roomify.dto.RoomsRequestDto;
import com.advantal.roomify.dto.RoomsResponseDto;
import com.advantal.roomify.service.RoomsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Page;


import java.util.List;

@RestController
@RequestMapping("/api/rooms")
public class RoomsController {

    @Autowired
    private RoomsService roomsService;
    
    // get all rooms (sorting support)
    @GetMapping("/get")
    public ResponseEntity<List<RoomsResponseDto>> getAllRooms(
            @RequestParam(name = "sortBy", defaultValue = "id") String sortBy,
            @RequestParam(name = "direction", defaultValue = "asc") String direction) {

        List<RoomsResponseDto> rooms = roomsService.getAllRooms(sortBy, direction);
        return ResponseEntity.ok(rooms);

    }
    
    @GetMapping("/{id}")
    public RoomsResponseDto getRoomById(@PathVariable(name = "id") Integer id) {
        return roomsService.getRoomById(id);
    }

    @GetMapping("/status")
    public ResponseEntity<List<RoomsResponseDto>> getRoomsByStatus(
            @RequestParam(name = "status", required = false) Integer status) {

        return ResponseEntity.ok(roomsService.getRoomsByStatus(status));
    }

    @GetMapping("/page")
    public ResponseEntity<?> getRoomsPaginated(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "5") int size,
            @RequestParam(name = "sortBy", defaultValue = "id") String sortBy,
            @RequestParam(name = "direction", defaultValue = "asc") String direction) {

        return ResponseEntity.ok(
                roomsService.getAllRoomsPaginated(page, size, sortBy, direction)
        );
    }


    

    // creating a room
    @PostMapping("/create")
    public ResponseEntity<RoomsResponseDto> createRoom(
            @RequestBody RoomsRequestDto requestDto) {

        RoomsResponseDto response = roomsService.createRoom(requestDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<RoomsResponseDto> updateRoom(
            @PathVariable("id") Integer id,
            @RequestBody RoomsRequestDto requestDto) {

        RoomsResponseDto response = roomsService.updateRoom(id, requestDto);
        return ResponseEntity.ok(response);
    }
    
    
    @PutMapping("/{id}/activate")
    public ResponseEntity<String> activateRoom(@PathVariable("id") Integer id) {
        roomsService.activateRoom(id);
        return ResponseEntity.ok("Room activated successfully");
    }

    @PutMapping("/{id}/deactivate")
    public ResponseEntity<String> deactivateRoom(@PathVariable("id") Integer id) {
        roomsService.deactivateRoom(id);
        return ResponseEntity.ok("Room deactivated successfully");
    }
    

    // soft delete room
    // status = 3 (Deleted)
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRoom(@PathVariable("id") Integer id) {
        roomsService.softDeleteRoom(id);
        return ResponseEntity.ok("Room deleted successfully");
    }

}
