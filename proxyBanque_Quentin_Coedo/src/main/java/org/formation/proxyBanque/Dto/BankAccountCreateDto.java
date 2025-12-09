package org.formation.proxyBanque.Dto;

import org.springframework.boot.context.properties.bind.DefaultValue;

import java.time.LocalDate;

public record BankAccountCreateDto(
        @DefaultValue()
        Long balance,
        LocalDate opening_date
) {
}
