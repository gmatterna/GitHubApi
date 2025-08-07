package com.example.ghApi;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service  // 
class GhApiService {
    private final RestTemplate restTemplate;

    public GhApiService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<RepositoryDto> getUserRepositories(String username) {
        String repoUrl = "https://api.github.com/users/" + username + "/repos";

        try {
            ResponseEntity<RepositoryResponse[]> response =
                    restTemplate.getForEntity(repoUrl, RepositoryResponse[].class);

            if (response.getBody() == null) {
                throw new UserNotFoundException();
            }

            return List.of(response.getBody()).stream()
                    .filter(repo -> !repo.fork)
                    .map(repo -> new RepositoryDto(
                            repo.name,
                            repo.owner.login,
                            getBranches(username, repo.name)
                    ))
                    .collect(Collectors.toList());

        } catch (HttpClientErrorException.NotFound e) {
            throw new UserNotFoundException();
        }
    }

    private List<BranchDto> getBranches(String username, String repoName) {
        String branchUrl = "https://api.github.com/repos/" + username + "/" + repoName + "/branches";
        ResponseEntity<BranchResponse[]> response = restTemplate.getForEntity(branchUrl, BranchResponse[].class);

        if (response.getBody() == null) {
            return List.of();
        }

        return List.of(response.getBody()).stream()
                .map(branch -> new BranchDto(branch.name, branch.commit.sha))
                .collect(Collectors.toList());
    }
}
