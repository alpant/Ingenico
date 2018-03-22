package mapper;

import model.Account;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

/**
 * Created by alperul on 10/9/2017.
 */
public interface AccountMapper {

    String INSERT_ACCOUNT = "insert into account (account_id, user_id, name, balance) values (#{accountId}, #{userId}, " +
            "#{name}, #{balance}";
    String SELECT_ACCOUNT_BY_ID = "select * from account where account_id = #{accountId}";
    String UPDATE_ACCOUNT = "update account set account_id = #{accountId}, user_id = #{userId}," +
            "name = #{name}, balance = #{balance} where account_id = #{accountId}";

    @Insert(INSERT_ACCOUNT)
    @SelectKey(statement = "select nextval('account_seq')", keyProperty = "accountId", before = true, resultType = Long.class)
    Long addAccount(Account account);

    @Select(SELECT_ACCOUNT_BY_ID)
    @ResultMap("Account")
    Account getAccountById(Long accountId);

    @Update(UPDATE_ACCOUNT)
    Account updateACcount(Account account);

}
