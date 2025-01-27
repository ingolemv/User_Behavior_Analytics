# System Architecture Design

## Overview
The UBA (User Behavior Analytics) system is designed to monitor, analyze, and detect anomalous user activities in real-time. The system consists of three main components:

1. **Data Collection Layer**
   - Kafka for real-time event streaming
   - Elasticsearch for activity logging
   - PostgreSQL for structured data storage

2. **Processing Layer**
   - Rules Engine for pattern matching
   - LLM Integration for advanced anomaly detection
   - Risk Scoring Engine for threat assessment

3. **Presentation Layer**
   - React.js frontend for visualization
   - REST API for data access
   - Reporting module for insights

## Data Flow
1. User activities are captured and sent to Kafka
2. Activities are processed by the Rules Engine
3. Potential anomalies are analyzed by the LLM module
4. Results are stored in PostgreSQL and Elasticsearch
5. Frontend displays real-time data and trends 