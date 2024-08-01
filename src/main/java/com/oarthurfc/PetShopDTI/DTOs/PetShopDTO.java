package com.oarthurfc.PetShopDTI.DTOs;

import com.oarthurfc.PetShopDTI.pricing.TipoAdicional;
import lombok.Data;

@Data
public class PetShopDTO {
    private String nome;
    private double distancia;

    private double precoBasePequeno;
    private double precoBaseGrande;

    private double precoFinalPequeno; 
    private double precoFinalGrande;   

    private TipoAdicional tipoAdicional; 
    private double adicional;     
}
