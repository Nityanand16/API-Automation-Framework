Got it — here is your **single complete README.md file (ready to copy as-is)** 👇

```md
# API Automation Framework (Java + RestAssured + TestNG + Allure)

A scalable and maintainable API automation framework built using **Java, RestAssured, TestNG, and Allure Reporting**, designed with clean architecture, reusable components, and CI/CD readiness.

---

## 🚀 Tech Stack

- Java 17
- RestAssured
- TestNG
- Maven
- Allure Report
- Logback (Logging)
- Jackson (JSON parsing)

---

## 📁 Project Structure

```

src
├── main
│   ├── java
│   │   ├── client          # API Client (REST wrapper)
│   │   ├── config          # Config Manager (env handling)
│   │   └── utils           # Utility classes
│
├── test
│   ├── java
│   │   ├── tests           # Test classes
│   │   └── listeners       # TestNG / Allure listeners
│   └── resources
│       └── testng.xml

```

---

## ⚙️ Configuration

Configuration is managed via:

```

src/main/resources/config.properties

```

Example:

```

base.url=[https://gorest.co.in/public/v2](https://gorest.co.in/public/v2)
token=YOUR_BEARER_TOKEN

````

---

## 📌 API Client Design

Centralized API handling using `ApiClient`.

Supports:
- GET
- POST
- PUT
- DELETE

### Key Features:
- Base URL injection
- Token-based authentication
- Centralized request handling
- Allure request/response logging

---

## 📊 Allure Reporting

Allure integration using:
- allure-rest-assured
- allure-testng

Enabled via:

```java
.filter(new AllureRestAssured())
````

---

## ▶️ How to Run Tests

```bash
mvn clean test
```

---

## 📊 Generate Allure Report

### Step 1: Run tests

```bash
mvn test
```

### Step 2: Generate report

```bash
allure generate target/allure-results -o target/allure-report --clean
```

### Step 3: Open report

```bash
allure open target/allure-report
```

---

## 🔥 CI/CD (GitHub Actions)

```yaml
name: API Automation Tests

on:
  push:
    branches: [ "main" ]

jobs:
  test:
    runs-on: ubuntu-latest

    steps:
      - uses: actions/checkout@v3

      - name: Set up JDK 17
        uses: actions/setup-java@v3
        with:
          distribution: temurin
          java-version: 17

      - name: Run tests
        run: mvn clean test

      - name: Upload Allure Results
        uses: actions/upload-artifact@v4
        with:
          name: allure-results
          path: target/allure-results
```

---

## 🧪 Test Strategy

* Independent test cases per API endpoint
* Setup & teardown for test data management
* Positive + negative test coverage
* Retry mechanism for flaky API behavior
* Thread-safe execution ready (ThreadLocal support)

---

## 🧠 Best Practices Followed

* Centralized API client
* No hardcoded values
* Config-driven architecture
* Reusable utilities
* Clean separation of concerns
* Scalable test design
* Parallel execution readiness

---

## 📌 Future Improvements

* Full parallel execution stability tuning
* Dockerized test execution
* Allure history dashboard (trend tracking)
* JSON schema validation (contract testing)

---

## 📞 Author

Built for learning and production-grade API automation framework design.

```