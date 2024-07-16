package br.com.andreisantos.gestao_vagas.exceptions;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ExceptionsHandlerController {
    private MessageSource messageSource;
    public ExceptionsHandlerController(MessageSource messageSource) {
        this.messageSource = messageSource;
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorMessageDTO>> handlerMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<ErrorMessageDTO> errDTO = new ArrayList<>();
        e.getBindingResult().getFieldErrors().forEach(err -> {
           String message = messageSource.getMessage(err, LocaleContextHolder.getLocale());
           ErrorMessageDTO errorMessageDTO = new ErrorMessageDTO(message, err.getField());
              errDTO.add(errorMessageDTO);
        });
        return new ResponseEntity<>(errDTO, HttpStatus.BAD_REQUEST);
    }
}