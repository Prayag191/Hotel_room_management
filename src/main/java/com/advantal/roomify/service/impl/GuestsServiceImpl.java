
package com.advantal.roomify.service.impl;

import com.advantal.roomify.dto.GuestsRequestDto;
import com.advantal.roomify.dto.GuestsResponseDto;
import com.advantal.roomify.model.Guests;
import com.advantal.roomify.repository.GuestsRepository;
import com.advantal.roomify.service.GuestsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GuestsServiceImpl implements GuestsService {

    @Autowired
    private GuestsRepository guestsRepository;

    // CREATE
    @Override
    public GuestsResponseDto createGuest(GuestsRequestDto requestDto) {

    	Guests guest = new Guests();
    	guest.setFullName(requestDto.getFullName());
    	guest.setEmail(requestDto.getEmail());
    	guest.setMobile(requestDto.getMobile());
    	guest.setStatus(1);


        Guests saved = guestsRepository.save(guest);
        return mapToDto(saved);
    }

    // GET BY ID
    @Override
    public GuestsResponseDto getGuestById(Integer id) {

        Guests guest = guestsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Guest not found with id: " + id));

        return mapToDto(guest);
    }

    // GET ALL (ACTIVE + INACTIVE)
    @Override
    public List<GuestsResponseDto> getAllGuests() {

        return guestsRepository.findByStatusNot(3)
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    // UPDATE
    @Override
    public GuestsResponseDto updateGuest(Integer id, GuestsRequestDto requestDto) {

        Guests guest = guestsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Guest not found"));

        guest.setFullName(requestDto.getFullName());
        guest.setMobile(requestDto.getMobile());
        guest.setEmail(requestDto.getEmail());
        guest.setAddress(requestDto.getAddress());

        Guests updated = guestsRepository.save(guest);
        return mapToDto(updated);
    }

    // ACTIVATE
    @Override
    public void activateGuest(Integer id) {

        Guests guest = guestsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Guest not found"));

        guest.setStatus(1);
        guestsRepository.save(guest);
    }

    // DEACTIVATE
    @Override
    public void deactivateGuest(Integer id) {

        Guests guest = guestsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Guest not found"));

        guest.setStatus(2);
        guestsRepository.save(guest);
    }

    // SOFT DELETE
    @Override
    public void softDeleteGuest(Integer id) {

        Guests guest = guestsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Guest not found"));

        guest.setStatus(3);
        guestsRepository.save(guest);
    }

    // MAPPER
    private GuestsResponseDto mapToDto(Guests guest) {

        GuestsResponseDto dto = new GuestsResponseDto();
        dto.setId(guest.getId());
        dto.setFullName(guest.getFullName());
        dto.setMobile(guest.getMobile());
        dto.setEmail(guest.getEmail());
        dto.setAddress(guest.getAddress());
        dto.setStatus(guest.getStatus());
        dto.setCreatedAt(guest.getCreatedAt());

        return dto;
    }
}
