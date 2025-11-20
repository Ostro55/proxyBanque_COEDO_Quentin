package org.formation.cofeemachine.service;

import org.formation.cofeemachine.entity.Coffee;

import java.util.List;
import java.util.Optional;

public interface CoffeeService {
    List<Coffee> getCoffees();

    public Coffee save(Coffee coffee);

    public Optional<Coffee> getCoffee(Long id);

    Optional<Coffee> deleteCoffee(Long id);

    Coffee updateCoffee(Long id, Coffee coffee);
}
