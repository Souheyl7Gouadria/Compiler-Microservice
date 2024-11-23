package com.Compiler.Compiler.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class CompileResponse {

    private UUID testCaseId;
    private String output;
    private boolean success;
    private String message;
    private String time;
    private Integer memory;
    private String status_code_description;

}
