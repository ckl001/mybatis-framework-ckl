package com.ckl.edu.mybatis.framework.ckl.handler;

import com.ckl.edu.mybatis.framework.ckl.config.MappedStatement;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

/**
 * @author chenkanglin
 * @desc
 * @Date 2020-09-14 21:27
 */
public interface ResultSetHandler {

    <T> List<T> handleResult(Statement statement, ResultSet rs, MappedStatement mappedStatement) throws Exception;

}
