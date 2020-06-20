package com.ayaz.walletapp.Service;

import com.ayaz.walletapp.Entity.Transaction;
import com.ayaz.walletapp.Entity.Wallet;
import com.ayaz.walletapp.Exception.WalletException;
import com.ayaz.walletapp.Repository.TransactionRepository;
import com.ayaz.walletapp.Repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private WalletRepository walletRepository;
    public List<Transaction> getAll(Long walletId) {
        Optional<Wallet> wallet = walletRepository.findById(walletId);
        if(wallet.isPresent()){
            return transactionRepository.findByWallet(wallet.get());
        }
        return null;
    }
    //    public Wallet getById(Long id,Long wallet_id){
//        Optional<Transaction> wallet = walletRepository.findById(wallet_id);
//        if (wallet.isPresent()) {
//            transactionRepository.save(transaction);
//
//            return transaction;
//        }
//       return null;
//    }
    public Transaction createOrUpdate(Long walletId, Transaction transaction) {
        Optional<Wallet> wallet = walletRepository.findById(walletId);
        if(wallet.isPresent()){
            transaction.setWallet(wallet.get());
            transactionRepository.save(transaction);
        }
        return null;


    }

    public boolean delete(Long id) {
        Optional<Transaction> wallet = transactionRepository.findById(id);
        if (wallet.isPresent()) {
            transactionRepository.delete(wallet.get());
            return true;
        }

        throw new WalletException("transaction with " + id + " Dose not exists ");
    }
}
