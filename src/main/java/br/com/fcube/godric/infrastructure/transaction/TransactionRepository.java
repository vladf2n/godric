package br.com.fcube.godric.infrastructure.transaction;

import br.com.fcube.godric.domain.transaction.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
}
