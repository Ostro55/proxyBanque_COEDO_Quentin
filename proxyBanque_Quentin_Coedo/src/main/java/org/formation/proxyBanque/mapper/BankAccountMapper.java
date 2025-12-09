package org.formation.proxyBanque.mapper;

import org.formation.proxyBanque.Dto.BankAccountCreateDto;
import org.formation.proxyBanque.Dto.BankAccountDto;
import org.formation.proxyBanque.Dto.BankAccountUpgradeDto;
import org.formation.proxyBanque.entity.BankAccount;
import org.mapstruct.*;

@Mapper(componentModel = "spring" , unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface BankAccountMapper {

    BankAccountDto toDto(BankAccount bankAccount);

    @Mapping(target = "accountNumber", ignore = true)
    BankAccount toEntity(BankAccountCreateDto bankAccountDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "opening_date", ignore = true)
    void updateEntity(@MappingTarget BankAccount entity, BankAccountUpgradeDto dto);
}