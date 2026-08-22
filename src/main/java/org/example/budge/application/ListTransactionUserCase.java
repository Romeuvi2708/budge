package org.example.budge.application;
import org.example.budge.application.output.TransactionOutput;
import org.example.budge.domain.Category;
import org.example.budge.domain.TransactionRepository;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListTransactionUserCase {
    private final TransactionRepository transactionRepository;

    public ListTransactionUserCase(TransactionRepository transactionRepository){
        this.transactionRepository = transactionRepository;
    }
    @Tool(name = "list-transaction",description = "Lista transaçoẽs financeiras por categoria ")
    public List<TransactionOutput> execute(@ToolParam(description = "Categoria de uma transação") Category category){
        return transactionRepository.findAllByCategory(category)
                .stream()
                .map(TransactionOutput::from)
                .toList();
    }
}
