package com.ckl.edu.mybatis.framework.ckl.handler;

import com.ckl.edu.mybatis.framework.ckl.config.Configuration;
import com.ckl.edu.mybatis.framework.ckl.config.MappedStatement;
import com.ckl.edu.mybatis.framework.ckl.sqlsource.BoundSql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

/**
 * @author chenkanglin
 * @desc  有 #{} 参数
 * @Date 2020-09-14 20:56
 */
public class PreparedStatementHandler implements StatementHandler{


    private ParameterHandler parameterHandler;
    private ResultSetHandler resultSetHandler;

    public PreparedStatementHandler(Configuration configuration) {
        this.parameterHandler = configuration.newParameterHandler();
        this.resultSetHandler = configuration.newResultSetHandler();

    }

    @Override
    public Statement prepare(Connection connection, String sql) throws Exception {
        return connection.prepareStatement(sql);
    }

    @Override
    public void parammeterize(Statement statement, Object param, BoundSql boundSql) throws Exception {
        PreparedStatement preparedStatement = (PreparedStatement) statement;
        parameterHandler.setParameter(preparedStatement, param, boundSql);

    }

    @Override
    public <T> List<T> query(Statement statement, MappedStatement mappedStatement) throws Exception {
        PreparedStatement preparedStatement = (PreparedStatement) statement;
        ResultSet rs = preparedStatement.executeQuery();
        return resultSetHandler.handleResult(statement,rs,mappedStatement);
    }
}
