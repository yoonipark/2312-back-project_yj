package com.github.backproject.web.advice;

import com.github.backproject.service.exceptions.AuthenticationEntryPointException;
import com.github.backproject.service.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.AccessDeniedException;

@RestController
@Slf4j
public class ExceptionControllerAdvice {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public String handleNotFoundException (NotFoundException nfe) {
        log.error("Client 요청이후 DB 검색 중 에러로 다음퍼럼 출력합니다. " + nfe.getMessage());
        return nfe.getMessage();
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(AuthenticationEntryPointException.class)
    public String handleAuthenticationException(AuthenticationEntryPointException aep) {
        log.error("Client 요청에 문제가 있습니다. " + aep.getMessage());
        return aep.getMessage();
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(AccessDeniedException.class)
    public String handleAccessDeniedException(AccessDeniedException ade) {
        log.error("Client 요청에 문제가 있습니다. " + ade.getMessage());
        return ade.getMessage();
    }

}
