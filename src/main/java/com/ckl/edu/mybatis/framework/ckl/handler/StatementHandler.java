package com.ckl.edu.mybatis.framework.ckl.handler;

import com.ckl.edu.mybatis.framework.ckl.config.MappedStatement;
import com.ckl.edu.mybatis.framework.ckl.sqlsource.BoundSql;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

/**
 * @author chenkanglin
 * @desc
 * @Date 2020-09-14 20:47
 */
public interface StatementHandler {


    Statement prepare(Connection connection, String sql) throws Exception;

    void parammeterize(Statement statement, Object param, BoundSql boundSql) throws Exception;

    <T> List<T> query(Statement statement, MappedStatement mappedStatement) throws Exception;





}
