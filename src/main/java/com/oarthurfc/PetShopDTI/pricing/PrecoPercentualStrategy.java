package com.oarthurfc.PetShopDTI.pricing;

public class PrecoPercentualStrategy implements PrecoStrategy {
    private final double percentualAumento;

    public PrecoPercentualStrategy(double percentualAumento) {
        this.percentualAumento = percentualAumento;
    }

    @Override
    public double calcularPreco(double precoBase, boolean fimDeSemana) {
        return fimDeSemana ? precoBase * (1 + percentualAumento) : precoBase;
    }
}
