package com.example.ghApi;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/github")
class GhApiController {
    private final GhApiService githubService;

    public GhApiController(GhApiService githubService) {
        this.githubService = githubService;
    }

    @GetMapping("/{username}")
    public ResponseEntity<?> getUserRepositories(@PathVariable String username) {
        try {
            return ResponseEntity.ok(githubService.getUserRepositories(username));
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(404).body(new ErrorResponse(404, "User not found"));
        }
    }
}
