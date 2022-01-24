package br.com.fcube.godric.application.transaction;

import br.com.fcube.godric.application.transaction.dto.TransactionDTO;
import br.com.fcube.godric.domain.transaction.TransactionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/transactions")
@Api(value = "Transaction", tags = {"Transactions"})
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping
    @ApiOperation(value = "Create a new transaction", tags = {"Transactions"})
    public ResponseEntity<Void> create(@Valid @RequestBody TransactionDTO transactionDTO) {

        log.info("Persisting transaction with: {}", transactionDTO);

        TransactionDTO transaction = transactionService.save(transactionDTO);
        return ResponseEntity.created(URI.create("/transactions/" + transaction.getId())).build();
    }
}
