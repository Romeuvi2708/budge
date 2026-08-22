package org.example.budge.application.input;

import org.example.budge.domain.Category;
import org.springframework.ai.tool.annotation.ToolParam;

public record PersistTransactionInput(@ToolParam(description = "Descrição dos gastos") String description,
                                     @ToolParam(description = "Valor do gasto") Long amount,
                                      @ToolParam(description = "categoria de uma transação") Category category) {
}
