package br.com.fcube.godric.domain.transaction;

import br.com.fcube.godric.application.exception.NotFoundException;
import br.com.fcube.godric.application.transaction.dto.TransactionDTO;
import br.com.fcube.godric.infrastructure.account.AccountRepository;
import br.com.fcube.godric.infrastructure.account.OperationRepository;
import br.com.fcube.godric.infrastructure.transaction.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;
    private final OperationRepository operationRepository;

    @Transactional
    public TransactionDTO save(TransactionDTO transactionDTO) {

        accountRepository.findById(transactionDTO.getAccountId())
                .orElseThrow(() -> new NotFoundException("Account not found!"));

        operationRepository.findById(transactionDTO.getOperationId())
                .orElseThrow(() -> new NotFoundException("Operation not found!"));

        Transaction transaction = transactionRepository.save(transactionDTO.toEntity());

        return TransactionDTO.from(transaction);
    }
}
