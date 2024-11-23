package com.Compiler.Compiler.Services.Implementation;

import com.Compiler.Compiler.DTO.ProgrammingLang;
import com.Compiler.Compiler.Services.Interfaces.LanguageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
@RequiredArgsConstructor
public class LanguageServiceImpl implements LanguageService {

    private final WebClient webClient;

    @Override
    public Flux<ProgrammingLang> getAvailableLang() {
        return webClient.get()
                .uri("/languages")
                .retrieve()
                .bodyToFlux(ProgrammingLang.class);
    }

    @Override
    public String generateSnippet (String languageId){
        return switch (languageId) {
            case "52" -> "console.log('Hello, World!')";
            case "54" -> "#include <iostream>\nint main() {\n    std::cout << \"Hello, World!\";\n    return 0;\n}";
            case "71" -> "print('Hello, World!')";
            case "62" ->
                    "public class Main {\n    public static void main(String[] args) {\n        System.out.println(\"Hello, World!\");\n    }\n}";
            default -> " ";
        };
    }

    public Mono<ProgrammingLang> getProgrammingLangById(Integer id) {
        return webClient.get()
                .uri("/languages/" + id)
                .retrieve()
                .bodyToMono(ProgrammingLang.class);
    }

}
