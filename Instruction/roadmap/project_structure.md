# UBA Application Project Structure

## Backend (Java - Spring Boot)
```plaintext
uba-backend/
├── src/
│   ├── main/
│   │   ├── java/com/uba/
│   │   │   ├── config/
│   │   │   │   └── KafkaConfig.java
│   │   │   ├── controller/
│   │   │   │   ├── AnomalyController.java
│   │   │   │   ├── RuleController.java
│   │   │   │   └── ReportController.java
│   │   │   ├── service/
│   │   │   │   ├── AnomalyService.java
│   │   │   │   ├── RuleService.java
│   │   │   │   └── ReportService.java
│   │   │   ├── repository/
│   │   │   │   ├── AnomalyRepository.java
│   │   │   │   └── RuleRepository.java
│   │   │   ├── model/
│   │   │   │   ├── Anomaly.java
│   │   │   │   ├── Rule.java
│   │   │   │   └── UserActivity.java
│   │   │   ├── engine/
│   │   │   │   ├── RulesEngine.java
│   │   │   │   └── RiskScoringEngine.java
│   │   │   ├── anomaly/
│   │   │   │   ├── AnomalyDetector.java
│   │   │   │   └── LLMAnomalyDetection.java
│   │   │   └── reporting/
│   │   │       ├── ReportGenerator.java
│   │   │       └── ReportExporter.java
│   │   ├── resources/
│   │   │   ├── application.yml
│   │   │   └── rules/
│   │   │       └── predefined_rules.json
│   ├── test/
│   │   ├── java/com/uba/
│   │   │   ├── controller/
│   │   │   ├── service/
│   │   │   └── engine/
├── pom.xml
├── Dockerfile
└── README.md
```

## Frontend (React.js)
```plaintext
uba-frontend/
├── public/
│   └── index.html
├── src/
│   ├── components/
│   │   ├── Dashboard/
│   │   │   ├── RealTimeView.js
│   │   │   └── TrendsView.js
│   │   └── Rules/
│   │       ├── RuleCreation.js
│   │       └── RuleList.js
│   ├── services/
│   │   └── api.js
│   ├── utils/
│   │   └── helpers.js
│   ├── App.js
│   ├── index.js
│   └── styles.css
├── package.json
├── Dockerfile
└── README.md
```

## LLM Integration
```plaintext
uba-llm/
├── src/
│   ├── llm/
│   │   ├── fine_tune.py
│   │   ├── anomaly_detection.py
│   │   └── utils.py
├── requirements.txt
├── Dockerfile
└── README.md
```

## Infrastructure
```plaintext
infra/
├── kafka/
│   └── docker-compose.yml
├── elasticsearch/
│   └── docker-compose.yml
├── postgresql/
│   └── docker-compose.yml
└── monitoring/
    ├── prometheus.yml
    └── grafana/
        └── dashboards/
```

## CI/CD
```plaintext
.github/
├── workflows/
│   ├── backend-ci.yml
│   ├── frontend-ci.yml
│   └── deploy.yml
```

## Documentation
```plaintext
docs/
├── api/
│   ├── backend-api.md
│   └── frontend-api.md
├── architecture/
│   └── system-design.md
└── user-guide.md
```

## Root Directory
```plaintext
uba-app/
├── uba-backend/
├── uba-frontend/
├── uba-llm/
├── infra/
├── .github/
├── docs/
├── docker-compose.yml
└── README.md
```

## Key Configuration Files

### Backend - application.yml
```yaml
server:
  port: 8080

spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/uba
    username: postgres
    password: password
  jpa:
    hibernate:
      ddl-auto: update
  kafka:
    bootstrap-servers: localhost:9092

elasticsearch:
  host: localhost
  port: 9200
```

### Frontend - package.json
```json
{
  "name": "uba-frontend",
  "version": "1.0.0",
  "dependencies": {
    "react": "^18.2.0",
    "react-dom": "^18.2.0",
    "axios": "^1.3.4",
    "chart.js": "^4.2.1",
    "react-router-dom": "^6.8.1"
  }
}
```

### LLM - requirements.txt
```text
openai
pandas
numpy
scikit-learn
fastapi
uvicorn
```

### Root - docker-compose.yml
```yaml
version: '3.8'

services:
  backend:
    build: ./uba-backend
    ports:
      - "8080:8080"
    depends_on:
      - kafka
      - postgres
      - elasticsearch

  frontend:
    build: ./uba-frontend
    ports:
      - "3000:3000"
    depends_on:
      - backend

  llm:
    build: ./uba-llm
    ports:
      - "8000:8000"
    depends_on:
      - backend

  kafka:
    image: bitnami/kafka:latest
    ports:
      - "9092:9092"
    environment:
      KAFKA_ADVERTISED_LISTENERS: PLAINTEXT://localhost:9092

  postgres:
    image: postgres:latest
    ports:
      - "5432:5432"
    environment:
      POSTGRES_PASSWORD: password

  elasticsearch:
    image: docker.elastic.co/elasticsearch/elasticsearch:8.6.2
    ports:
      - "9200:9200"
    environment:
      discovery.type: single-node
```

This setup provides a complete structure for the UBA application as described in the roadmap. Each component is properly organized and includes the necessary configuration files for development and deployment. 