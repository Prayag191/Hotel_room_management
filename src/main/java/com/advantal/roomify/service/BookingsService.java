package com.advantal.roomify.service;

import com.advantal.roomify.dto.BookingRequestDto;
import com.advantal.roomify.dto.BookingResponseDto;

import java.util.List;

public interface BookingsService {

    BookingResponseDto createBooking(BookingRequestDto requestDto);

    BookingResponseDto getBookingById(Integer id);

    List<BookingResponseDto> getAllBookings();

    void cancelBooking(Integer id);
}

