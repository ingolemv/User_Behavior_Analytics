# UBA Backend API Documentation

## Base URL
`https://api.uba.example.com/v1`

## Authentication
All endpoints require JWT authentication. Include the token in the Authorization header:
`Authorization: Bearer <token>`

## Endpoints

### User Activity
- **GET /activities**
  - Get all user activities
  - Query Params:
    - `userId`: Filter by user ID
    - `startDate`: Filter by start date (ISO format)
    - `endDate`: Filter by end date (ISO format)
  - Response:
    ```json
    [
      {
        "id": "activity-id",
        "userId": "user-id",
        "activityType": "LOGIN",
        "timestamp": "2023-01-01T00:00:00Z",
        "ipAddress": "192.168.1.1",
        "location": "New York, USA",
        "deviceInfo": "Chrome 120, Windows 10",
        "riskScore": 5.2,
        "isAnomaly": false
      }
    ]
    ```

### Anomaly Detection
- **POST /anomalies/detect**
  - Detect anomalies in real-time
  - Request Body:
    ```json
    {
      "activity": {
        "userId": "user-id",
        "activityType": "FILE_ACCESS",
        "ipAddress": "192.168.1.1",
        "location": "Unknown",
        "deviceInfo": "Unknown"
      }
    }
    ```
  - Response:
    ```json
    {
      "isAnomaly": true,
      "confidence": 0.92,
      "reason": "Unusual location detected"
    }
    ```

### Rules Management
- **GET /rules**
  - Get all security rules
- **POST /rules**
  - Create new rule
- **PUT /rules/{id}**
  - Update existing rule
- **DELETE /rules/{id}**
  - Delete rule 