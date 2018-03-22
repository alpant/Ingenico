package db;

import mapper.AccountMapper;
import model.Account;

/**
 * Created by alperul on 10/9/2017.
 * Data Access Object for acount table
 */
public class AccountDao {

    private DbSession dbSession = null;
	
    public DbSession getSession() {
        try {
            if (dbSession == null || dbSession.isClosed()) {
                dbSession = new DbSession();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Long createAccount(Account account, DbSession dbSession) {
        AccountMapper mapper = dbSession.getMapper(AccountMapper.class);
        mapper.addAccount(account);
        return account.getAccountId();
    }

    public Account getAccountById(Long accountId, DbSession dbSession) {
        AccountMapper mapper = dbSession.getMapper(AccountMapper.class);
        return mapper.getAccountById(accountId);
    }

    public Account updateAccount(Account account, DbSession dbSession) {
        AccountMapper mapper = dbSession.getMapper(AccountMapper.class);
        return mapper.updateACcount(account);
    }
}