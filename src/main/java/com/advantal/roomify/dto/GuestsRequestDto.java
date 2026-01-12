package com.advantal.roomify.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GuestsRequestDto {

    private String fullName;
    private String mobile;
    private String email;
    private String address;
}
