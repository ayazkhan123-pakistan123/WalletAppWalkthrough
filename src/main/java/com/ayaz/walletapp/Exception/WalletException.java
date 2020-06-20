package com.ayaz.walletapp.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class WalletException extends RuntimeException{
    public WalletException (String Message){super(Message);}
}
