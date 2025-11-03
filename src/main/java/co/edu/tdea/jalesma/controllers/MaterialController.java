package co.edu.tdea.jalesma.controllers;

import co.edu.tdea.jalesma.entities.Material;
import co.edu.tdea.jalesma.services.MaterialService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/materiales")
@RequiredArgsConstructor
public class MaterialController {

    private final MaterialService materialService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("materiales", materialService.findAll());
        return "materiales/list";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("material", new Material());
        return "materiales/form";
    }

    @PostMapping
    public String guardar(@ModelAttribute Material material) {
        materialService.save(material);
        return "redirect:/materiales";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable int id, Model model) {
        model.addAttribute("material", materialService.findById(id));
        return "materiales/form";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable int id, RedirectAttributes redirectAttrs) {
        Material material = materialService.findById(id);

        if (material != null && material.getBolsos() != null && !material.getBolsos().isEmpty()) {
            redirectAttrs.addFlashAttribute("warning",
                    "El material estaba asociado a bolsos y fue removido de ellos antes de eliminarlo.");
        } else {
            redirectAttrs.addFlashAttribute("success", "Material eliminado correctamente.");
        }

        materialService.delete(id);
        return "redirect:/materiales";
    }

}
