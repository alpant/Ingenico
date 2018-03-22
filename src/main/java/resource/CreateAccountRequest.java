package resource;

/**
 * Created by alperul on 10/9/2017.
 */
public class CreateAccountRequest extends GenericRequest{

    private long userId;
    private String accountName;
    private long balance;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }
}
