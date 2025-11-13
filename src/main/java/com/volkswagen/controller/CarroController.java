package com.volkswagen.controller;

import com.volkswagen.model.Carro;
import com.volkswagen.repository.CarroRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/carros")
public class CarroController {

    @Autowired
    private CarroRepository carroRepository;

    @GetMapping
    public String listar(Model model, @ModelAttribute("carro") Carro carro) {
        List<Carro> carros = carroRepository.findAll();
        model.addAttribute("carros", carros);
        // 'carro' já será usado pelo formulário (novo ou editado)
        return "carros";
    }

    @PostMapping
    public String salvar(@Valid @ModelAttribute("carro") Carro carro, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("carros", carroRepository.findAll());
            return "carros";
        }

        carroRepository.save(carro);
        return "redirect:/carros";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Carro carro = carroRepository.findById(id).orElse(new Carro());
        model.addAttribute("carro", carro);
        model.addAttribute("carros", carroRepository.findAll());
        return "carros";
    }

    @PostMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id) {
        carroRepository.deleteById(id);
        return "redirect:/carros";
    }

}
