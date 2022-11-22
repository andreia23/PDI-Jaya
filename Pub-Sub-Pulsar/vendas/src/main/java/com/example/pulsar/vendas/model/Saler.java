package com.example.pulsar.vendas.model;

import java.math.BigDecimal;
import java.util.List;

public class Saler {
    private BigDecimal value;
    private String idStore;
    private List<String> products;
    private String adrres;
    private String idCliente;
    private String idSale;

    public BigDecimal getValue() {
        return value;
    }

    public String getIdStore() {
        return idStore;
    }

    public List<String> getProducts() {
        return products;
    }

    public String getAdrres() {
        return adrres;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public String getIdSale() {
        return idSale;
    }
}
