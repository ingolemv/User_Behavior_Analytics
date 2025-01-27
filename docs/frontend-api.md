# UBA Frontend API Documentation

## Base URL
`https://uba.example.com/api`

## Authentication
Uses JWT stored in cookies after login

## Endpoints

### Authentication
- **POST /auth/login**
  - User login
  - Request Body:
    ```json
    {
      "username": "admin",
      "password": "password123"
    }
    ```
  - Response:
    ```json
    {
      "token": "jwt-token",
      "user": {
        "id": "user-id",
        "username": "admin",
        "role": "ADMIN"
      }
    }
    ```

### Dashboard
- **GET /dashboard/activities**
  - Get recent user activities
- **GET /dashboard/metrics**
  - Get system metrics

### Rules
- **GET /rules**
  - Get all rules
- **POST /rules**
  - Create new rule
- **PUT /rules/{id}**
  - Update rule
- **DELETE /rules/{id}**
  - Delete rule 