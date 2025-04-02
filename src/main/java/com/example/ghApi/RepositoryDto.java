package com.example.ghApi;

import java.util.List;

record RepositoryDto(String name, String ownerLogin, List<BranchDto> branches) {
}
