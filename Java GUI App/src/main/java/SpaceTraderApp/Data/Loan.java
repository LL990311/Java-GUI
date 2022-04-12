package SpaceTraderApp.Data;

public class Loan {
    private int amount;
    private boolean collateralRequired;
    private int rate;
    private int termInDays;
    private String type;
    String due;

    public String getDue() {
        return due;
    }

    public void setDue(String due) {
        this.due = due;
    }

    private String id;
    private int repaymentAmount;
    private String status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getRepaymentAmount() {
        return repaymentAmount;
    }

    public void setRepaymentAmount(int repaymentAmount) {
        this.repaymentAmount = repaymentAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getAmount() {
        return amount;
    }

    public boolean isCollateralRequired() {
        return collateralRequired;
    }

    public int getRate() {
        return rate;
    }

    public int getTermInDays() {
        return termInDays;
    }

    public String getType() {
        return type;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setCollateralRequired(boolean collateralRequired) {
        this.collateralRequired = collateralRequired;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public void setTermInDays(int termInDays) {
        this.termInDays = termInDays;
    }

    public void setType(String type) {
        this.type = type;
    }
}
