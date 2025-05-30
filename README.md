# 💳 ATM Simulation System (Core Java + MySQL)

This is a console-based **ATM Simulation Project** built with **Core Java** and **MySQL**. It allows customer registration, deposits, withdrawals, balance checks, and fund transfers — with database integration using JDBC.

---

## 📁 Project Structure

```
ATM-Simulation/
├── src/                      # Java source files
│   ├── ATMService.java
│   ├── Balance.java
│   ├── Customer.java
│   ├── DBConnection.java
│   └── Main.java             # Console-based menu UI
├── db/                       # SQL schema files
│   ├── customer.sql
│   └── balance.sql
├── .gitignore
├── db.properties             # (Not pushed to GitHub)
└── README.md
```



---

## ⚙️ Features

### ✅ Core Functionalities

- Register new customers
- View and update customer info
- Deposit and withdraw money
- Check account balance
- Transfer funds between accounts
- Console-based user interaction

### ✨ Bonus Features (Optional)

- Transaction logging (e.g., with timestamps)
- Customer search by name or mobile number
- Multi-threaded ATM simulation (handling concurrent transactions)

---

## 🧑‍💻 Technologies Used

- **Java (Core Java, JDBC)**
- **MySQL**
- Console-based UI

---

## 🛠️ Database Setup

1. **Create the database:**

```sql
CREATE DATABASE atm;
USE atm;
mysql -u root -p atm < db/customer.sql
mysql -u root -p atm < db/balance.sql
```

✅ The customer table must be created before the balance table due to foreign key constraints.

## 🔐 Database Configuration
Use a db.properties file (not committed to Git) to store DB credentials:
````
db.url=jdbc:mysql://localhost:3306/atm
db.username=root
db.password=your_password_here
````
Add it to the root folder and not inside of src


## 🧪 Sample Console Menu
````
Welcome to the ATM Simulation
1. Register New Customer
2. Deposit
3. Withdraw
4. Check Balance
5. Transfer Funds
6. Exit<br>
Enter your choice:
````

## 📄 License
This project is open-source for learning and development purposes.

## Author
- Sashank Baral



