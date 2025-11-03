package co.edu.tdea.jalesma.controllers;

import co.edu.tdea.jalesma.entities.Bolso;
import co.edu.tdea.jalesma.services.BolsoService;
import co.edu.tdea.jalesma.services.MarcaService;
import co.edu.tdea.jalesma.services.MaterialService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/bolsos")
@RequiredArgsConstructor
public class BolsoController {

    private final BolsoService bolsoService;
    private final MarcaService marcaService;
    private final MaterialService materialService;

    @GetMapping
    public String listarBolsos(Model model) {
        model.addAttribute("bolsos", bolsoService.findAll());
        model.addAttribute("marcas", marcaService.findAll());     
        model.addAttribute("materiales", materialService.findAll()); 
        return "bolsos/list";
    }

    @GetMapping("/nuevo")
    public String nuevoBolso(Model model) {
        model.addAttribute("bolso", new Bolso());
        model.addAttribute("marcas", marcaService.findAll());
        model.addAttribute("materiales", materialService.findAll());
        return "bolsos/form";
    }

    @PostMapping
    public String guardarBolso(@ModelAttribute Bolso bolso) {
        bolsoService.save(bolso);
        return "redirect:/bolsos";
    }

    @GetMapping("/editar/{id}")
    public String editarBolso(@PathVariable int id, Model model) {
        model.addAttribute("bolso", bolsoService.findById(id));
        model.addAttribute("marcas", marcaService.findAll());
        model.addAttribute("materiales", materialService.findAll());
        return "bolsos/form";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarBolso(@PathVariable int id) {
        bolsoService.delete(id);
        return "redirect:/bolsos";
    }
}