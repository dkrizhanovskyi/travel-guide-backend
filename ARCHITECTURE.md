### Project Architecture Description

The architecture of the Travel Guide Backend is based on a layered architecture, including the following main components:

## Main Components

1. **Controllers**:
    - Handle HTTP requests and return responses.
    - Key controllers: `DestinationController`, `UserController`, `RecommendationController`.

2. **Service Layer**:
    - Implements business logic.
    - Key services: `DestinationService`, `UserService`, `RecommendationService`.

3. **Data Access Layer**:
    - Handles database interactions via repositories.
    - Repositories: `DestinationRepository`, `UserRepository`, `RecommendationRepository`.

4. **Models/Entities**:
    - Define entities corresponding to database tables.
    - Key models: `Destination`, `User`, `Recommendation`.

5. **Configuration**:
    - Application settings and configuration files like `application.properties`.

## Architecture Diagram

```plaintext
┌──────────────────┐
│  Controllers     │
└────────┬─────────┘
         │
         ▼
┌──────────────────┐
│  Service Layer   │
└────────┬─────────┘
         │
         ▼
┌──────────────────┐
│Data Access Layer │
└────────┬─────────┘
         │
         ▼
┌──────────────────┐
│     Database     │
└──────────────────┘
```

## Component Descriptions

1. **Controllers**:
    - `DestinationController`: Handles requests related to travel destinations (CRUD operations).
    - `UserController`: Manages user-related requests (registration, authorization).
    - `RecommendationController`: Manages recommendation-related requests.

2. **Service Layer**:
    - `DestinationService`: Business logic for managing travel destinations.
    - `UserService`: Business logic for managing users.
    - `RecommendationService`: Business logic for generating recommendations.

3. **Data Access Layer**:
    - Repositories like `DestinationRepository`, `UserRepository`, `RecommendationRepository` use JPA for database interactions.

4. **Models**:
    - `Destination`: Represents a travel destination.
    - `User`: Represents a user.
    - `Recommendation`: Represents a recommendation for a user.

5. **Configuration**:
    - `application.properties`: Contains database settings, server parameters, and other configuration settings.

This structure ensures separation of concerns, making the project easier to maintain and extend.

