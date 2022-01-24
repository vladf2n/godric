package br.com.fcube.godric.application.account;

import br.com.fcube.godric.application.account.dto.AccountDTO;
import br.com.fcube.godric.application.exception.NotFoundException;
import br.com.fcube.godric.domain.account.AccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/accounts")
@Api(value = "Account", tags = {"Accounts"})
public class AccountController {

    private final AccountService accountService;

    @PostMapping
    @ApiOperation(value = "Create a new account", tags = {"Accounts"})
    public ResponseEntity<Void> create(@Valid @RequestBody AccountDTO accountDto) {

        log.info("Persisting account with: {}", accountDto);

        AccountDTO account = accountService.save(accountDto);
        return ResponseEntity.created(URI.create("/accounts/" + account.getId())).build();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Return account with given id", tags = {"Accounts"})
    public AccountDTO findById(
            @ApiParam(value = "Account ID", required = true, example = "1")
            @PathVariable("id") Integer id
    ) {
        log.info("Searching account with id #{}", id);

        return accountService.findById(id).orElseThrow(NotFoundException::new);
    }
}
