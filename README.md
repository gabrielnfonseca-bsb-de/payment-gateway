# Payment Gateway 🧾💳

This project is a backend microservice architecture to handle **bank slip (boleto) payment requests** and **notifications**. It is developed with **Java 21**, **Spring Boot 3.5**, **Kafka**, and **Avro**, and it follows modern backend patterns including Kafka consumers, topic-based communication, and Avro schema validation.

## 🚀 Features

- 📩 Processes boleto (bank slip) payment requests via Kafka topics  
- 📬 Listens to notifications for processing status updates  
- ✅ Uses Avro for message schema enforcement  
- 🧪 Built-in support for local development with Docker Compose  
- 🧱 Modular structure following clean architecture principles  

---

## 🛠️ Tech Stack

- **Java 21**
- **Spring Boot 3.5**
- **Apache Kafka**
- **Confluent Schema Registry**
- **Apache Avro**
- **Docker Compose**
- **Maven**

---

## 📂 Project Structure

payment-gateway/

│

├── api-bankslip/ # Handles bank slip business logic

├── payment-validator/ # Validates boleto requests

├── avro-schemas/ # Avro schemas for Kafka topics

├── docker-compose.yml # Kafka and Schema Registry setup

└── README.md # Project documentation

---

## 🔄 Kafka Topics Used

| Topic                 | Purpose                            |
|----------------------|------------------------------------|
| `solicitacao-boleto` | Receives boleto payment requests   |
| `notification-topic` | Processes notifications and status |

---

## ⚙️ Getting Started

### Prerequisites

- Docker and Docker Compose installed
- Java 21
- Maven 3.9+

### Step-by-step

```bash
# 1. Clone the project
git clone https://github.com/gabrielnfonseca-bsb-de/payment-gateway.git
cd payment-gateway

# 2. Start Kafka and Schema Registry
docker-compose up -d

# 3. Build and run the services
./mvnw clean install
cd api-bankslip
./mvnw spring-boot:run

🧪 Testing

Each module can be tested individually using:

Integration with Kafka can be tested using tools like Postman + Kafka REST Proxy or manually publishing to the topic using Kafka CLI.

📈 Future Improvements

    Add Swagger/OpenAPI for better API documentation

    Add support for retry mechanisms on Kafka consumers

    Implement a database to persist boleto records

    Implement monitoring with Prometheus + Grafana


