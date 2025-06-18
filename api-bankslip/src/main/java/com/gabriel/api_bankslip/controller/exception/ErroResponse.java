package com.gabriel.api_bankslip.controller.exception;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErroResponse {

    private String error;
    private int code;
    private Date timestamp;
    private String path;


}
