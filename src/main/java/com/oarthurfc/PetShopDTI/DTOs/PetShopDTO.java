package com.oarthurfc.PetShopDTI.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class PetShopDTO {
    private String nome;
    private double distancia;
    private double precoPequeno;
    private double precoGrande;
}

