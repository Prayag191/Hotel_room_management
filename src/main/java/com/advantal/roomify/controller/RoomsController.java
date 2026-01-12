package com.advantal.roomify.controller;

import com.advantal.roomify.dto.RoomsRequestDto;
import com.advantal.roomify.dto.RoomsResponseDto;
import com.advantal.roomify.service.RoomsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
public class RoomsController {

    @Autowired
    private RoomsService roomsService;

    // CREATE
    @PostMapping("/create")
    public ResponseEntity<RoomsResponseDto> createRoom(
            @RequestBody RoomsRequestDto requestDto) {

        return ResponseEntity.ok(roomsService.createRoom(requestDto));
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<RoomsResponseDto> getRoomById(
            @PathVariable("id") Integer id) {

        return ResponseEntity.ok(roomsService.getRoomById(id));
    }

    // GET ALL WITH SORT
    @GetMapping("/get")
    public ResponseEntity<List<RoomsResponseDto>> getAllRooms(
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {

        return ResponseEntity.ok(roomsService.getAllRooms(sortBy, direction));
    }

    // FILTER BY STATUS
    @GetMapping
    public ResponseEntity<List<RoomsResponseDto>> getRoomsByStatus(
            @RequestParam(required = false) Integer status) {

        return ResponseEntity.ok(roomsService.getRoomsByStatus(status));
    }

    // UPDATE
    @PutMapping("/update/{id}")
    public ResponseEntity<RoomsResponseDto> updateRoom(@PathVariable("id") Integer id, @RequestBody RoomsRequestDto requestDto) {

    	RoomsResponseDto response = roomsService.updateRoom(id, requestDto);
    	return ResponseEntity.ok(response);
//        return ResponseEntity.ok(roomsService.updateRoom(id, requestDto));
    }

    // ACTIVATE
    @PutMapping("/{id}/activate")
    public ResponseEntity<String> activateRoom(@PathVariable("id") Integer id) {
        roomsService.activateRoom(id);
        return ResponseEntity.ok("Room activated");
    }

    // DEACTIVATE
    @PutMapping("/{id}/deactivate")
    public ResponseEntity<String> deactivateRoom(@PathVariable("id") Integer id) {
        roomsService.deactivateRoom(id);
        return ResponseEntity.ok("Room deactivated");
    }

    // SOFT DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRoom(@PathVariable("id") Integer id) {
        roomsService.softDeleteRoom(id);
        return ResponseEntity.ok("Room deleted");
    }
}