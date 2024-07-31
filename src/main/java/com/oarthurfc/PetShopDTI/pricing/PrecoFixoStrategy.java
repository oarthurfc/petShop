package com.oarthurfc.PetShopDTI.pricing;

public class PrecoFixoStrategy implements PrecoStrategy {
    private final double aumentoFixo;

    public PrecoFixoStrategy(double aumentoFixo) {
        this.aumentoFixo = aumentoFixo;
    }

    @Override
    public double calcularPreco(double precoBase, boolean fimDeSemana) {
        return fimDeSemana ? precoBase + aumentoFixo : precoBase;
    }
}

