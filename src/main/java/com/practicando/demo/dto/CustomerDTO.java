package com.practicando.demo.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CustomerDTO {
    private String firstName;
    private String lastName;
    private String email;
}
