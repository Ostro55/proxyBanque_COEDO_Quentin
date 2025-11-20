package org.formation.proxyBanque.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String prenom;
    private String address;
    private Integer code_postal;
    private String city;
    private String phone_number;

    @OneToMany(mappedBy = "owner", orphanRemoval = true, cascade = CascadeType.ALL)
    Set<BankAccount> bank_accounts = new HashSet<>();
    // Maybe should have separated it between two account instead of a Set, one for saving and a classic one
    // or add a limit of two to the set
}
