package co.edu.tdea.jalesma.controllers;

import co.edu.tdea.jalesma.services.BolsoService;
import co.edu.tdea.jalesma.services.MarcaService;
import co.edu.tdea.jalesma.services.MaterialService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class DashboardController {

    private final BolsoService bolsoService;
    private final MarcaService marcaService;
    private final MaterialService materialService;

    @GetMapping("/")
    public String dashboard(Model model) {

        // Contadores
        model.addAttribute("totalBolsos", bolsoService.count());
        model.addAttribute("totalMarcas", marcaService.count());
        model.addAttribute("totalMateriales", materialService.count());

        // Bolsos agrupados por color
        Map<String, Long> bolsosPorColor = bolsoService.countBolsosByColor();
        model.addAttribute("colores", bolsosPorColor.keySet());
        model.addAttribute("cantidadPorColor", bolsosPorColor.values());

        // Bolsos agrupados por rango de precio
        Map<String, Long> bolsosPorPrecio = bolsoService.countBolsosByPriceRange();
        model.addAttribute("rangosPrecio", bolsosPorPrecio.keySet());
        model.addAttribute("cantidadPorRango", bolsosPorPrecio.values());

        return "index"; 
    }
}
