package br.com.fcube.godric.infrastructure.account;


import br.com.fcube.godric.domain.account.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Integer> {
}
