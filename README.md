# ☕ Java Project

A collection of Java projects and practical applications developed in **IntelliJ IDEA**.

This repository contains Java Swing applications, MVC-based projects, socket programming, Servlet/JSP projects, and Maven-based web applications.


---

## 📑 Table of Contents

- [Projects Included](#-projects-included)
- [Technologies Used](#️-technologies-used)
- [Repository Structure](#-repository-structure)
- [Requirements](#-requirements)
- [Running Java Projects](#-running-java-projects)
- [Running Maven Servlet Projects](#-running-maven-servlet-projects)
- [IntelliJ IDEA Setup](#-intellij-idea-setup)
- [Git Commands](#-git-commands)
- [Learning Objectives](#-learning-objectives)
- [Future Improvements](#-future-improvements)
- [Author](#-author)
- [License](#-license)

---

## 📌 Projects Included

### 1. JavaCalculator
A Java calculator application implemented using the **MVC architecture**.

**Main classes:**
- `CalculatorModel`
- `CalculatorView`
- `CalculatorController`
- `MainCalculator`

**Architecture:**

```
View → Controller → Model
```

### 2. StudentManagementSystem
A Java-based student management application following an MVC-style structure.

**Main classes:**
- `Main`
- `Student`
- `StudentController`
- `StudentDAO`
- `StudentView`

**Features:**
- Student data management
- MVC architecture
- DAO-based data handling

### 3. Chat Application
A Java client-server chat application using socket programming.

**Main classes:**
- `Client`
- `Server`

**Concepts used:**
- Java Socket Programming
- Client-Server Architecture
- Network Communication

### 4. JavaSwingDrawing
A Java Swing drawing/GUI application.

**Technologies:** Java Swing, AWT, Event Handling, GUI Components

### 5. UserSelectionForm
A Java GUI form for user selection and interaction.

**Technologies:** Java Swing, AWT, Event Handling

### 6. Student Info
A Java application for handling student information.

### 7. Servlet Project
A Maven-based Java Servlet web application.

**Structure:**

```
servlet/
├── pom.xml
├── src/
│   └── main/
│       ├── java/
│       │   └── servlet/
│       │       └── HelloServlet.java
│       ├── resources/
│       └── webapp/
│           └── index.html
└── target/
```

**Technologies:** Java, Jakarta Servlet, Maven, HTML, Apache Tomcat

### 8. Student Servlet Project
A Maven-based Servlet project containing a login servlet.

**Main class:** `LoginServlet`

**Technologies:** Java, Servlet, Maven, JSP/HTML, Apache Tomcat

---

## 🛠️ Technologies Used

- Java
- Java Swing
- AWT
- Socket Programming
- Servlet
- JSP/HTML
- JDBC/DAO concepts
- Maven
- Apache Tomcat
- IntelliJ IDEA
- Git & GitHub

---

## 📂 Repository Structure

```
java project/
│
├── .idea/
├── out/
│
├── servlet/
│   ├── .mvn/
│   ├── src/
│   │   └── main/
│   │       ├── java/
│   │       │   └── servlet/
│   │       │       └── HelloServlet.java
│   │       ├── resources/
│   │       └── webapp/
│   │           └── index.html
│   ├── target/
│   └── pom.xml
│
├── src/
│   ├── chat/
│   │   ├── Client.java
│   │   └── Server.java
│   │
│   ├── JavaCalculator/
│   │   ├── CalculatorController.java
│   │   ├── CalculatorModel.java
│   │   ├── CalculatorView.java
│   │   └── MainCalculator.java
│   │
│   ├── StudentManagementSystem/
│   │   ├── Main.java
│   │   ├── Student.java
│   │   ├── StudentController.java
│   │   ├── StudentDAO.java
│   │   └── StudentView.java
│   │
│   ├── JavaSwingDrawing.java
│   ├── studentinfo.java
│   └── UserSelectionForm.java
│
├── student/
│   ├── pom.xml
│   ├── src/
│   │   └── main/
│   │       ├── java/
│   │       │   └── servlet/
│   │       │       └── LoginServlet.java
│   │       ├── resources/
│   │       └── webapp/
│   └── target/
│
├── .gitignore
├── java project.iml
└── README.md
```

---

## 💻 Requirements

Recommended environment:

- JDK 25
- IntelliJ IDEA
- Apache Maven
- Apache Tomcat 11 (for Servlet projects)
- Git

Verify Maven:

```bash
mvn -version
```

Verify Java:

```bash
java -version
```

---

## 🚀 Running Java Projects

### Swing / Core Java Projects

1. Open the project in IntelliJ IDEA.
2. Select the required Java class.
3. Find the `main()` method.
4. Right-click the class.
5. Select **Run**.

Example:

```java
public static void main(String[] args) {
    // application starts here
}
```

---

## 🌐 Running Maven Servlet Projects

1. Open the required Maven project, for example `servlet/`.
2. Reload the Maven project in IntelliJ IDEA.
3. Build using:

   ```bash
   mvn clean package
   ```

4. Deploy the generated WAR / WAR-exploded artifact to **Apache Tomcat 11**.
5. Open the application in the browser, for example:

   ```
   http://localhost:8080/servlet/
   ```

> The exact URL depends on the configured Tomcat context path.

---

## 🔧 IntelliJ IDEA Setup

1. Open IntelliJ IDEA.
2. Open the `java project` folder.
3. Set the Project SDK to **JDK 25**.
4. For Maven projects, open/reload `pom.xml` as a Maven project.
5. For Servlet projects, configure **Tomcat 11**.
6. Add the required WAR / WAR exploded deployment.
7. Run the Tomcat configuration.

> **Note:** Tomcat 11 uses the Jakarta namespace. Servlet code should use:
>
> ```java
> import jakarta.servlet.*;
> import jakarta.servlet.http.*;
> ```
>
> Do **not** use the old `javax.servlet.*` imports.

---

## 🔀 Git Commands

Check repository status:

```bash
git status
```

Add files:

```bash
git add .
```

Commit:

```bash
git commit -m "Update Java projects"
```

Push:

```bash
git push
```

If the GitHub remote has not been configured:

```bash
git remote add origin https://github.com/gargv4049/JavaSwing.git
```

Check remote:

```bash
git remote -v
```

If `origin` already exists and you need to change it:

```bash
git remote set-url origin https://github.com/gargv4049/JavaSwing.git
```

---

## 🎯 Learning Objectives

This repository demonstrates practical implementation of:

- Object-Oriented Programming
- Java Swing GUI development
- MVC architecture
- DAO pattern
- Event handling
- Socket programming
- Client-server communication
- Java Servlets
- Maven project management
- Web application development
- Tomcat deployment
- Git and GitHub workflow

---

## 🔮 Future Improvements

- [ ] MySQL database integration
- [ ] JDBC-based CRUD operations
- [ ] Better form validation
- [ ] Authentication and authorization
- [ ] JSP-based frontend
- [ ] Improved MVC architecture
- [ ] REST-style APIs
- [ ] Better exception handling
- [ ] Unit testing
- [ ] Responsive web UI
- [ ] Deployment to a cloud server

---

## 👨‍💻 Author

**Vivek Garg**

- GitHub: [@gargv4049](https://github.com/gargv4049)
- Repository: [github.com/gargv4049/JavaSwing](https://github.com/gargv4049/JavaSwing)

---

## 📄 License

This repository is created primarily for educational, academic, and learning purposes.
