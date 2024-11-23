package com.Compiler.Compiler.Services.Interfaces;

import com.Compiler.Compiler.DTO.ProgrammingLang;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface LanguageService {

    Flux<ProgrammingLang> getAvailableLang();

    String generateSnippet(String languageId);

    Mono<ProgrammingLang> getProgrammingLangById(Integer id);
}
