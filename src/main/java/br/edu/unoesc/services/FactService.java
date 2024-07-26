package br.edu.unoesc.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class FactService {

    private final RestTemplate restTemplate;

    public FactService() {
        this.restTemplate = new RestTemplate();
    }

    public String getRandomFact() {
        String factUrl = "https://uselessfacts.jsph.pl/api/v2/facts/random";
        try {
            ResponseEntity<Map> response = restTemplate.getForEntity(factUrl, Map.class);
            Map<String, Object> body = response.getBody();
            if (body != null) {
                return (String) body.get("text");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Unable to fetch fact";
    }
}
