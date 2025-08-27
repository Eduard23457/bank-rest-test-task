package org.example.test.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne()
    @JoinColumn(name = "from_card_id")
    private Card fromCard;
    @ManyToOne
    @JoinColumn(name = "to_card_id")
    private Card toCard;
}
