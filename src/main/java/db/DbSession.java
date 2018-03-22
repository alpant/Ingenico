package db;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import javax.sql.DataSource;
import java.io.Closeable;
import java.io.IOException;

/**
 * Created by alperul on 10/9/2017.
 */
public class DbSession implements Closeable {

    private static SqlSessionFactory sqlSessionFactory;
    private static DataSource dataSource;
    private boolean closed = false;

    protected SqlSession sqlSession = null;

    public DbSession() throws Exception {
        try {
            this.sqlSession = sqlSessionFactory.openSession();
        } catch (Exception e) {
            throw new Exception("DB error");
        }
    }

    public <T> T getMapper(Class<T> mapperClass) {
        return sqlSession.getMapper(mapperClass);
    }

    public void commit() {
        sqlSession.commit();
    }

    public void rollback() {
        sqlSession.rollback();
    }

    public void close() throws IOException {
        sqlSession.close();
        this.closed = true;
    }

    public boolean isClosed() {
        return closed;
    }
}
