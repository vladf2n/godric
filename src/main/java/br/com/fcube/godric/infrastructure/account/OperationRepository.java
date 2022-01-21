package br.com.fcube.godric.infrastructure.account;

import br.com.fcube.godric.domain.transaction.operation.Operation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperationRepository extends JpaRepository<Operation, Integer> {
}
