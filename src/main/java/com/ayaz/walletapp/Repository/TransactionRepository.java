package com.ayaz.walletapp.Repository;

import com.ayaz.walletapp.Entity.Transaction;
import com.ayaz.walletapp.Entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Long> {
    List<Transaction> findByWallet(Wallet wallet);
}
