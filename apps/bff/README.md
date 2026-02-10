# FoodRetailor BFF – Backend for Frontend

This project is a **Backend for Frontend (BFF)** service designed to sit between a mobile application and internal backend services.  
Its primary responsibility is **authentication, token exchange, orchestration, and aggregation of data** into a single response optimized for frontend consumption.

The implementation follows **Hexagonal (Ports & Adapters) Architecture**, ensuring that business logic is independent of frameworks, transport protocols, and infrastructure.

---

## Architecture Overview

### Hexagonal Architecture (Ports & Adapters)

The system is structured around a clean core:

```
┌──────────────────────────────┐
│     Inbound Adapters         │  (Ktor / http4k)
│                              │
│   - Routing                  │
│   - DTO mapping              │
│   - HTTP concerns            │
└──────────────┬───────────────┘
               │
            UseCase
               │
┌──────────────▼───────────────┐
│     Application Core         │ (Pure kotlin)
│                              │
│   - Use cases                │
│   - Domain models            │
│   - Business orchestration   │
└──────────────▲───────────────┘
               │
             Port
               |
┌──────────────┴───────────────┐
│     Outbound Adapters        │ (Rest / Soap / DB)
│                              │
│   - Token exchange           │
│   - Store / Membership /     │
│     Reward services          │
│   - Mocked implementations   │
└──────────────────────────────┘
```

### Key Principles

- **Use cases are the core of the system**
- Frameworks (Ktor, http4k), serialization mechanisms, and security concerns are treated strictly as implementation details.
  The application core remains framework-agnostic, allowing these components to be freely interchanged or replaced without affecting business behavior.
- Business logic never depends on:
    - HTTP
    - JSON
    - JWT libraries
    - Web frameworks
- Dependencies always point **inward**

As a general best practice, each external system is represented by exactly one outbound adapter.

```
Use case
   ↓
Port (interface)
   ↓
Adapter (implementation detail)
   ↓
External system
```

--- 

## Core Domain

The core application exposes use cases:

[`GetHomePageDataUseCase`](applications/home-page-application/src/main/kotlin/com/foodretailor/bff/applications/homepage/usecase/api/GetHomePageDataUseCase.kt)


### Responsibilities

1. Accept an **external JWT** from the client
2. Exchange it for an **internal service token**
3. Call multiple downstream services **in parallel**
4. Aggregate results into a single response

```kotlin
Output(
    store: Store,
    membership: Membership,
    reward: Reward
)
```

### Concurrency

Downstream calls are executed using **structured concurrency with Kotlin coroutines**:

- Parallel execution via `async`
- Fail-fast behavior
- Proper cancellation propagation
- No framework-specific threading assumptions

---

## Security Model

### OAuth2 / JWT (Mocked)

- The mobile app sends a JWT in the `Authorization: Bearer` header
- The backend acts as an **OAuth2 Resource Server**
- JWT validation is implemented using **Ktor Authentication**
- For this assignment:
    - JWT verification is mocked
    - No real issuer / JWK integration is required

---

## Testing Strategy

### Unit Tests (Core)

- Located in the application module
- Test **use cases in isolation**
- Use pure Kotlin fakes
- Cover happy path, token exchange correctness, and failure propagation

### Integration Tests (Web)

- Located in the configuration module
- Use `ktor-server-test-host`
- Test routing, authentication, and HTTP status codes

---

## Running the Application Locally

### Prerequisites

- **JDK 25**
- Gradle (wrapper included)

### Start the server

```bash
./gradlew :configurations:ktor-configuration:run
```

The server starts on:

```
http://localhost:8080
```

---

## Summary

Everything outside the **use cases** is treated as an **implementation detail**.
