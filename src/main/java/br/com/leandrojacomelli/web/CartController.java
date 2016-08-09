package br.com.leandrojacomelli.web;

import br.com.leandrojacomelli.domain.Cart;
import br.com.leandrojacomelli.service.CartService;
import br.com.leandrojacomelli.service.exception.SkuNotFoundException;
import br.com.leandrojacomelli.web.dto.CartDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class CartController extends BaseController {

    private final Logger log = LoggerFactory.getLogger(CartController.class);

    @Autowired
    private CartService cartService;

    @RequestMapping(value = "/cart/new",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Cart> newCart(@RequestBody final CartDTO dto) {
        return ResponseEntity.ok(cartService.newCart(dto));
    }


    @RequestMapping(value = "/cart/add-item",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity addItemCart(@RequestBody final CartDTO dto) {
        Cart body = cartService.addItemCart(dto);
        return ResponseEntity.ok(body);
    }


    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Product not found")
    @ExceptionHandler(SkuNotFoundException.class)
    public void DefaultExceptionHandler(HttpServletRequest req, Exception exception) {
        log.error(exception.getMessage());
    }
}
