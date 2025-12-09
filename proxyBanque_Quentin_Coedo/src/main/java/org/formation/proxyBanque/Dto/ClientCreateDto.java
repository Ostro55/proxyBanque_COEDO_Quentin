package org.formation.proxyBanque.Dto;

import jakarta.validation.constraints.NotBlank;

public record ClientCreateDto(
        @NotBlank
        String nom,
        @NotBlank
        String prenom,
        @NotBlank
        String address,
        Integer code_postal,
        @NotBlank
        String city,
        @NotBlank
        String phone_number
) {
}
