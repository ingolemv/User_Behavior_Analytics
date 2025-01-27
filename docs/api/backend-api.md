# Backend API Documentation

## Anomaly Endpoints

### GET /api/anomalies
Returns a list of all detected anomalies

**Response:**
```json
[
    {
        "id": "123",
        "description": "Unusual login pattern detected",
        "severity": "HIGH",
        "timestamp": "2023-07-15T12:00:00Z"
    }
]
```

### POST /api/anomalies
Creates a new anomaly record

**Request Body:**
```json
{
    "description": "Unusual file access pattern",
    "severity": "MEDIUM"
}
```

**Response:**
```json
{
    "id": "124",
    "description": "Unusual file access pattern",
    "severity": "MEDIUM",
    "timestamp": "2023-07-15T12:05:00Z"
}
``` 