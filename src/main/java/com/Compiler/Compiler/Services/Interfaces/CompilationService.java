package com.Compiler.Compiler.Services.Interfaces;

import com.Compiler.Compiler.DTO.CompileRequest;
import com.Compiler.Compiler.DTO.CompileResponse;
import reactor.core.publisher.Mono;

import java.util.List;


public interface CompilationService {
    Mono<List<CompileResponse>> compileCode(CompileRequest compileRequest, boolean wait, boolean base64);

}
