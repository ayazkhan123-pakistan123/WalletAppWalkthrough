package com.ayaz.walletapp.Controller;

import com.ayaz.walletapp.Entity.Transaction;
import com.ayaz.walletapp.Service.TransactionService;
import com.ayaz.walletapp.Service.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
@RestController
@CrossOrigin
@RequestMapping("/transaction")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;
    @Autowired
    private ValidationService validationService;
    @GetMapping("/wallet_id")
    public ResponseEntity<?> getAll(@PathVariable Long wallet_id){
        return new ResponseEntity<>(transactionService.getAll(wallet_id), HttpStatus.OK);
    }
    @PostMapping("/{wallet_id}")
    public ResponseEntity<?> create(@PathVariable Long wallet_id, @Valid @RequestBody Transaction transaction, BindingResult result) {
        ResponseEntity errors = validationService.validate(result);
        if (errors != null) return errors;
        Transaction transactionSaved = transactionService.createOrUpdate(wallet_id,transaction);
        return new ResponseEntity<Transaction>(transactionSaved, HttpStatus.CREATED);
    }
    @GetMapping("/{wallet_id}/{id}")
    public ResponseEntity<?> getById(@PathVariable Long wallet_id,@PathVariable Long id) {
        return new ResponseEntity<>(transactionService.getAll(wallet_id), HttpStatus.OK);
    }
    @PutMapping("/{wallet_id}/{id}")
    public ResponseEntity<?> update(@PathVariable Long id,@PathVariable Long wallet_id, @Valid @RequestBody Transaction transaction, BindingResult result) {
        ResponseEntity errors = validationService.validate(result);
        if (errors != null) return errors;
        transaction.setId(id);
        Transaction transactionSaved = transactionService.createOrUpdate(wallet_id,transaction);
        return new ResponseEntity<Transaction>(transactionSaved, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        transactionService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
