# SPRINT 1: USER REGISTRATION (Backend)

**Epic:** EPIC-1: User Management  
**Sprint:** Sprint 1 10-01-2026  
**Focus:** Backend only

---

## USER STORY

**MH-1: User Registration**

As Anna (buyer)  
I want to create an account with email + password  
So that I can save listings and message sellers

**Story Points:** 8  
**Priority:** P1 (High)

---

## ACCEPTANCE CRITERIA

- AC1: POST /api/v1/auth/register endpoint works
- AC2: Email must be unique (409 if duplicate)
- AC3: Password stored as BCrypt hash
- AC4: Returns 201 + user data (no password!)
- AC5: Validation errors return 400
- AC6: Test coverage ≥ 80%

---

## TASKS

### MH-1.1: User Entity + Migration

**Est:** 1h  
**Subtasks:**

- Create V1\_\_create_users_table.sql
- Create User.java entity
- Test migration runs

**DoD:**

- Migration executes
- Table exists in DB
- Commit: `MH-1.1: feat(user): add User entity and migration`

---

### MH-1.2: DTOs

**Est:** 1h  
**Subtasks:**

- UserRegisterRequest.java
- UserResponse.java
- Validation annotations

**DoD:**

- DTOs created
- Validation works
- Commit: `MH-1.2: feat(user): add registration DTOs`

---

### MH-1.3: UserRepository

**Est:** 30min  
**Subtasks:**

- Create UserRepository interface
- Add existsByEmail()
- Add findByEmail()

**DoD:**

- Repository created
- Commit: `MH-1.3: feat(user): add UserRepository`

---

### MH-1.4: UserService + Tests (TDD!)

**Est:** 3h  
**Subtasks:**

- FIRST: Write 3 failing tests
- Implement UserService.register()
- BCrypt hashing
- All tests GREEN

**DoD:**

- 3+ tests pass
- BCrypt works
- Coverage ≥ 90%
- Commit: `MH-1.4: feat(user): add UserService with tests`

---

### MH-1.5: UserController + Tests

**Est:** 2h  
**Subtasks:**

- FIRST: Write integration tests
- Create UserController
- POST /api/v1/auth/register
- All tests GREEN

**DoD:**

- 4+ integration tests pass
- Endpoint works (201, 400, 409)
- Commit: `MH-1.5: feat(user): add registration endpoint`

---

### MH-1.6: Exception Handler

**Est:** 1h  
**Subtasks:**

- GlobalExceptionHandler.java
- ErrorResponse.java
- Handle all exceptions

**DoD:**

- Consistent error responses
- Commit: `MH-1.6: feat(common): add exception handler`

---

### MH-1.7: Security Config

**Est:** 30min  
**Subtasks:**

- Update SecurityConfig
- Permit /api/v1/auth/\*\*
- BCryptPasswordEncoder bean

**DoD:**

- Auth endpoints public
- Commit: `MH-1.7: feat(security): allow /auth endpoints`

---

### MH-1.8: Manual Test + Docs

**Est:** 1h  
**Subtasks:**

- Test with Postman
- Update Swagger
- Update README

**DoD:**

- Postman tests pass
- Docs updated
- Commit: `MH-1.8: docs(api): add registration docs`

---

## DEFINITION OF DONE

- [ ] All tasks MH-1.1 to MH-1.8 complete
- [ ] All tests GREEN (CI passing)
- [ ] Coverage ≥ 80%
- [ ] Postman tests pass
- [ ] Merged to develop
- [ ] Demo ready

---

## GIT COMMITS

```
MH-1.1: feat(user): add User entity and migration
MH-1.2: feat(user): add registration DTOs
MH-1.3: feat(user): add UserRepository
MH-1.4: feat(user): add UserService with tests
MH-1.5: feat(user): add registration endpoint
MH-1.6: feat(common): add exception handler
MH-1.7: feat(security): allow /auth endpoints
MH-1.8: docs(api): add registration docs
```
