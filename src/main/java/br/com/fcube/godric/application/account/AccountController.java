package br.com.fcube.godric.application.account;

import br.com.fcube.godric.application.account.dto.AccountDTO;
import br.com.fcube.godric.application.exception.NotFoundException;
import br.com.fcube.godric.domain.account.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService accountService;

    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody AccountDTO accountDto) {
        AccountDTO account = accountService.save(accountDto);
        return ResponseEntity.created(URI.create("/accounts/" + account.getId())).build();
    }

    @GetMapping("/{id}")
    public AccountDTO findById(@PathVariable("id") Integer id) {
        return accountService.findById(id).orElseThrow(NotFoundException::new);
    }
}
