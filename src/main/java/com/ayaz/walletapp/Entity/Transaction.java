package com.ayaz.walletapp.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Min(1)
    @NotBlank(message="amount can not be null")

    private Double amount;
    private String description;
    @Min(1)
    @Max(3)
    private int type;// 1 = income; 2 = expense; 3= transfer
    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date transactionDate;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "wallet_id",nullable = false)
    @JsonIgnore
    private Wallet wallet;

    @PrePersist
    public void setTransactionDate(){this.transactionDate= new Date();}



}
