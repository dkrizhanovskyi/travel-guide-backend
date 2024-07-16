# Travel Guide Backend

## Overview

The Travel Guide Backend is a Java-based application designed to provide backend support for a travel guide application. It offers APIs for managing travel destinations, user recommendations, and related services.

## Table of Contents

- [Features](#features)
- [Installation](#installation)
- [Usage](#usage)
- [API Endpoints](#api-endpoints)
- [Configuration](#configuration)
- [Contributing](#contributing)
- [License](#license)

## Features

- CRUD operations for travel destinations
- User recommendations based on preferences
- Authentication and authorization
- Error handling and logging

## Installation

### Prerequisites

- Java 11 or higher
- Maven
- PostgreSQL (or your preferred database)

### Steps

1. Clone the repository:
    ```bash
    git clone https://github.com/dkrizhanovskyi/travel-guide-backend.git
    cd travel-guide-backend
    ```

2. Set up the database:
    - Create a database named `travelguide`.
    - Update the database configuration in `application.properties`.

3. Build the project:
    ```bash
    mvn clean install
    ```

4. Run the application:
    ```bash
    mvn spring-boot:run
    ```

## Usage

The application runs on `http://localhost:8080` by default. You can access the API documentation at `http://localhost:8080/swagger-ui.html`.

## API Endpoints

Here are some of the primary API endpoints:

- `GET /api/destinations` - Retrieve all travel destinations
- `POST /api/destinations` - Add a new travel destination
- `GET /api/destinations/{id}` - Retrieve a specific travel destination by ID
- `PUT /api/destinations/{id}` - Update a specific travel destination by ID
- `DELETE /api/destinations/{id}` - Delete a specific travel destination by ID

For detailed API documentation, refer to the Swagger UI.

## Configuration

Configuration settings are managed in `application.properties`. Key configurations include:

- Database settings
- Server port
- Logging levels
- Authentication settings

## Contributing

Contributions are welcome! Please follow these steps:

1. Fork the repository.
2. Create a new branch (`git checkout -b feature-xyz`).
3. Make your changes.
4. Commit your changes (`git commit -m 'Add feature xyz'`).
5. Push to the branch (`git push origin feature-xyz`).
6. Open a pull request.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

