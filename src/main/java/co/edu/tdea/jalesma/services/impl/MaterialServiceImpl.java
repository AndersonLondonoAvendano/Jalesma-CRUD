package co.edu.tdea.jalesma.services.impl;


import co.edu.tdea.jalesma.entities.Material;
import co.edu.tdea.jalesma.repositories.BolsoRepository;
import co.edu.tdea.jalesma.repositories.MaterialRepository;
import co.edu.tdea.jalesma.services.MaterialService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MaterialServiceImpl implements MaterialService {

    private final MaterialRepository materialRepository;
    private final BolsoRepository bolsoRepository;


    @Override
    public List<Material> findAll() {
        return materialRepository.findAll();
    }

    @Override
    public Material findById(int id) {
        return materialRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Material no encontrado"));
    }

    @Override
    public Material save(Material material) {
        return materialRepository.save(material);
    }

public void delete(int id) {
        Material material = findById(id);

        if (material == null) return;

        // Quitar material de cada bolso asociado
        material.getBolsos().forEach(bolso -> {
            bolso.getMateriales().remove(material);
            bolsoRepository.save(bolso);
        });

        materialRepository.delete(material);
    }

    @Override
    public Material update(Material material) {
        return materialRepository.save(material);
    }

    public long count() {
        return materialRepository.count();
    }
}
