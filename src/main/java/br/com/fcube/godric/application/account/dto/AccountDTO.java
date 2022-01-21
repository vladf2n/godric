package br.com.fcube.godric.application.account.dto;

import br.com.fcube.godric.domain.account.Account;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY, value = "account_id")
    private Integer id;

    @JsonProperty(value = "document_number")
    private String documentNumber;

    public static AccountDTO from(Account account) {
        return AccountDTO.builder()
                .id(account.getId())
                .documentNumber(account.getDocumentNumber())
                .build();
    }

    public Account toEntity() {
        return Account.builder()
                .documentNumber(documentNumber)
                .build();
    }
}
