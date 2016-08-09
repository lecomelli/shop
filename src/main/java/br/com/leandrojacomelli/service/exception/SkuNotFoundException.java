package br.com.leandrojacomelli.service.exception;

/**
 * Created by Leandro Jacomelli on 6/15/16.
 */
public class SkuNotFoundException extends RuntimeException {
    public SkuNotFoundException(String sku) {
        super(String.format("Sku %s not found", sku));
    }
}
