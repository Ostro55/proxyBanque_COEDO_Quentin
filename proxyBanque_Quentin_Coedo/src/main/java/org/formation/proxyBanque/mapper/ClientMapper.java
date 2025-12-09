package org.formation.proxyBanque.mapper;

import org.formation.proxyBanque.Dto.ClientCreateDto;
import org.formation.proxyBanque.Dto.ClientDto;
import org.formation.proxyBanque.Dto.ClientUpgradeDto;
import org.formation.proxyBanque.entity.Client;
import org.mapstruct.*;

@Mapper(componentModel = "spring" , unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface ClientMapper {

    ClientDto toDto(Client client);

    @Mapping(target = "id", ignore = true)
    Client toEntity(ClientCreateDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    void updateEntity(@MappingTarget Client entity, ClientUpgradeDto dto);
}