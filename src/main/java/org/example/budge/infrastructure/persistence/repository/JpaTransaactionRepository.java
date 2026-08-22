package org.example.budge.infrastructure.persistence.repository;

import org.example.budge.domain.Category;
import org.example.budge.domain.Transaction;
import org.example.budge.domain.TransactionRepository;
import org.example.budge.infrastructure.persistence.entity.TransactionEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JpaTransaactionRepository implements TransactionRepository {

    private final TransactionEntityRepository transactionEntityRepository;

    public JpaTransaactionRepository(TransactionEntityRepository transactionEntityRepository){
        this.transactionEntityRepository = transactionEntityRepository;
    }

    @Override
    public Transaction save(Transaction transaction) {
       var entity =  TransactionEntity.from(transaction);
      return transactionEntityRepository.save(entity).toDomain();

    }

    @Override
    public List<Transaction> findAllByCategory(Category category) {
        return  transactionEntityRepository.findAllByCategory(category)
                 .stream()
                 .map(TransactionEntity::toDomain)
                 .toList();


    }
}
