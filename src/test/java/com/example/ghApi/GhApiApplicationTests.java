package com.example.ghApi;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.junit.jupiter.api.Test;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GhApiApplicationTests {

    @Autowired
    private TestRestTemplate restTemplate;
    
    @Test
    public void testGetUserRepositories() {
        String username = "gmatterna";
        ResponseEntity<String> response = restTemplate.getForEntity("/api/github/" + username, String.class);
        
        assert(response.getStatusCode() == HttpStatus.OK);
        
        assert(response.getBody() != null && !response.getBody().isEmpty());
    }

    @Test
    public void testGetUserRepositoriesNotFound() {
        String username = "nonexistentuser"; 
        ResponseEntity<String> response = restTemplate.getForEntity("/api/github/" + username, String.class);
        
        assert(response.getStatusCode() == HttpStatus.NOT_FOUND);

        assert(response.getBody() != null && response.getBody().contains("User not found"));
    }

    @Test
    public void testGetUserRepositoriesEmptyUsername() {
        String username = "";
        ResponseEntity<String> response = restTemplate.getForEntity("/api/github/" + username, String.class);

        assert(response.getStatusCode() == HttpStatus.BAD_REQUEST);

        assert(response.getBody() != null && response.getBody().contains("Username cannot be empty"));
    }
}
