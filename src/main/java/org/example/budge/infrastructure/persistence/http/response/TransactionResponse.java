package org.example.budge.infrastructure.persistence.http.response;

import org.example.budge.application.output.TransactionOutput;

public record TransactionResponse(String id, String category, Double amount, String description) {

    public static TransactionResponse from(TransactionOutput output) {
        return new TransactionResponse(output.id(), output.category(), output.value(), output.description());
    }
}
