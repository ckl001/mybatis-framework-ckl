package com.ckl.edu.mybatis.framework.ckl.handler;

import com.ckl.edu.mybatis.framework.ckl.sqlsource.BoundSql;

import java.sql.PreparedStatement;

/**
 * @author chenkanglin
 * @desc
 * @Date 2020-09-14 21:21
 */
public interface ParameterHandler {

    void setParameter(PreparedStatement preparedStatement, Object param, BoundSql boundSql) throws Exception;
}
