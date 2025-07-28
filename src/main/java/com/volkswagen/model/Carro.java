package com.volkswagen.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Entity
@Table(name = "tb_carros")
@Data
public class Carro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, length=25, unique=true)
    private String modelo;

    @Column(length=12)
    private String cor;

    @Column(nullable=false, length=8, unique=true)
        @Pattern(regexp = "[A-Z]{3}[0-9][A-Z0-9][0-9]{2}", // Regex para ambos os padrões
             message = "Formato de placa inválido. Use ABC1D23 (Mercosul) ou ABC-1234 (Antigo).")
    private String placa;

    @Column(length=12, nullable = false, unique = true)
    private String chassi;

    @Column(length=4)
    private int ano;

    @Column(length=20)
    private String tipoCombustivel;

    @Column(length=20)
    private String tipoCambio;

}
