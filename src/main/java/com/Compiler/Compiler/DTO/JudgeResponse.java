package com.Compiler.Compiler.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JudgeResponse {
    private String stdout;
    private String time;
    private Integer memory;
    private String stderr;
    private String token;
    private String compile_output;
    private String message;
    private Status status;

}
