package br.com.leandrojacomelli.service;

import br.com.leandrojacomelli.domain.*;
import br.com.leandrojacomelli.repository.CartRepository;
import br.com.leandrojacomelli.repository.ItemLineRepository;
import br.com.leandrojacomelli.repository.ProductRepository;
import br.com.leandrojacomelli.service.exception.SkuNotFoundException;
import br.com.leandrojacomelli.web.dto.CartDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

@Service
public class CartService {

    private static Map<Customer, Cart> cache = new ConcurrentHashMap<>();

    @Autowired
    private CustomerService customerService;

    @Autowired
    private DiscountService discountService;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ItemLineRepository itemLineRepository;

    public Cart newCart(CartDTO dto) {
        Customer customer = customerService.findOrCreate(dto.getCustomer());

        return cache.computeIfAbsent(customer, key -> {
                    Cart cart = new Cart(key);
                    return cartRepository.save(cart);
                }
        );
    }


    public Cart addItemCart(CartDTO dto) {
        Customer customer = customerService.findOrCreate(dto.getCustomer());
        return cache.compute(customer, ((key, cart) -> {

            final Product product = productRepository.
                    findBySku(dto.getSku()).orElseThrow(() -> new SkuNotFoundException(dto.getSku()));

            if (cart == null) {
                cart = new Cart(key);
            }
            Stream<DiscountConfiguration> activeDiscounts = discountService.findActiveDiscounts(product);
            addProductToCart(cart, product, activeDiscounts);

            return cart;
        }));

    }


    public void addProductToCart(Cart cart, Product product, Stream<DiscountConfiguration> activeDiscounts) {
        final Optional<ItemLine> first = cart
                .getItems()
                .stream()
                .filter(itemLine -> itemLine.getProduct().equals(product))
                .findFirst();

        if (first.isPresent()) {
            ItemLine itemLine = first.get();
            itemLine.setCartQuantity(itemLine.getCartQuantity() + 1);
            itemLine.setPayedQuantity(itemLine.getPayedQuantity() + 1);
            itemLineRepository.save(itemLine);
        } else {
            ItemLine itemLine = itemLineRepository.save(new ItemLine(cart, product, 1, 1, product.getPrice()));
            cart.getItems().add(itemLine);
        }
        activeDiscounts.forEach(discount -> discount.applyDiscount(cart));


    }
}
