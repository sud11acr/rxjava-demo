package com.rxjava.business.business.model.response;

import com.rxjava.business.business.model.proxy.Cuenta;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountResponse {
    private List<Cuenta> accounts;
}
