package com.practicando.demo.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
@Builder
@Getter
@Setter
public class DeleteCustomer {
   private String message;
   private Boolean success;
}
