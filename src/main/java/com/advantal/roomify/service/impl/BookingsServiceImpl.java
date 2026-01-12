
package com.advantal.roomify.service.impl;

import com.advantal.roomify.dto.BookingRequestDto;
import com.advantal.roomify.dto.BookingResponseDto;
import com.advantal.roomify.model.Bookings;
import com.advantal.roomify.model.Guests;
import com.advantal.roomify.model.Rooms;
import com.advantal.roomify.repository.BookingsRepository;
import com.advantal.roomify.repository.GuestsRepository;
import com.advantal.roomify.repository.RoomsRepository;
import com.advantal.roomify.service.BookingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingsServiceImpl implements BookingsService {

    @Autowired
    private BookingsRepository bookingsRepository;

    @Autowired
    private GuestsRepository guestsRepository;

    @Autowired
    private RoomsRepository roomsRepository;

    @Override
    public BookingResponseDto createBooking(BookingRequestDto requestDto) {

        Guests guest = guestsRepository.findById(requestDto.getGuestId())
                .orElseThrow(() -> new RuntimeException("Guest not found"));

        Rooms room = roomsRepository.findById(requestDto.getRoomId())
                .orElseThrow(() -> new RuntimeException("Room not found"));

        long days = ChronoUnit.DAYS.between(
                requestDto.getCheckInDate(),
                requestDto.getCheckOutDate()
        );

        if (days <= 0) {
            throw new RuntimeException("Invalid booking dates");
        }

        int totalAmount = (int) days * room.getPricePerDay();

        Bookings booking = new Bookings();
        booking.setGuest(guest);
        booking.setRoom(room);
        booking.setCheckInDate(requestDto.getCheckInDate());
        booking.setCheckOutDate(requestDto.getCheckOutDate());
        booking.setTotalDays((int) days);
        booking.setTotalAmount(totalAmount);
        booking.setStatus(1);

        Bookings savedBooking = bookingsRepository.save(booking);

        return mapToDto(savedBooking);
    }

    @Override
    public BookingResponseDto getBookingById(Integer id) {

        Bookings booking = bookingsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        return mapToDto(booking);
    }

    @Override
    public List<BookingResponseDto> getAllBookings() {

        return bookingsRepository.findAll()
                .stream()
                .filter(b -> b.getStatus() != 3)
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void cancelBooking(Integer id) {

        Bookings booking = bookingsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        booking.setStatus(2);
        bookingsRepository.save(booking);
    }

    private BookingResponseDto mapToDto(Bookings booking) {

        BookingResponseDto dto = new BookingResponseDto();
        dto.setBookingId(booking.getId());
        dto.setGuestId(booking.getGuest().getId());
        dto.setGuestName(booking.getGuest().getFullName());
        dto.setRoomId(booking.getRoom().getId());
        dto.setRoomNumber(booking.getRoom().getRoomNumber());
        dto.setCheckInDate(booking.getCheckInDate());
        dto.setCheckOutDate(booking.getCheckOutDate());
        dto.setTotalDays(booking.getTotalDays());
        dto.setTotalAmount(booking.getTotalAmount());
        dto.setStatus(booking.getStatus());
        dto.setCreatedAt(booking.getCreatedAt());

        return dto;
    }
}
