package com.oarthurfc.PetShopDTI.DTOs;

import com.oarthurfc.PetShopDTI.pricing.TipoAdicional;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PetShopDTO {
    private String nome;
    private double distancia;

    private double precoBasePequeno;
    private double precoBaseGrande;

    private TipoAdicional tipoAdicional;

    private double adicional;

    private double precoFinalPequeno;
    private double precoFinalGrande;
    
    private double precoTotal;
}
