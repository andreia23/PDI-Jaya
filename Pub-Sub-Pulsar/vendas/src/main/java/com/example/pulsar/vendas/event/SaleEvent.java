package com.example.pulsar.vendas.event;

import java.math.BigDecimal;
import java.util.List;

public class SaleEvent {
    private BigDecimal value;
    private String idStore;
    private List<String> products;
    private String adrres;
    private String idCliente;
    private String idSale;

    public SaleEvent(BigDecimal value, String idStore, List<String> products, String adrres, String idCliente, String idSale) {
    }
}
