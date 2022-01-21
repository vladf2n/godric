package br.com.fcube.godric.domain.account;

import br.com.fcube.godric.application.account.dto.AccountDTO;
import br.com.fcube.godric.infrastructure.account.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    @Transactional
    public AccountDTO save(AccountDTO accountDTO) {
        Account account = accountRepository.save(accountDTO.toEntity());
        return AccountDTO.from(account);
    }

    public Optional<AccountDTO> findById(Integer id) {
        return accountRepository.findById(id).map(AccountDTO::from);
    }
}
