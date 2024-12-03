import java.util.Map;
import java.util.TreeMap;

public class ATM {
    private final TreeMap<Integer, Integer> denominations = new TreeMap<>((a, b) -> b - a);

    public ATM() {
        // Initialize the ATM with zero balances
        denominations.put(20, 0);
        denominations.put(10, 0);
        denominations.put(5, 0);
        denominations.put(1, 0);
    }

    public void deposit(Map<Integer, Integer> depositAmounts) {
        if (depositAmounts.values().stream().anyMatch(v -> v < 0)) {
            System.out.println("Incorrect deposit amount");
            return;
        }

        if (depositAmounts.values().stream().allMatch(v -> v == 0)) {
            System.out.println("Deposit amount cannot be zero");
            return;
        }

        depositAmounts.forEach((denomination, count) -> {
            denominations.put(denomination, denominations.getOrDefault(denomination, 0) + count);
        });

        printBalances();
    }

    public void withdraw(int amount) {
        if (amount <= 0 || amount > getTotalBalance()) {
            System.out.println("Incorrect or insufficient funds");
            return;
        }

        Map<Integer, Integer> toDispense = new TreeMap<>((a, b) -> b - a);
        int remainingAmount = amount;

        for (Map.Entry<Integer, Integer> entry : denominations.entrySet()) {
            int denomination = entry.getKey();
            int count = entry.getValue();

            if (denomination <= remainingAmount && count > 0) {
                int notes = Math.min(remainingAmount / denomination, count);
                toDispense.put(denomination, notes);
                remainingAmount -= notes * denomination;
            }
        }

        if (remainingAmount > 0) {
            System.out.println("Requested withdraw amount is not dispensable");
            return;
        }

        toDispense.forEach((denomination, count) -> {
            denominations.put(denomination, denominations.get(denomination) - count);
        });

        printDispensedNotes(toDispense);
        printBalances();
    }

    private void printBalances() {
        int total = getTotalBalance();
        System.out.println("Balance: " + denominations + ", Total=" + total);
    }

    private void printDispensedNotes(Map<Integer, Integer> toDispense) {
        System.out.println("Dispensed: " + toDispense);
    }

    private int getTotalBalance() {
        return denominations.entrySet().stream().mapToInt(e -> e.getKey() * e.getValue()).sum();
    }

    public static void main(String[] args) {
        ATM atm = new ATM();

        // Test deposits
        System.out.println("Deposit 1: 10s: 8, 5s: 20");
        atm.deposit(Map.of(20, 0, 10, 8, 5, 20, 1, 0));

        System.out.println("Deposit 2: 20s: 3, 5s: 18, 1s: 4");
        atm.deposit(Map.of(20, 3, 10, 0, 5, 18, 1, 4));

        // Test withdrawals
        System.out.println("Withdraw 1: 75");
        atm.withdraw(75);

        System.out.println("Withdraw 2: 122");
        atm.withdraw(122);

        System.out.println("Withdraw 3: 63");
        atm.withdraw(63);

        System.out.println("Withdraw 4: 253");
        atm.withdraw(253);

        System.out.println("Withdraw 5: -25");
        atm.withdraw(-25);
    }
}
