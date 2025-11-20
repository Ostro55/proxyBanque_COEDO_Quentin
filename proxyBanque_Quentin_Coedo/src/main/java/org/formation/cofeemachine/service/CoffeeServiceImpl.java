package org.formation.cofeemachine.service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.formation.cofeemachine.entity.Coffee;
import org.formation.cofeemachine.repository.CoffeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CoffeeServiceImpl implements CoffeeService {

    private final CoffeeRepository coffeeRepository;

    @PostConstruct
    private void initDb() {
        coffeeRepository.saveAll(List.of(
                new Coffee("Grenador"),
                new Coffee("Long"),
                new Coffee("Cream")
        ));
    }

    @Override
    public List<Coffee> getCoffees() {
        return coffeeRepository.findAll();
    }

    @Override
    public Coffee save(Coffee coffee) {
        List<Coffee> coffees = coffeeRepository.findAll();
        if (!coffees.contains(coffee)) {
            coffeeRepository.save(coffee);
        }
        return coffee;
    }

    @Override
    public Optional<Coffee> getCoffee(Long id) {
        return coffeeRepository.findById(id);
    }

    @Override
    public Optional<Coffee> deleteCoffee(Long id) {
        Optional<Coffee> coffee = coffeeRepository.findById(id);
        coffee.ifPresent(coffeeRepository::delete);
        return coffee;
    }

    @Override
    public Coffee updateCoffee(Long id, Coffee coffee) {
        coffee.setId(id);
        if (coffeeRepository.findById(id).isPresent()) {
            return coffee;
        }
        return coffeeRepository.save(coffee);
    }
}
