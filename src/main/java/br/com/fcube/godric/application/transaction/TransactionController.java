package br.com.fcube.godric.application.transaction;

import br.com.fcube.godric.application.transaction.dto.TransactionDTO;
import br.com.fcube.godric.domain.transaction.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody TransactionDTO transactionDTO) {
        TransactionDTO transaction = transactionService.save(transactionDTO);
        return ResponseEntity.created(URI.create("/transactions/" + transaction.getId())).build();
    }
}
