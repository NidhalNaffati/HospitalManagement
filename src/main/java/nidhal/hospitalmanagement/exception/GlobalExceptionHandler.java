package nidhal.hospitalmanagement.exception;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import java.util.NoSuchElementException;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    // handle NoSuchElementException
    @ExceptionHandler(NoSuchElementException.class)
    public ModelAndView handleResourceNotFoundException(NoSuchElementException exception) {
        log.error("Exception: ", exception);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("errorStatusCode", 404);
        modelAndView.addObject("errorMessage", exception.getMessage());
        modelAndView.setViewName("error");
        return modelAndView;
    }

    // handle all other exceptions that are not declared here.
    @ExceptionHandler(Exception.class)
    public ModelAndView handleException(NoSuchElementException exception) {
        log.error("Exception: ", exception);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("errorStatusCode", 404);
        modelAndView.addObject("errorMessage", exception.getMessage());
        modelAndView.setViewName("error");
        return modelAndView;
    }
}

