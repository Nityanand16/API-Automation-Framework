# API Automation Framework (Java + RestAssured + TestNG + Allure)

A scalable API automation framework built using **Java, RestAssured, TestNG, and Allure Reporting**, designed with clean architecture, reusable components, and CI/CD readiness.

---

## 🚀 Tech Stack

* Java 17
* RestAssured
* TestNG
* Maven
* Allure Report
* Logback (Logging)
* Jackson (JSON parsing)

---

## 📁 Project Structure

```
src
├── main
│   ├── java
│   │   ├── client          # API Client (REST wrapper)
│   │   ├── config         # Config Manager (env handling)
│   │   └── utils          # Utility classes
│
├── test
│   ├── java
│   │   ├── tests          # Test classes
│   │   └── listeners      # TestNG / Allure listeners
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
base.url=https://gorest.co.in/public/v2
token=YOUR_BEARER_TOKEN
```

---

## 📌 API Client Design

Centralized API handling using `ApiClient`:

* GET
* POST
* PUT
* DELETE

Features:

* Base URL injection
* Token authentication
* Content-Type handling
* Allure request/response logging

---

## 📊 Allure Reporting

Allure is integrated using:

```xml
io.qameta.allure:allure-rest-assured
io.qameta.allure:allure-testng
```

### Enable Allure logging in API Client:

```java
.filter(new AllureRestAssured())
```

---

## ▶️ How to Run Tests

### Run tests using Maven

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

If `allure` command is not recognized:

```bash
C:\Users\<username>\scoop\apps\allure\current\bin\allure.bat serve target\allure-results
```

---

## 🔥 CI/CD (GitHub Actions)

Example workflow:

```yaml
name: API Automation Tests

on:
  push:
    branches: [ "main" ]

jobs:
  test:
    runs-on: ubuntu-latest

    steps:
      - name: Checkout code
        uses: actions/checkout@v3

      - name: Set up JDK 17
        uses: actions/setup-java@v3
        with:
          distribution: 'temurin'
          java-version: '17'

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

* Separate test methods per API
* Setup & teardown for dynamic user creation
* Negative + positive test coverage
* Reusable API client

---

## 🧠 Best Practices Followed

* Centralized API client
* No hardcoded values
* Config-driven design
* Reusable utilities
* Clean separation of concerns
* Thread-safe design ready (future scaling)

---

## 📌 Future Improvements

* Parallel execution with ThreadLocal
* Dockerized test execution
* Allure history trend dashboard
* Contract testing (JSON schema validation)

---

## 📞 Author

Built for API automation learning and production-ready scaling.
