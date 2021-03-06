package com.ayaz.walletapp.Controller;

import com.ayaz.walletapp.Entity.Wallet;
import com.ayaz.walletapp.Service.ValidationService;
import com.ayaz.walletapp.Service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
@RestController
@RequestMapping("/wallet")
@CrossOrigin
public class WalletController {
    @Autowired
    private WalletService walletService;
    @Autowired
    private ValidationService validationService;

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Wallet wallet, BindingResult result) {
        ResponseEntity errors = validationService.validate(result);
        if (errors != null) return errors;
        Wallet walletSave = walletService.crearteOrUpdate(wallet);
        return new ResponseEntity<Wallet>(walletSave, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody Wallet wallet, BindingResult result) {
        ResponseEntity errors = validationService.validate(result);
        if (errors != null) return errors;
        wallet.setId(id);
        Wallet walletSave = walletService.crearteOrUpdate(wallet);
        return new ResponseEntity<Wallet>(walletSave, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        walletService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(walletService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return new ResponseEntity<>(walletService.getById(id), HttpStatus.OK);
    }

}
