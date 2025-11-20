package org.formation.cofeemachine.controller;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.formation.cofeemachine.entity.Coffee;
import org.formation.cofeemachine.service.CoffeeService;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class CoffeeController {

    private final CoffeeService coffeeService;

    @GetMapping("coffees")
    List<Coffee> getCoffees() {
        return coffeeService.getCoffees();
    }

    @GetMapping("coffees/{id}")
    Coffee getCoffee(@PathVariable Long id){
        return coffeeService.getCoffee(id).orElse(null);
    }

    @PostMapping("coffees")
    Coffee createCoffee(@RequestBody Coffee coffee) {
        return coffeeService.save(coffee);
    }

    @DeleteMapping("coffees/delete/{id}")
    Coffee deleteCoffee(@PathVariable Long id, HttpServletResponse response) {
        try {
            Optional<Coffee> coffee = coffeeService.deleteCoffee(id);
            if (coffee.isEmpty())
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, String.format("Could not find coffee with id: %d", id));
            return coffee.orElse(null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping("coffees/update/{id}")
    Coffee updateCoffee(@PathVariable Long id, @RequestBody Coffee coffee) {
        return coffeeService.updateCoffee(id, coffee);
    }
}
