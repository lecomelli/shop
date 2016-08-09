package br.com.leandrojacomelli.web.dto;

public class CartDTO {

    private String customer;
    private String sku;

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }
}
