
package com.advantal.roomify.controller;

import com.advantal.roomify.dto.GuestsRequestDto;
import com.advantal.roomify.dto.GuestsResponseDto;
import com.advantal.roomify.service.GuestsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/guests")
public class GuestsController {

    @Autowired
    private GuestsService guestsService;

    // CREATE
    @PostMapping("/create")
    public ResponseEntity<GuestsResponseDto> createGuest(
            @RequestBody GuestsRequestDto requestDto) {

        return ResponseEntity.ok(guestsService.createGuest(requestDto));
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<GuestsResponseDto> getGuestById(
            @PathVariable("id") Integer id) {

        return ResponseEntity.ok(guestsService.getGuestById(id));
    }

    // GET ALL
    @GetMapping("/getAll")
    public ResponseEntity<List<GuestsResponseDto>> getAllGuests() {

        return ResponseEntity.ok(guestsService.getAllGuests());
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<GuestsResponseDto> updateGuest(
            @PathVariable("id") Integer id,
            @RequestBody GuestsRequestDto requestDto) {

        return ResponseEntity.ok(guestsService.updateGuest(id, requestDto));
    }

    // ACTIVATE
    @PutMapping("/{id}/activate")
    public ResponseEntity<String> activateGuest(@PathVariable("id") Integer id) {
        guestsService.activateGuest(id);
        return ResponseEntity.ok("Guest activated");
    }

    // DEACTIVATE
    @PutMapping("/{id}/deactivate")
    public ResponseEntity<String> deactivateGuest(@PathVariable("id") Integer id) {
        guestsService.deactivateGuest(id);
        return ResponseEntity.ok("Guest deactivated");
    }

    // SOFT DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteGuest(@PathVariable("id") Integer id) {
        guestsService.softDeleteGuest(id);
        return ResponseEntity.ok("Guest deleted");
    }
}
