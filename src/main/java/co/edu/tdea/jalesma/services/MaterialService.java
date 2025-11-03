package co.edu.tdea.jalesma.services;

import co.edu.tdea.jalesma.entities.Material;

import java.util.List;

public interface MaterialService {
    List<Material> findAll();
    Material findById(int id);
    Material save(Material material);
    void delete(int id);
    Material update(Material material);
    long count();
}
