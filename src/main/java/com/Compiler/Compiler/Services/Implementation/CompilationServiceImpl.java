package com.Compiler.Compiler.Services.Implementation;

import com.Compiler.Compiler.DTO.CompileRequest;
import com.Compiler.Compiler.DTO.CompileResponse;
import com.Compiler.Compiler.DTO.JudgeRequest;
import com.Compiler.Compiler.DTO.JudgeResponse;
import com.Compiler.Compiler.DTO.TestCase;
import com.Compiler.Compiler.Services.Interfaces.CompilationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
@RequiredArgsConstructor
public class CompilationServiceImpl implements CompilationService {
    private static final Logger logger = LoggerFactory.getLogger(CompilationServiceImpl.class);

    private final WebClient webClient;

    @Override
    public Mono<List<CompileResponse>> compileCode(CompileRequest compileRequest, boolean wait, boolean base64) {
        logger.info("Received compile request: {}", compileRequest);
        String languageId = compileRequest.getLanguage().getId().toString();

        return Flux.fromIterable(compileRequest.getTest_cases())
                .flatMap(testCase ->
                    makeCompileRequest(compileRequest.getSource_code(), languageId, testCase, wait, base64)
                )
                .collectList();
    }

    private Mono<CompileResponse> makeCompileRequest(String sourceCode, String languageId, TestCase testCase, boolean wait, boolean base64) {
        logger.info("Making compile request for test case: {}", testCase);
        return webClient.post()
                .uri(uriBuilder -> uriBuilder
                        .path("/submissions")
                        .queryParam("base64", base64)
                        .queryParam("wait", wait)
                        .build())
                .body(BodyInserters.fromValue(
                        JudgeRequest.builder()
                                .source_code(sourceCode)
                                .language_id(languageId)
                                .stdin(testCase.getInput())
                                .expected_output(testCase.getExpectedOutput())
                                .build()))
                .retrieve()
                .bodyToMono(JudgeResponse.class)
                .map(response -> CompileResponse.builder()
                        .output(response.getStdout())
                        .success(response.getStatus().getDescription().equals("Accepted"))
                        .message(response.getStderr())
                        .time(response.getTime())
                        .memory(response.getMemory())
                        .status_code_description(response.getStatus().getDescription())
                        .testCaseId(testCase.getId())
                        .build());
    }
}
