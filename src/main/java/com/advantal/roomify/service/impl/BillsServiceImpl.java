
package com.advantal.roomify.service.impl;

import com.advantal.roomify.dto.BillsResponseDto;
import com.advantal.roomify.model.Bills;
import com.advantal.roomify.model.Bookings;
import com.advantal.roomify.repository.BillsRepository;
import com.advantal.roomify.repository.BookingsRepository;
import com.advantal.roomify.service.BillsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BillsServiceImpl implements BillsService {

    @Autowired
    private BillsRepository billsRepository;

    @Autowired
    private BookingsRepository bookingsRepository;

    @Override
    public BillsResponseDto generateBill(Integer bookingId) {

        // 1️⃣ Booking fetch
        Bookings booking = bookingsRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        // 2️⃣ Bill create
        Bills bill = new Bills();
        bill.setNumberOfDays(booking.getTotalDays());
        bill.setRoomCharges(booking.getRoom().getPricePerDay());
        bill.setTotalAmount(
                booking.getTotalDays() * booking.getRoom().getPricePerDay()
        );

        Bills savedBill = billsRepository.save(bill);

        // 3️⃣ Response DTO
        BillsResponseDto dto = new BillsResponseDto();
        dto.setId(savedBill.getId());
        dto.setRoomCharges(savedBill.getRoomCharges());
        dto.setNumberOfDays(savedBill.getNumberOfDays());
        dto.setTotalAmount(savedBill.getTotalAmount());
        dto.setGeneratedAt(savedBill.getGeneratedAt());


        return dto;
    }

    @Override
    public BillsResponseDto getBillById(Integer id) {
        Bills bill = billsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bill not found"));

        BillsResponseDto dto = new BillsResponseDto();
        dto.setId(bill.getId());
        dto.setNumberOfDays(bill.getNumberOfDays());
        dto.setRoomCharges(bill.getRoomCharges());
        dto.setTotalAmount(bill.getTotalAmount());
        dto.setGeneratedAt(bill.getGeneratedAt());

        return dto;
    }

    @Override
    public List<BillsResponseDto> getAllBills() {
        return billsRepository.findAll()
                .stream()
                .map(bill -> {
                    BillsResponseDto dto = new BillsResponseDto();
                    dto.setId(bill.getId());
                    dto.setNumberOfDays(bill.getNumberOfDays());
                    dto.setRoomCharges(bill.getRoomCharges());
                    dto.setTotalAmount(bill.getTotalAmount());
                    dto.setGeneratedAt(bill.getGeneratedAt());
                    return dto;
                })
                .toList();
    }
}

