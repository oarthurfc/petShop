package com.oarthurfc.PetShopDTI.models;

import com.oarthurfc.PetShopDTI.pricing.PrecoFixoStrategy;
import com.oarthurfc.PetShopDTI.pricing.PrecoPercentualStrategy;
import com.oarthurfc.PetShopDTI.pricing.PrecoStrategy;
import com.oarthurfc.PetShopDTI.pricing.TipoAdicional;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PostLoad;
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
    private double precoBasePequeno;
    private double precoBaseGrande;

    private TipoAdicional tipoAdicional;
    private double adicional;

    @Transient
    private PrecoStrategy precoStrategy;

    public PetShop(){};

    public PetShop(String nome, double distancia, double precoBasePequeno, double precoBaseGrande,
                TipoAdicional tipoAdicional, double adicional) {
    this.nome = nome;
    this.distancia = distancia;
    this.precoBasePequeno = precoBasePequeno;
    this.precoBaseGrande = precoBaseGrande;
    this.tipoAdicional = tipoAdicional;
    this.adicional = adicional;
    this.precoStrategy = tipoAdicional == TipoAdicional.PERCENTUAL
        ? new PrecoPercentualStrategy(adicional)
        : new PrecoFixoStrategy(adicional);
    }


    @PostLoad
    private void postLoad() {
        this.precoStrategy = tipoAdicional == TipoAdicional.PERCENTUAL
            ? new PrecoPercentualStrategy(adicional)
            : new PrecoFixoStrategy(adicional);
    }

    public double calcularPreco(double precoBase, boolean fimDeSemana) {
        if (precoStrategy == null) {
            throw new IllegalStateException("PrecoStrategy não foi inicializado corretamente.");
        }
        return precoStrategy.calcularPreco(precoBase, fimDeSemana);
    }
}

