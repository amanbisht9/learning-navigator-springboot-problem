package crio.learningnavigator.lms.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
@RequestMapping("/hidden-feature")
public class EggController {

    private final WebClient webClient;

    // Constructor to inject WebClient
    public EggController(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://numbersapi.com").build();
    }

    @GetMapping("/{id}")
    public String getRandomNumberFact(@PathVariable int id) {
        // Call the external API and retrieve the response
        String response = webClient
            .get()
            .uri("/" + id)  // Append the ID to the URL
            .retrieve()
            .bodyToMono(String.class)
            .block();  // block() to wait for the response

        return response;  // Return the API response
    }
}