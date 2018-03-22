package operations;

import db.AccountDao;
import db.DbSession;
import model.Account;
import resource.CreateAccountRequest;
import resource.TransferReq;

/**
 * Created by alperul on 10/9/2017.
 */
public class AccountOpImpl implements AccountOps {

    @Override
    public void createAccount(CreateAccountRequest request) throws Exception {

        if (request.getUserId() == 0)
            throw new Exception("UserId is empty!");

        if (request.getAccountName() == null)
            throw new Exception("Account name is null!");

        AccountDao accountDao = new AccountDao();

        try (DbSession dbSession = new DbSession()) {
            Account account = new Account();
            account.setUserId(request.getUserId());
            account.setName(request.getAccountName());
            account.setBalance(request.getBalance());

            accountDao.createAccount(account, dbSession);

            dbSession.commit();
        } catch (Exception e) {
            rollbackDao(accountDao);
            throw e;
        }
    }

    @Override
    public void transferMoney(TransferReq request) throws Exception {

        AccountDao accountDao = new AccountDao();
        try (DbSession dbSession = new DbSession()) {

            Account fromAccount = accountDao.getAccountById(request.getFromAccountId(), dbSession);

            if (fromAccount == null)
                throw new Exception("Source account could not found!");

            if (fromAccount.getBalance() - request.getAmount() < 0)
                throw new Exception("The transfer amount can not be higher than current balance");

            Account toAccount = accountDao.getAccountById(request.getToAccountId(), dbSession);

            if (toAccount == null)
                throw new Exception("Target account could not found!");

			//money transfer
            fromAccount.setBalance(fromAccount.getBalance() - request.getAmount());
            accountDao.updateAccount(fromAccount, dbSession);

            toAccount.setBalance(toAccount.getBalance() + request.getAmount());
            accountDao.updateAccount(toAccount, dbSession);

            dbSession.commit();
        } catch (Exception e) {
            rollbackDao(accountDao); //if there is an exception rollback the operation
            throw e;
        }
    }

    private void rollbackDao(AccountDao accountDao) {
        accountDao.getSession().rollback();
    }

}
