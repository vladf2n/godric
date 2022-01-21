package br.com.fcube.godric.domain.transaction;

import br.com.fcube.godric.domain.account.Account;
import br.com.fcube.godric.domain.transaction.operation.Operation;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.Instant;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = { "id" })
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @ManyToOne
    @JoinColumn(name = "operation_id")
    private Operation operation;

    private Double amount;

    @CreatedDate
    private Instant eventDate;
}
