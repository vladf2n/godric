package br.com.fcube.godric.application.transaction.dto;

import br.com.fcube.godric.domain.account.Account;
import br.com.fcube.godric.domain.transaction.Transaction;
import br.com.fcube.godric.domain.transaction.operation.Operation;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.Instant;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "Transaction")
public class TransactionDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY, value = "transaction_id")
    @ApiModelProperty(hidden = true)
    private Integer id;

    @JsonProperty(value = "account_id")
    @NotNull(message = "Account cannot be null")
    private Integer accountId;

    @JsonProperty(value = "operation_id")
    @NotNull(message = "Operation cannot be null")
    private Integer operationId;

    private Double amount;

    public static TransactionDTO from(Transaction transaction) {
        return TransactionDTO.builder()
                .id(transaction.getId())
                .accountId(transaction.getAccount().getId())
                .operationId(transaction.getOperation().getId())
                .amount(transaction.getAmount())
                .build();
    }

    public Transaction toEntity() {
        return Transaction.builder()
                .account(Account.builder().id(accountId).build())
                .operation(Operation.builder().id(operationId).build())
                .amount(operationId != 4 ? -amount : amount)
                .eventDate(Instant.now())
                .build();
    }
}
