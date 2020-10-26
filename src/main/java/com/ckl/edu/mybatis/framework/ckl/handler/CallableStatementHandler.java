package com.ckl.edu.mybatis.framework.ckl.handler;

import com.ckl.edu.mybatis.framework.ckl.config.Configuration;
import com.ckl.edu.mybatis.framework.ckl.config.MappedStatement;
import com.ckl.edu.mybatis.framework.ckl.sqlsource.BoundSql;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

/**
 * @author chenkanglin
 * @desc  存储过程
 * @Date 2020-09-14 20:57
 */
public class CallableStatementHandler implements StatementHandler {


    public CallableStatementHandler(Configuration configuration) {

    }


    @Override
    public Statement prepare(Connection connection, String sql) throws Exception {
        return null;
    }

    @Override
    public void parammeterize(Statement statement, Object param, BoundSql boundSql) throws Exception {

    }

    @Override
    public <T> List<T> query(Statement statement, MappedStatement mappedStatement) throws Exception {
        return null;
    }
}
