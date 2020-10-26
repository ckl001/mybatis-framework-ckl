package com.ckl.edu.mybatis.framework.ckl.handler;

import com.ckl.edu.mybatis.framework.ckl.config.Configuration;
import com.ckl.edu.mybatis.framework.ckl.config.MappedStatement;
import com.ckl.edu.mybatis.framework.ckl.sqlsource.BoundSql;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

/**
 * @author chenkanglin
 * @desc
 * @Date 2020-09-14 20:55
 */
public class RouterStatementHandler implements StatementHandler {

    private StatementHandler delegate;

    public RouterStatementHandler(String statementType, Configuration configuration) {
        switch (statementType){
            case "prepared" :
                delegate = new PreparedStatementHandler(configuration);
                break;
            case "callble" :
                delegate = new CallableStatementHandler(configuration);
                break;
            case "simple" :
                delegate = new SimpleStatementHandler(configuration);
                break;
            default:
                delegate = new PreparedStatementHandler(configuration);
        }
    }

    @Override
    public Statement prepare(Connection connection, String sql) throws Exception {
        return delegate.prepare(connection, sql);
    }

    @Override
    public void parammeterize(Statement statement, Object param, BoundSql boundSql) throws Exception {
        delegate.parammeterize(statement, param, boundSql);
    }

    @Override
    public <T> List<T> query(Statement statement, MappedStatement mappedStatement) throws Exception {
        return delegate.query(statement, mappedStatement);
    }
}
