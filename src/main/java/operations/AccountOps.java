package operations;

import resource.CreateAccountRequest;
import resource.TransferReq;

/**
 * Created by alperul on 10/9/2017.
 */
public interface AccountOps {

	/*
	*Creates an account for given user
	*/
    void createAccount(CreateAccountRequest request) throws Exception;

	/*
	*Transfer money from one account to another account
	*/
    void transferMoney(TransferReq request) throws Exception;
}
