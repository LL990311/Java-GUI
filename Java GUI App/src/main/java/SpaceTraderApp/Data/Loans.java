package SpaceTraderApp.Data;

import java.util.List;

public class Loans {
    public List<Loan> loans;
    public int credits;
    public Loan loan;

    public List<Loan> getLoans() {
        return loans;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public Loan getLoan() {
        return loan;
    }

    public void setLoan(Loan loan) {
        this.loan = loan;
    }

    public void setLoans(List<Loan> loans) {
        this.loans = loans;
    }
}
