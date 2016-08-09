package br.com.leandrojacomelli.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api")
public class BaseController {

    private final Logger log = LoggerFactory.getLogger(BaseController.class);

    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Unexpected error")
    @ExceptionHandler(Exception.class)
    public void DefaultExceptionHandler(HttpServletRequest req, Exception exception) {
        log.error(exception.getMessage());
    }


}
