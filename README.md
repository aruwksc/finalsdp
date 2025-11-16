# 🎓 School Management System - Design Patterns Project

A Spring Boot application demonstrating 6 design patterns with interactive web interface built for Software Design Patterns course.

## 📋 Table of Contents

- [Project Overview](#project-overview)
- [Technologies Used](#technologies-used)
- [Design Patterns Explained](#design-patterns-explained)
- [Prerequisites](#prerequisites)
- [How to Run](#how-to-run)
- [Using the Interface](#using-the-interface)
- [API Endpoints](#api-endpoints)
- [Testing the API](#testing-the-api)
- [What I Learned](#what-i-learned)


---

## 🎯 Project Overview

This is our final project for Software Design Patterns course where We implemented a school management system demonstrating how 6 different design patterns work together in a real application. The system includes a backend REST API built with Spring Boot and a modern interactive frontend.

**What it does:**
- Manages students, teachers, and staff
- Handles grade notifications
- Calculates final grades using different strategies
- Dynamically assigns user roles and permissions
- Creates school objects through factory methods

---

## 💻 Technologies Used

- **Backend:** Java 17, Spring Boot 3.2.0
- **API:** REST with JSON
- **Frontend:** HTML5, CSS3, JavaScript
- **Build Tool:** Maven
- **Server Port:** 8080
- **Architecture:** MVC with Design Patterns

---

## 🎨 Design Patterns Explained

### 1. 🔔 Observer Pattern
**Location:** `observer/` package

The Observer pattern creates a notification system where objects can subscribe to events and automatically get notified when something changes.

**Classes & Their Roles:**

| Class | Type | What It Does |
|-------|------|--------------|
| `Observer` | Interface | Defines `update()` method that all observers must implement |
| `Subject` | Interface | Manages observers list, provides attach/detach/notify methods |
| `Student` | Concrete Observer | Implements `Observer`, receives notifications about their own grades |
| `Parent` | Concrete Observer | Implements `Observer`, receives notifications about child's grades |
| `GradeSystemService` | Concrete Subject | Maintains observer list, notifies them when grades are added |

**How it works:**
1. Students and parents subscribe to grade notifications through `attach()`
2. When teacher adds a grade, `GradeSystemService.notifyObservers()` is called
3. All subscribed observers receive `update()` call with grade information
4. Each observer processes notification based on their type

**Real-world example:** Like subscribing to YouTube channel - when new video uploads, all subscribers get notified.

**API Endpoints:**
- `POST /api/grades/subscribe` - Subscribe observer to notifications
- `POST /api/grades/add` - Add grade and trigger notifications
- `GET /api/grades/{studentId}` - Get student's grades

---

### 2. 🎭 Decorator Pattern
**Location:** `decorator/` package

The Decorator pattern allows adding new functionality to objects dynamically by wrapping them in decorator objects. We used it to add roles and permissions to users without modifying their base class.

**Classes & Their Roles:**

| Class | Type | What It Does |
|-------|------|--------------|
| `User` | Abstract Component | Base class with getName(), getUserId(), getDescription(), getPermissions() |
| `BasicUser` | Concrete Component | Simple user with only basic permissions (view_profile, edit_own_profile) |
| `UserDecorator` | Abstract Decorator | Extends User, wraps another User object, delegates calls to wrapped user |
| `TeacherRole` | Concrete Decorator | Adds teaching permissions: add_grades, view_all_students, create_assignments |
| `AdminRole` | Concrete Decorator | Adds admin permissions: manage_users, manage_system, view_all_data |
| `GroupCuratorRole` | Concrete Decorator | Adds curator permissions: view_group_students, send_group_notifications, manage_attendance |
| `UserService` | Service | Creates decorated users by wrapping BasicUser with selected role decorators |

**How it works:**
1. Start with `BasicUser` (has basic permissions)
2. Wrap it with `TeacherRole` decorator (adds teaching permissions)
3. Wrap result with `AdminRole` decorator (adds admin permissions)
4. Final object has permissions from all layers: BasicUser + Teacher + Admin
5. Each decorator's `getDescription()` adds its role to the description chain

**Real-world example:** Like adding toppings to pizza - base pizza + cheese + pepperoni + mushrooms, each addition enhances the base.

**API Endpoints:**
- `POST /api/users/create` - Create user with multiple roles
- `GET /api/users/{userId}` - Get user with all permissions

---

### 3. 📊 Strategy Pattern
**Location:** `strategy/` package

The Strategy pattern defines a family of algorithms, encapsulates each one, and makes them interchangeable. We used it for different grade calculation methods.

**Classes & Their Roles:**

| Class | Type | What It Does |
|-------|------|--------------|
| `GradeCalculationStrategy` | Interface | Defines `calculate(Map<String, List<Double>> grades)` method |
| `ExamOnlyStrategy` | Concrete Strategy | Calculates final grade using only exam scores (100%) |
| `HomeworkIncludedStrategy` | Concrete Strategy | Weighs exams 70% and homework 30% |
| `ComprehensiveStrategy` | Concrete Strategy | Weighs exams 50%, homework 25%, projects 25% |
| `GradeCalculatorService` | Context | Holds strategy reference, delegates calculation to chosen strategy |

**How it works:**
1. User selects calculation strategy (exam/homework/comprehensive)
2. `GradeCalculatorService` sets appropriate strategy object
3. When `calculate()` is called, it delegates to strategy's algorithm
4. Each strategy uses different formula to compute final grade
5. Can switch strategies at runtime without changing client code

**Calculation formulas:**
- **ExamOnly:** Average of all exam grades
- **HomeworkIncluded:** (Avg_Exams × 0.7) + (Avg_Homework × 0.3)
- **Comprehensive:** (Avg_Exams × 0.5) + (Avg_Homework × 0.25) + (Avg_Projects × 0.25)

**Real-world example:** Like choosing payment method - credit card, PayPal, or cash. Same action (payment) but different implementation.

**API Endpoint:**
- `POST /api/grades/calculate?strategy=<type>` - Calculate using selected strategy

---

### 4. 🏭 Factory Pattern
**Location:** `factory/` package (implemented in FactoryController)

The Factory pattern provides an interface for creating objects without specifying their exact classes. The factory decides which class to instantiate based on input parameters.

**Classes & Their Roles:**

| Class | Type | What It Does |
|-------|------|--------------|
| `SchoolMember` | Interface/Abstract | Common interface for all school members |
| `Student` | Concrete Product | Represents student with student-specific properties |
| `Teacher` | Concrete Product | Represents teacher with teaching-specific properties |
| `StaffMember` | Concrete Product | Represents staff with administrative properties |
| `FactoryController` | Factory | Contains creation logic, returns appropriate object type |

**How it works:**
1. Client sends creation request with type parameter ("student", "teacher", "staff")
2. Factory method checks the type parameter
3. Based on type, instantiates appropriate class
4. Returns object as common interface type
5. Client uses object without knowing its concrete class

**Real-world example:** Like ordering from restaurant menu - you order "burger" and kitchen decides whether to make beef burger, chicken burger, or veggie burger based on availability.

**API Endpoint:**
- `POST /api/factory/create` - Create school member via factory

**Request Example:**
```json
{
  "type": "student",
  "name": "John Smith",
  "id": "S001"
}
```

---

### 5. 🏗️ Abstract Factory Pattern
**Location:** `abstractFactory/` package (implemented in AbstractFactoryController)

Abstract Factory creates families of related objects without specifying their concrete classes. We used it to create complete department structures with their associated staff members.

**Classes & Their Roles:**

| Class | Type | What It Does |
|-------|------|--------------|
| `DepartmentFactory` | Abstract Factory | Interface for creating department families |
| `AcademicDepartmentFactory` | Concrete Factory | Creates academic department + professors + researchers |
| `AdministrativeDepartmentFactory` | Concrete Factory | Creates admin department + managers + secretaries |
| `Department` | Abstract Product | Interface for department objects |
| `DepartmentStaff` | Abstract Product | Interface for staff objects |

**How it works:**
1. Client requests department creation with type ("academic" or "administrative")
2. Appropriate factory is selected (Academic or Administrative)
3. Factory creates entire family of related objects:
    - Academic: Department + Professors + Researchers
    - Administrative: Department + Managers + Secretaries
4. All objects in family are guaranteed to work together
5. Ensures consistency - can't mix academic staff with admin department

**Difference from Factory Pattern:**
- **Factory:** Creates single object
- **Abstract Factory:** Creates families of related objects

**Real-world example:** Like furniture sets - if you choose "Modern" style, you get modern sofa + modern table + modern chairs. Everything matches.

**API Endpoint:**
- `POST /api/abstract-factory/create` - Create department family

**Request Example:**
```json
{
  "departmentType": "academic",
  "name": "Mathematics Department",
  "id": "DEPT001"
}
```

---

### 6. 🌉 Bridge Pattern
**Location:** `bridge/` package (implemented in BridgeController)

The Bridge pattern separates abstraction from implementation, allowing them to vary independently. We used it to separate user types from the actions they can perform.

**Classes & Their Roles:**

| Class | Type | What It Does |
|-------|------|--------------|
| `UserAction` | Implementation Interface | Defines `execute(String targetId)` method |
| `ViewGradesAction` | Concrete Implementation | Implements viewing grades logic |
| `EditGradesAction` | Concrete Implementation | Implements editing grades logic |
| `GenerateReportAction` | Concrete Implementation | Implements report generation logic |
| `ManageUsersAction` | Concrete Implementation | Implements user management logic |
| `UserType` | Abstraction | Contains reference to UserAction, has `performAction()` |
| `StudentUser` | Refined Abstraction | Student-specific behavior and permissions |
| `TeacherUser` | Refined Abstraction | Teacher-specific behavior and permissions |
| `AdminUser` | Refined Abstraction | Admin-specific behavior and permissions |

**How it works:**
1. User type (abstraction) and action (implementation) are separate hierarchies
2. User type holds reference to action object
3. When `performAction()` is called on user type, it delegates to action's `execute()`
4. Can add new user types without changing actions
5. Can add new actions without changing user types
6. Any user type can potentially perform any action (with permission check)

**Why it's useful:**
- Without Bridge: 3 user types × 4 actions = 12 classes needed
- With Bridge: 3 user types + 4 actions = 7 classes needed

**Real-world example:** Like remote control (abstraction) and TV/Radio/AC (implementation). Same remote can control different devices, new devices can be added without changing remote.

**API Endpoint:**
- `POST /api/bridge/execute` - Execute action through bridge

**Request Example:**
```json
{
  "userType": "teacher",
  "action": "editGrades",
  "targetId": "S001"
}
```
---

## 🔧 Prerequisites

Make sure you have these installed before running the project:

### Required:
- **Java 17 or higher**
  ```bash
  java -version
  # Should show: java version "17.x.x" or higher
  ```

- **Maven 3.6+**
  ```bash
  mvn -version
  # Should show: Apache Maven 3.6.x or higher
  ```

### Optional:
- **Git** (for cloning repository)
- **Postman or cURL** (for API testing)
- **Modern Web Browser** (Chrome, Firefox, Edge recommended)

---

## 🚀 How to Run

### Step 1: Clone/Download Project

```bash
# Using Git
git clone https://github.com/aruwksc/finalsdp.git
cd finalsdp

# Or download ZIP and extract
```

### Step 2: Build the Project

```bash
# This installs dependencies and compiles code
mvn clean install
```

### Step 3: Run the Application

**Option A: Using Maven (easier for development)**
```bash
mvn spring-boot:run
```

**Option B: Using JAR file (like production)**
```bash
# First build the JAR
mvn clean package

# Then run it
java -jar target/finalsdp-1.0.0.jar
```

### Step 4: Verify It's Running

You should see output like:
```
==============================================
🎓 School Management System Started!
==============================================
📡 API: http://localhost:8080/api
🌐 UI:  http://localhost:8080/index.html
💚 Health: http://localhost:8080/api/health
==============================================
```

### Step 5: Open the Interface

Open your browser and go to:
```
http://localhost:8080/index.html
```

You should see a dark-themed interface with 6 pattern cards! ✨

---

## 🖥️ Using the Interface

The UI has 6 interactive cards. Click any card to open a modal with forms to test that pattern.

### 1. Observer Pattern Testing 🔔

**Step 1: Subscribe to Notifications**
- Select type: Student or Parent
- Enter name: "John Doe"
- Enter ID: "S001"
- If parent, enter child's name
- Click "Subscribe to Notifications"

**Step 2: Add Grade (This triggers notifications!)**
- Enter student ID: "S001" (same as subscriber)
- Enter subject: "Mathematics"
- Enter grade: 4.5 (scale 0-5)
- Select type: Exam/Homework/Project/Test
- Click "Add Grade"
- ✅ All subscribed observers will receive notification!

---

### 2. Decorator Pattern Testing 🎭

**Create User with Multiple Roles**
- Enter name: "Anna Smith"
- Enter user ID: "U001"
- Check roles (can select multiple!):
    - ☑️ Teacher
    - ☑️ Administrator
    - ☑️ Group Curator (shows group name field)
- If curator selected, enter group: "Group 10-A"
- Click "Create User with Roles"
- ✅ See combined permissions from all roles!

**Example Result:**
```
Description: User: Anna Smith + Role: Teacher + Role: Administrator
Permissions: view_profile, edit_own_profile, add_grades, view_all_students, 
             create_assignments, manage_users, manage_system, view_all_data
```

---

### 3. Strategy Pattern Testing 📊

**Calculate Final Grade**
- Select strategy:
    - 🎯 Exams Only (100% exams)
    - 📚 Exams + Homework (70% + 30%)
    - ⭐ Comprehensive (50% + 25% + 25%)
- Enter grades (comma-separated):
    - Exams: `4.5, 5.0, 4.0`
    - Homework: `4.0, 4.5, 5.0`
    - Projects: `5.0, 4.5`
- Click "Calculate Final Grade"
- ✅ See final grade calculated with selected formula!

**Example:**
- Exams average: (4.5 + 5.0 + 4.0) / 3 = 4.5
- Homework average: (4.0 + 4.5 + 5.0) / 3 = 4.5
- Projects average: (5.0 + 4.5) / 2 = 4.75
- **Comprehensive Result:** (4.5 × 0.5) + (4.5 × 0.25) + (4.75 × 0.25) = **4.5625**

---

### 4. Factory Pattern Testing 🏭

**Create School Member**
- Select type:
    - 👨‍🎓 Student
    - 👨‍🏫 Teacher
    - ⚙️ Staff Member
- Enter name: "John Smith"
- Enter ID: "F001"
- Click "Create via Factory"
- ✅ Object created with type-specific properties!

---

### 5. Abstract Factory Testing 🏗️

**Create Department Family**
- Select department type:
    - 📚 Academic Department (creates professors, researchers)
    - ⚙️ Administrative Department (creates managers, secretaries)
- Enter department name: "Mathematics Department"
- Enter department ID: "DEPT001"
- Click "Create Department Family"
- ✅ Complete department structure created!

---

### 6. Bridge Pattern Testing 🌉

**Execute User Action**
- Select user type:
    - 👨‍🎓 Student (limited permissions)
    - 👨‍🏫 Teacher (can edit grades)
    - ⚙️ Administrator (full access)
- Select action:
    - 📊 View Grades
    - ✏️ Edit Grades
    - 📄 Generate Report
    - 👥 Manage Users
- Enter target ID: "S001"
- Click "Execute Action via Bridge"
- ✅ Action executed with permission check!

---

## 📡 API Endpoints

### Health Check
```http
GET /api/health
```

**Response:**
```json
{
  "status": "UP",
  "message": "School Management System API is running",
  "patterns": ["Observer", "Decorator", "Strategy", "Factory", "Abstract Factory", "Bridge"]
}
```

---

### Observer Pattern

**Subscribe Observer**
```http
POST /api/grades/subscribe
Content-Type: application/json

{
  "type": "student",
  "name": "John Doe",
  "id": "S001",
  "childName": null
}
```

**Add Grade (Notifies Observers)**
```http
POST /api/grades/add
Content-Type: application/json

{
  "studentId": "S001",
  "subject": "Mathematics",
  "grade": 4.5,
  "gradeType": "Exam"
}
```

---

### Decorator Pattern

**Create User with Roles**
```http
POST /api/users/create
Content-Type: application/json

{
  "name": "Anna Smith",
  "userId": "U001",
  "roles": ["teacher", "admin"],
  "groupName": null
}
```

**Get User**
```http
GET /api/users/{userId}
```

---

### Strategy Pattern

**Calculate Grade**
```http
POST /api/grades/calculate?strategy=comprehensive
Content-Type: application/json

{
  "grades": {
    "exam": [4.5, 5.0, 4.0],
    "homework": [4.0, 4.5, 5.0],
    "project": [5.0, 4.5]
  }
}
```

**Response:**
```json
{
  "finalGrade": 4.5625,
  "strategy": "Comprehensive (Exams 50%, Homework 25%, Projects 25%)"
}
```

---

### Factory Pattern

**Create Object**
```http
POST /api/factory/create
Content-Type: application/json

{
  "type": "student",
  "name": "John Smith",
  "id": "F001"
}
```

---

### Abstract Factory Pattern

**Create Department Family**
```http
POST /api/abstract-factory/create
Content-Type: application/json

{
  "departmentType": "academic",
  "name": "Mathematics Department",
  "id": "DEPT001"
}
```

---

### Bridge Pattern

**Execute Action**
```http
POST /api/bridge/execute
Content-Type: application/json

{
  "userType": "teacher",
  "action": "editGrades",
  "targetId": "S001"
}
```

---

## 🧪 Testing the API

### Using cURL (Command Line)

**Test Health:**
```bash
curl http://localhost:8080/api/health
```

**Subscribe Observer:**
```bash
curl -X POST http://localhost:8080/api/grades/subscribe \
  -H "Content-Type: application/json" \
  -d '{"type":"student","name":"John","id":"S001"}'
```

**Add Grade:**
```bash
curl -X POST http://localhost:8080/api/grades/add \
  -H "Content-Type: application/json" \
  -d '{"studentId":"S001","subject":"Math","grade":4.5,"gradeType":"Exam"}'
```

**Create User:**
```bash
curl -X POST http://localhost:8080/api/users/create \
  -H "Content-Type: application/json" \
  -d '{"name":"Anna","userId":"U001","roles":["teacher","admin"]}'
```

**Calculate Grade:**
```bash
curl -X POST "http://localhost:8080/api/grades/calculate?strategy=comprehensive" \
  -H "Content-Type: application/json" \
  -d '{"grades":{"exam":[4.5,5.0],"homework":[4.0],"project":[5.0]}}'
```

---

## 📚 What I Learned

Working on this project taught me:

### Technical Skills:
- ✅ How to implement classic design patterns in real code
- ✅ When to use each pattern and why
- ✅ Building REST APIs with Spring Boot
- ✅ Connecting frontend and backend
- ✅ CORS configuration for cross-origin requests
- ✅ JSON data handling and validation

### Design Patterns Understanding:
- **Observer:** Perfect for event-driven systems and notifications
- **Decorator:** Great for adding features without modifying existing code
- **Strategy:** Useful when you need different algorithms for same task
- **Factory:** Simplifies object creation and hides complexity
- **Abstract Factory:** Ensures related objects work together
- **Bridge:** Separates "what" from "how" for flexibility

### Software Engineering:
- How patterns make code more maintainable
- Importance of separation of concerns
- How to structure larger projects
- Writing clean, readable code
- API design best practices

---

## 🐛 Challenges & Solutions

### Challenge 1: Understanding Abstract Factory vs Factory
**Problem:** I was confused about the difference at first.  
**Solution:** Realized Factory creates one object, Abstract Factory creates families of related objects. Like Factory = build one car, Abstract Factory = build entire car family (sedan, SUV, truck that all match).

### Challenge 2: CORS Errors
**Problem:** Frontend couldn't connect to backend - got CORS errors in browser console.  
**Solution:** Created `WebConfig.java` with `@CrossOrigin` configuration to allow requests from frontend.

```java
@Configuration
public class WebConfig implements WebCorsRegistry {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE");
    }
}
```

### Challenge 3: Decorator Pattern - Permission Accumulation
**Problem:** Wasn't sure how to combine permissions from multiple decorators.  
**Solution:** Each decorator calls `super.getPermissions()` first, then adds its own permissions to the list. This way permissions accumulate through the decorator chain.

### Challenge 4: Strategy Pattern - Grade Calculation Logic
**Problem:** Getting the math right for weighted averages.  
**Solution:** Broke it down step by step:
1. Calculate average for each category
2. Multiply by weight
3. Sum all weighted averages
4. Added comments in code to explain formula

### Challenge 5: Observer Pattern - Managing Multiple Observers
**Problem:** How to store and notify multiple different observer types.  
**Solution:** Used a `List<Observer>` in Subject, all observers implement same interface, so can store different types together.

---


## 📝 Notes

### Important Points:
- **Port 8080** - Make sure nothing else is running on this port
- **CORS Enabled** - Frontend can connect from any origin (development only!)
- **In-Memory Data** - All data is lost when server restarts
- **No Authentication** - This is educational project, not production-ready

### Known Limitations:
- No data persistence (no database)
- No real security/authentication
- Limited error handling
- No input validation on some fields
- API rate limiting not implemented

### Educational Purpose:
This project is for learning design patterns. In a real production application, you would need:
- Proper database with migrations
- Security (Spring Security, JWT)
- Input validation (Bean Validation)
- Comprehensive error handling
- Unit and integration tests
- CI/CD pipeline
- Monitoring and logging
- Documentation (Swagger/OpenAPI)

---

## 👨‍🎓 Author

Student project for Software Design Patterns course  
Authors: Zhagipar Aruzhan, Abdimutalip Aidana. 
Repository: [finalsdp](https://github.com/aruwksc/finalsdp)

---

**Made with ❤️ for Software Design Patterns course**