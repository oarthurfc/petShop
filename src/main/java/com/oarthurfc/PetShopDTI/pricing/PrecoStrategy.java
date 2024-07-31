package com.oarthurfc.PetShopDTI.pricing;

public interface PrecoStrategy {
    public double calcularPreco(double precoBase, boolean fimDeSemana);
}
