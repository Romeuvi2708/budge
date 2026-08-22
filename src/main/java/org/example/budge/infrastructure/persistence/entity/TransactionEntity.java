package org.example.budge.infrastructure.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.budge.domain.Category;
import org.example.budge.domain.Transaction;
import org.example.budge.domain.TransactionId;

import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
public class TransactionEntity {
    @Id
    private UUID id;
    private String description;
    private Long amount;
    private Category category;


    public static TransactionEntity from(Transaction transaction){
        return new TransactionEntity(transaction.getId().uuid(),
                transaction.getDescription(),
                transaction.getAmount(),
                transaction.getCategory() );
    }

    public Transaction toDomain(){
        return new Transaction(
                new TransactionId(this.id),
                this.description,
                this.amount,
                this.category
        );
    }
}
