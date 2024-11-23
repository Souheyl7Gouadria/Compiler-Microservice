package com.Compiler.Compiler.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class JudgeRequest {

    private String source_code;
    private String language_id;
    private String stdin;
    private String expected_output;

}
