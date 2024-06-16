package com.app.e_commerce.Source.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BusinessException extends RuntimeException {
    /**
     *
     */
    private static final long serialVersionUID = 4794155883221794945L;

    private String messageCode;
    private String[] varValues;

    public BusinessException(String messageCode, Throwable cause, String... varValues) {
        super(messageCode, cause);
        this.messageCode = messageCode;
        this.varValues = varValues;
    }

    public BusinessException(String messageCode, String... varValues) {
        super(messageCode);
        this.messageCode = messageCode;
        this.varValues = varValues;
    }

    public BusinessException(String messageCode, Throwable cause) {
        super(cause);
        this.messageCode = messageCode;
    }

}
