package br.com.fcube.godric.application.account.dto;

import br.com.fcube.godric.domain.account.Account;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "Account")
public class AccountDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY, value = "account_id")
    @ApiModelProperty(hidden = true)
    private Integer id;

    @JsonProperty(value = "document_number")
    @Size(min = 11, max = 14)
    @NotBlank(message = "Document Number cannot be null")
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
