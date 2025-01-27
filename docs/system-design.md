# UBA System Design

## Architecture Overview
The UBA system follows a microservices architecture with the following components:

1. **Frontend**: React-based web application
2. **Backend**: Spring Boot application
3. **Database**: PostgreSQL for persistent storage
4. **LLM Service**: Python-based anomaly detection
5. **Message Queue**: Kafka for event streaming
6. **Monitoring**: Prometheus + Grafana

## Components

### Frontend
- React 18
- React Router for navigation
- Axios for API calls
- Chart.js for data visualization

### Backend
- Spring Boot 3
- Spring Security for authentication
- Spring Data JPA for database access
- Kafka integration for event streaming
- Actuator for monitoring

### Database
- PostgreSQL 15
- Tables:
  - Users
  - Activities
  - Rules
  - Anomalies

### LLM Service
- Python 3.9
- Hugging Face Transformers
- Fine-tuned GPT model for anomaly detection

## Data Flow
1. User activity → Kafka → Backend → Database
2. Backend → LLM Service → Anomaly Detection
3. Frontend ↔ Backend API 