/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Soal3;

/**
 *
 * @author ASUS
 */
import java.awt.HeadlessException;
import java.text.DecimalFormat;
import javax.swing.JOptionPane;


public class Main { 

    public static abstract class BankAccount { 
        protected double balance; 
        protected int numberOfDeposits; 
        protected int numberOfWithdrawals; 
        protected double annualInterest; 
        protected double monthlyCharge; 

// Constructor accepts annual interest and 
// the balance. 
    public BankAccount(double balance, double annualInterest) { 
        this.setAnnualInterest(annualInterest);
        this.setBalance(balance); 
    } 
// Default constructor. 
    public BankAccount() {} 

// Monthly charge process.  
    public void monthlyProcess() { 
        this.balance -= this.monthlyCharge; 
        this.calcInterest(); 
        this.monthlyCharge = 0; 
        this.numberOfDeposits = 0; 
        this.numberOfWithdrawals = 0; 
    } 

// Calculate the interest. 
    protected void calcInterest() { 
        double monthlyInterestRate; 
        double monthlyInterest; 

        monthlyInterestRate = this.annualInterest / 12; 
        monthlyInterest = this.balance * monthlyInterestRate; 
        this.balance = this.balance + monthlyInterest;  
    } 

// Withdraw. 
    public boolean withdraw(double amount) { 
        if ((this.balance > amount) && (amount > 0)) 
        { 
            this.balance -= amount; 
            this.numberOfWithdrawals++; 
            return true; 
        } else { 
        return false; 
        } 
    } 

// Deposit 
    public boolean deposit(double amount) { 
        if (amount > 0) 
        { 
            this.balance += amount; 
            this.numberOfDeposits++; 
            return true; 
        } else { 
        return false; 
        } 
    } 

// Accessors and mutators. 
    public double getBalance() { 
        return balance; 
    } 

    public void setBalance(double balance) { 
        this.balance = balance; 
    } 

    public int getNumDepositsThisMonth() { 
        return numberOfDeposits; 
    } 

    public void setNumDepositsThisMonth(int numDepositsThisMonth) { 
        this.numberOfDeposits = numDepositsThisMonth; 
    } 

    public double getAnnualInterest() { 
        return annualInterest; 
    } 

    public void setAnnualInterest(double annualInterest) { 
        this.annualInterest = annualInterest; 
    } 

    public double getMonthlyCharge() { 
        return monthlyCharge; 
    } 

    public void setMonthlyCharge(double monthlyCharge) { 
        this.monthlyCharge = monthlyCharge; 
    } 
    public boolean getIsActive() { 
// TODO Auto-generated method stub 
    return (Boolean) null; 
    } 
} 
    
    public static class SavingsAccount extends BankAccount { 
    protected boolean isActive = (super.balance > 25); 

    // Constructors 
    public SavingsAccount() { 
        super(); 
    } 
    public SavingsAccount(double balance, double annualInterest) { 
        super(balance, annualInterest); 
    } 

    public double getMonthlyCharge() { 
        this.monthlyProcess(); 
        return monthlyCharge; 
    } 

    // Monthly charge process. 
    public void monthlyProcess() { 
        if (super.numberOfWithdrawals > 4) { 
        super.monthlyCharge += super.numberOfWithdrawals - 4; 
            if (super.balance < 25) { 
                this.isActive = false; 
            } 
        } 
    }   

    // Withdraw. 
    public boolean withdraw(double amount) { 
        if (isActive) { 
            return super.withdraw(amount); 
        } else { 
            return false; 
        } 
    } 

    // Deposit 
    public boolean deposit(double amount) { 
        super.deposit(amount); 
        if ((!this.isActive) && super.balance > 25) { 
            this.isActive = true; 
            return true; 
        } else { 
        return false; 
        } 

    } 

    public boolean getIsActive() { 
        return isActive; 
    } 
    public void setActive(boolean isActive) { 
        this.isActive = isActive; 
    } 
} 

    public static void main(String[] args) { 
        double balance = 0; 
        double annualInterest = 0; 
        String output; 
        String input; 
        DecimalFormat df = new DecimalFormat("#0.00"); 

        do { 
        try { 
        output = "Enter your account balance $:"; 
        input = JOptionPane.showInputDialog(output); 

        balance = Double.parseDouble(input); 
        if (balance < 0) { 
            throw new NumberFormatException(); 
            } 
                 break; 
            }   catch (HeadlessException e) { 
                errorMsg(); 
            }   catch (NumberFormatException e) { 
                errorMsg(); 
            } 
        } while (true); 

        do { 
            try { 
                output = "Please enter your annual interest rate: \n" 
                + "EXAMPLE: A 4% interst rate is 0.04"; 
                input = JOptionPane.showInputDialog(output); 

                annualInterest = Double.parseDouble(input); 
                if (annualInterest < 0) { 
                throw new NumberFormatException(); 
                } 
                break; 
                } catch (HeadlessException e) { 
                    errorMsg(); 
                } catch (NumberFormatException e) { 
                    errorMsg(); 
                } 
            } while (true); 

    // Instantiate your object. 
    BankAccount savings = new SavingsAccount(balance, annualInterest); 

    do { 
        try { 
            output = "Enter the number of withdrawals:"; 
            input = JOptionPane.showInputDialog(output); 

            savings.numberOfWithdrawals = Integer.parseInt(input); 
            if (savings.numberOfWithdrawals < 0) { 
            throw new NumberFormatException(); 
            } 
            break; 
            } catch (HeadlessException e) { 
                errorMsg(); 
            } catch (NumberFormatException e) { 
            errorMsg(); 
            } 
        } while (true); 

        do { 
            try { 
                output = "Enter the number of deposits:"; 
                input = JOptionPane.showInputDialog(output); 

                savings.numberOfDeposits = Integer.parseInt(input); 
                if (savings.numberOfDeposits < 0) { 
                throw new NumberFormatException(); 
                } 
                    break; 
                } catch (HeadlessException e) { 
                    errorMsg(); 
                } catch (NumberFormatException e) { 
                    errorMsg(); 
                } 
            } while (true); 

    // Add the interest to the balance: 
    savings.calcInterest(); 

    output = "Account Balance with Interest: $" + df.format(savings.getBalance()); 
    output += "\nMonthly Charge that will be deducted: $" + df.format(savings.getMonthlyCharge()); 
    output += "\nAccount Status: "; 
    output += savings.getIsActive() ? "Is Active" : "Not Active"; 

    JOptionPane.showMessageDialog(null, output); 

    } 

    private static void errorMsg() { 
        String output; 
        output = "Error: There was an error with your entry"; 
        JOptionPane.showMessageDialog(null, output); 
    } 

}
