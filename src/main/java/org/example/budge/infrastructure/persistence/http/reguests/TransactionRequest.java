package org.example.budge.infrastructure.persistence.http.reguests;

import org.example.budge.application.input.PersistTransactionInput;
import org.example.budge.domain.Category;

public record TransactionRequest(String description, Category category, Long amount) {



    public PersistTransactionInput toInput(){
        return  new PersistTransactionInput(description, amount, category);
    }
}
