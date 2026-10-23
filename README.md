# Computer Store Prototype

A desktop stock and order management system for a small computer hardware shop, built with Java Swing.

This was the first full application I built, back in 2022. It's kept here as a prototype and a record of where I started, so expect the rough edges of a first project.

## Features

- **Login and registration.** Passwords are salted and hashed with SHA-256 over 1,000 iterations. New sign-ups start disabled until a manager enables them.
- **Role-based access.** There are two roles, `Manager` and `Standard`. Only managers can open user management.
- **Stock management.** Browse, add, and edit items (name, brand, type, quantity, price, discount). You can sort by any column and search the list.
- **Checkout.** Add items to a basket, link a customer to the order if you want, and place it. Discounts are applied to the total, and stock levels go down automatically.
- **Order history.** See past orders and the items in each one.
- **Customer management.** Browse, add, and edit customer records.
- **User management.** Managers can add users, change roles, and enable or disable accounts.
- **Input validation.** The forms check for missing fields, email format, phone numbers, dates (including leap years), password strength, price precision, unique usernames, and commas (which would break the file format).

## Tech stack

| | |
|---|---|
| Language | Java 20 |
| UI | Swing, with the forms designed in the NetBeans GUI Builder (`.form` files) |
| Build | Maven |
| Storage | Comma-separated plain-text files, no database |

## Project structure

```
.
├── pom.xml
├── nbactions.xml            # NetBeans run configuration
├── users.txt                # id, first name, surname, username, password hash, role, status
├── customers.txt            # id, first name, surname, email, phone, date
├── items.txt                # id, name, brand, type, quantity, price, discount
├── orders.txt               # id, customer id (or null), total, date
├── orderItems.txt           # order id, item id, quantity
├── basket.txt               # current basket (cleared after each order)
└── src/main/java/com/mycompany/computerstoreprototype/
    ├── LoginPage*.java       # Entry point, login and registration
    ├── HomePage.java         # Main menu
    ├── Stock*.java           # Items, checkout, and order history screens
    ├── Customers*.java       # Customer screens
    ├── Users*.java           # User admin screens (managers only)
    ├── Item / Order / Customer / User.java   # Domain classes
    ├── FileManagement.java   # Reading and writing the CSV files, generating IDs
    ├── SortsAndSearches.java # Hand-written quicksort, binary search, and linear search
    ├── Validator.java        # Input validation rules
    └── PasswordsAndSecurity.java  # Password hashing
```

## Getting started

### Prerequisites

- JDK 20 or newer
- Maven 3.x, or NetBeans, which comes with Maven

### Run in NetBeans

Open the project folder in NetBeans and click **Run**. The included `nbactions.xml` already sets `LoginPage` as the main class.

### Run from the command line

Run these commands from the project root. The app looks for the `.txt` data files in the current working directory, so starting it from anywhere else won't work.

```bash
mvn clean package
```

```bash
java -cp target/classes com.mycompany.computerstoreprototype.LoginPage
```

> **Note:** `pom.xml` sets `exec.mainClass` to `ComputerStoreProtoType`, but no class has that name. Start `LoginPage` directly as shown above, or change `exec.mainClass` in `pom.xml` to `com.mycompany.computerstoreprototype.LoginPage`.

### Logging in

The data files come with made-up sample users, customers, items, and orders. You can log in with these demo accounts:

| Username | Password | Role | Status |
|---|---|---|---|
| `Admin` | `Admin123!` | Manager | Enabled |
| `Standard` | `Standard123!` | Standard | Enabled |
| `usertest` | `Password123!` | Standard | Enabled |
| `UniqueUser200` | `Password123!` | Standard | Enabled |
| `usernameunique` | `Password123!` | Standard | Disabled |

Accounts you create with **Register** start out disabled. To use one, log in as `Admin` and enable it from user management.

## What I'd do differently now

Looking back, this is what I'd change:

- **Use a real database.** The CSV files break as soon as a value contains a comma, IDs come from line counts, and two people using the app at once would overwrite each other's changes.
- **Hash passwords properly.** Iterated SHA-256 with a hard-coded pepper works, but a purpose-built algorithm like bcrypt or Argon2 is the right choice.
- **Fix the session handling.** The logged-in user's role is kept in a static field on `LoginPage`.
- **Separate the logic from the UI.** Most of the business logic is written straight into Swing event handlers.
- **Add tests.** There aren't any.
- **Fix known bugs.** For example, the binary search always compares against column 1 instead of the column it was asked to search.

## License

This project has no license yet. Add one, such as MIT, if you want other people to be able to reuse the code.
