package com.rxjava.business.business.model.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class BusinessError {
    private String code;
    private String description;
    private String errorCategory;
}
