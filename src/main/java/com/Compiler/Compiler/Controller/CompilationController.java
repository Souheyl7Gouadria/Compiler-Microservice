package com.Compiler.Compiler.Controller;

import com.Compiler.Compiler.DTO.CompileRequest;
import com.Compiler.Compiler.DTO.CompileResponse;
import com.Compiler.Compiler.DTO.ProgrammingLang;
import com.Compiler.Compiler.Services.Interfaces.CompilationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class CompilationController {

    private final CompilationService compilationService;

    @PostMapping("/compile")
    public Mono<List<CompileResponse>> compile(
            @RequestBody CompileRequest compileRequest,
            @RequestParam(name = "base64", defaultValue = "false") boolean base64,
            @RequestParam(name = "wait", defaultValue = "true") boolean wait) {

        return compilationService.compileCode(compileRequest, wait, base64);
    }

}
