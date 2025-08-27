package org.example.test.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "cards")
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String number;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private LocalDate date;

    @Enumerated(EnumType.STRING)
    private CardStatus status;

    private BigDecimal balance = BigDecimal.ZERO;

   @OneToMany(mappedBy = "fromCard")
    private List<Transaction>requestTransactional;
   @OneToMany(mappedBy = "toCard")
   private List<Transaction>responseTransactional;

   public void addRequest(Transaction transaction){
       if(requestTransactional==null){
           requestTransactional = new ArrayList<>();
       }
       requestTransactional.add(transaction);
       transaction.setFromCard(this);
   }
   public void addResponse(Transaction transaction){
       if(responseTransactional==null){
           responseTransactional = new ArrayList<>();
       }
       responseTransactional.add(transaction);
       transaction.setToCard(this);
   }



}
