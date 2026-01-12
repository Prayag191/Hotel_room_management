
package com.advantal.roomify.controller;

import com.advantal.roomify.dto.BillsResponseDto;


import com.advantal.roomify.service.BillsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/bills")
public class BillsController {

    @Autowired
    private BillsService billsService;

    // ✅ GENERATE BILL FROM BOOKING ID
    @PostMapping("/generate/{bookingId}")
    public ResponseEntity<BillsResponseDto> generateBill(
            @PathVariable("bookingId") Integer bookingId) {

        return ResponseEntity.ok(billsService.generateBill(bookingId));
    }

    // ✅ GET BILL BY BILL ID
    @GetMapping("/{id}")
    public ResponseEntity<BillsResponseDto> getBillById(
            @PathVariable("id") Integer id) {

        return ResponseEntity.ok(billsService.getBillById(id));
    }

    // ✅ GET ALL BILLS
    @GetMapping
    public ResponseEntity<List<BillsResponseDto>> getAllBills() {
        return ResponseEntity.ok(billsService.getAllBills());
    }
}
