# ATM Solution

This project is a simple, object-oriented ATM solution built using Java. It allows customers to deposit and withdraw money in denominations of 20, 10, 5, and 1 dollar bills. The solution is designed with extensibility and robustness in mind, supporting future additions of new denominations with minimal code changes.

---

## Features

- **Deposit Money**:
  - Accepts deposits in denominations of 20, 10, 5, and 1 dollar bills.
  - Handles invalid inputs such as negative values or zero deposits.
  - Updates and displays the new balances after each deposit.

- **Withdraw Money**:
  - Dispenses the requested amount using the highest available denominations first.
  - Handles scenarios where the withdrawal amount is zero, negative, exceeds available balance, or cannot be dispensed with available denominations.
  - Displays the dispensed notes and updates the balances.

---

## Requirements

- Java 8 or higher
- JDK for compiling and running the program

---

## How to Run

1. **Clone the Repository**:
   ```bash
   git clone <repository-url>
   cd ATM-Solution
