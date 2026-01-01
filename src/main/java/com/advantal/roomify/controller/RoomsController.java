package com.advantal.roomify.controller;

import com.advantal.roomify.dto.RoomsRequestDto;
import com.advantal.roomify.dto.RoomsResponseDto;
import com.advantal.roomify.service.RoomsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
public class RoomsController {

    @Autowired
    private RoomsService roomsService;

    //creating a room
    @PostMapping("/create")
    public ResponseEntity<RoomsResponseDto> createRoom(
            @RequestBody RoomsRequestDto requestDto) {

        RoomsResponseDto response = roomsService.createRoom(requestDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // get all rooms (sorting support)
    @GetMapping("/get")
    public ResponseEntity<List<RoomsResponseDto>> getAllRooms(
            @RequestParam(name = "sortBy", defaultValue = "id") String sortBy,
            @RequestParam(name = "direction", defaultValue = "asc") String direction) {

        List<RoomsResponseDto> rooms = roomsService.getAllRooms(sortBy, direction);
        return ResponseEntity.ok(rooms);

    }

    //soft delete room
    // status = 3 (Deleted)
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRoom(
            @PathVariable(name = "id") Integer id) {

        roomsService.softDeleteRoom(id);
        return ResponseEntity.ok("Room deleted successfully (Soft Delete)");
    }

}
