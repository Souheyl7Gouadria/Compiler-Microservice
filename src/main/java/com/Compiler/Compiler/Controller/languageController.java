package com.Compiler.Compiler.Controller;

import com.Compiler.Compiler.DTO.ProgrammingLang;
import com.Compiler.Compiler.Services.Interfaces.LanguageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class languageController {

    private final LanguageService languageService;

    @GetMapping("/languages")
    public Flux<ProgrammingLang> getProgrammingLang(){
        return languageService.getAvailableLang();
    }

    @GetMapping("/languages/{id}")
    public Mono<ProgrammingLang> getProgrammingLangById(@PathVariable("id") Integer id) {
        return languageService.getProgrammingLangById(id);
    }

    @GetMapping("/snippet")
    public String getSnippetForLang(@RequestParam(name = "id") String LanguageId){
        return languageService.generateSnippet(LanguageId);
    }
}
