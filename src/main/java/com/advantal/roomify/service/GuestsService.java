package com.advantal.roomify.service;

import com.advantal.roomify.dto.GuestsRequestDto;
import com.advantal.roomify.dto.GuestsResponseDto;

import java.util.List;

public interface GuestsService {

    GuestsResponseDto createGuest(GuestsRequestDto requestDto);

    GuestsResponseDto getGuestById(Integer id);

    List<GuestsResponseDto> getAllGuests();

    GuestsResponseDto updateGuest(Integer id, GuestsRequestDto requestDto);

    void activateGuest(Integer id);

    void deactivateGuest(Integer id);

    void softDeleteGuest(Integer id);
}