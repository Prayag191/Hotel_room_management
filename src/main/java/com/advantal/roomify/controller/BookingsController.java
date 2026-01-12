
package com.advantal.roomify.controller;

import com.advantal.roomify.dto.BookingRequestDto;
import com.advantal.roomify.dto.BookingResponseDto;
import com.advantal.roomify.service.BookingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingsController {

    @Autowired
    private BookingsService bookingsService;

    @PostMapping("/create")
    public ResponseEntity<BookingResponseDto> createBooking(
            @RequestBody BookingRequestDto requestDto) {

        return ResponseEntity.ok(bookingsService.createBooking(requestDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookingResponseDto> getBooking(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(bookingsService.getBookingById(id));
    }

    @GetMapping
    public ResponseEntity<List<BookingResponseDto>> getAllBookings() {
        return ResponseEntity.ok(bookingsService.getAllBookings());
    }

    @PutMapping("/cancel/{id}")
    public ResponseEntity<String> cancelBooking(@PathVariable("id") Integer id) {
        bookingsService.cancelBooking(id);
        return ResponseEntity.ok("Booking cancelled successfully");
    }
}
