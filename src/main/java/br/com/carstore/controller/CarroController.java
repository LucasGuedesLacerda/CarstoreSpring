package br.com.carstore.controller;

import br.com.carstore.model.Carro;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CarroController {

    @GetMapping("/carro")
    public Carro getCarro() {
        return new Carro("Fusca", "Azul");
    }
}
