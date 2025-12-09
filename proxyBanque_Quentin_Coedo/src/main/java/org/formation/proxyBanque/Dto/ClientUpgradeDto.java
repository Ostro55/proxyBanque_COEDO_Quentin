package org.formation.proxyBanque.Dto;

public record ClientUpgradeDto(
    String nom,
    String prenom,
    String address,
    Integer code_postal,
    String city,
    String phone_number
) {
}
