package org.example.budge.application;

import org.example.budge.application.input.PersistTransactionInput;
import org.example.budge.application.output.TransactionOutput;
import org.example.budge.domain.Transaction;
import org.example.budge.domain.TransactionRepository;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;

@Service

public class PersistTransactionUseCase {
    private final TransactionRepository transactionRepository;

    public PersistTransactionUseCase(TransactionRepository transactionRepository){
        this.transactionRepository = transactionRepository;
    }

    @Tool(name = "persist-transaction",description = "Persiste uma nova transação financeira")
    public TransactionOutput execute(PersistTransactionInput Input){
        var transaction = transactionRepository.save(
                new Transaction(Input.description(), Input.amount(), Input.category()));

        return TransactionOutput.from(transaction);
    }
}
