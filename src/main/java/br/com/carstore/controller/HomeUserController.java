package br.com.carstore.controller;

import br.com.carstore.model.CarDTO;
import br.com.carstore.service.CarService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class HomeUserController {

    private final CarService carService;

    public HomeUserController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/user/cars")
    public String viewUserCars(Model model) {
        List<CarDTO> cars = carService.findAll();
        model.addAttribute("cars", cars);
        return "user-cars";
    }

    @GetMapping("/user/cars/{id}")
    public String viewUserCarDetail(@PathVariable Long id, Model model) {
        CarDTO car = null;
        for (CarDTO c : carService.findAll()) {
            if (c.getId().equals(id)) {
                car = c;
                break;
            }
        }
        if (car == null) {
            return "redirect:/user/cars";
        }
        model.addAttribute("car", car);
        return "user-car-detail";
    }
}