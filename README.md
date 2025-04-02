# GitHub API Wrapper

A Spring Boot application that provides a simplified API for retrieving GitHub user repositories and their branches.

## Features

- Fetch non-forked repositories for a given GitHub username
- Retrieve branch information for each repository
- Error handling for non-existent users


## API Endpoints

### Get User Repositories

```
GET /api/github/{username}
```

Returns a list of repositories for the specified GitHub username, excluding forks.

#### Success Response

```json
[
  {
    "name": "repository-name",
    "ownerLogin": "username",
    "branches": [
      {
        "name": "branch-name",
        "lastCommitSha": "commit-sha"
      }
    ]
  }
]
```

#### Error Response (User Not Found)

```json
{
  "status": 404,
  "message": "User not found"
}
```

## Technologies Used

- Java 21
- Spring Boot
- Spring Web
- RestTemplate for HTTP requests


## Getting Started

### Prerequisites

- Java 17 or higher
- Maven

### Installation

1. Clone the repository:
```bash
git clone https://github.com/yourusername/ghApi.git
cd ghApi
```

2. Build the project:
```bash
mvn clean install
```

3. Run the application:
```bash
mvn spring-boot:run
```

The application will start on `http://localhost:8080`.

## Testing

Run the tests with:

```bash
mvn test
```

## Project Structure

- `GhApiApplication.java` - Main application class
- `GhApiController.java` - REST controller for handling API requests
- `GhApiService.java` - Service layer for GitHub API integration
- Model classes:
  - `RepositoryDto.java` - Data transfer object for repository information
  - `BranchDto.java` - Data transfer object for branch information
  - `RepositoryResponse.java` - Response model for GitHub API repository data
  - `BranchResponse.java` - Response model for GitHub API branch data

## License

This project is licensed under the MIT License - see the LICENSE file for details.