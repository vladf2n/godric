package br.com.fcube.godric.transaction;

import br.com.fcube.godric.application.account.dto.AccountDTO;
import br.com.fcube.godric.application.transaction.dto.TransactionDTO;
import br.com.fcube.godric.domain.account.AccountService;
import br.com.fcube.godric.domain.transaction.Transaction;
import br.com.fcube.godric.infrastructure.transaction.TransactionRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TransactionRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Flyway flyway;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountService accountService;

    @BeforeEach
    public void init() {
        flyway.clean();
        flyway.migrate();
    }

    @Test
    public void shouldNotCreateTransactionWithANonExistentAccount() throws Exception {
        TransactionDTO transaction = TransactionDTO.builder().accountId(1).operationId(4).amount(100.0).build();

        mockMvc.perform(post("/transactions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(transaction)))
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldNotCreateTransactionWithANonExistentOperation() throws Exception {

        AccountDTO account = AccountDTO.builder().documentNumber("12345689123").build();
        account = accountService.save(account);

        Assertions.assertNotNull(account.getId());

        TransactionDTO transaction = TransactionDTO.builder().accountId(account.getId()).operationId(5).amount(100.0).build();

        mockMvc.perform(post("/transactions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(transaction)))
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldCreateValidTransaction() throws Exception {

        AccountDTO account = AccountDTO.builder().documentNumber("12345689123").build();
        account = accountService.save(account);

        Assertions.assertNotNull(account.getId());

        TransactionDTO transaction = TransactionDTO.builder().accountId(account.getId()).operationId(4).amount(100.0).build();

        mockMvc.perform(post("/transactions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(transaction)))
                .andExpect(status().isCreated());

        Transaction savedTransaction = transactionRepository.findById(1).orElse(null);

        Assertions.assertNotNull(savedTransaction);
    }

    @Test
    public void amountShouldBeNegativeWhenOperationIdIsNot4() throws Exception {

        AccountDTO account = AccountDTO.builder().documentNumber("12345689123").build();
        account = accountService.save(account);

        Assertions.assertNotNull(account.getId());

        Double amount = 100.0;

        TransactionDTO transaction = TransactionDTO.builder().accountId(account.getId()).operationId(3).amount(amount).build();

        mockMvc.perform(post("/transactions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(transaction)))
                .andExpect(status().isCreated());

        Transaction savedTransaction = transactionRepository.findById(1).orElse(null);

        amount *= -1;

        Assertions.assertNotNull(savedTransaction);
        Assertions.assertEquals(savedTransaction.getAmount(), amount);
    }
}
