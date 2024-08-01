package com.oarthurfc.PetShopDTI.models;

import com.oarthurfc.PetShopDTI.pricing.PrecoStrategy;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Data;

@Data
@Entity
@Table(name = "tb_petShop")
public class PetShop {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private double distancia;

    @Transient
    private PrecoStrategy precoPequenoStrategy;
    @Transient
    private PrecoStrategy precoGrandeStrategy;

    public PetShop() {}

    public PetShop(String nome, double distancia, PrecoStrategy precoPequenoStrategy, PrecoStrategy precoGrandeStrategy) {
        this.nome = nome;
        this.distancia = distancia;
        this.precoPequenoStrategy = precoPequenoStrategy;
        this.precoGrandeStrategy = precoGrandeStrategy;
    }

    public double calcularPrecoPequeno(double precoBase, boolean fimDeSemana) {
        return precoPequenoStrategy.calcularPreco(precoBase, fimDeSemana);
    }

    public double calcularPrecoGrande(double precoBase, boolean fimDeSemana) {
        return precoGrandeStrategy.calcularPreco(precoBase, fimDeSemana);
    }

}
