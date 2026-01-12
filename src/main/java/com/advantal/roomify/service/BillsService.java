package com.advantal.roomify.service;

import com.advantal.roomify.dto.BillsResponseDto;


import java.util.List;
public interface BillsService {

    BillsResponseDto generateBill(Integer bookingId);

    BillsResponseDto getBillById(Integer id);

    List<BillsResponseDto> getAllBills();
}

