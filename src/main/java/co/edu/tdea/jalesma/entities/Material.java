package co.edu.tdea.jalesma.entities;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
@Table(name="Materiales")
public class Material {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String tipo;
    private double precio;

   @OneToMany(mappedBy = "materiales", cascade = CascadeType.ALL)
    private List<Bolso> bolsos;
}
