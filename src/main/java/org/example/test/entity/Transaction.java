package org.example.test.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

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
    private BigDecimal amount;
    private LocalDateTime createdAt;
}
