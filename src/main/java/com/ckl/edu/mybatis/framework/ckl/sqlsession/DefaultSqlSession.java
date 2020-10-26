package com.ckl.edu.mybatis.framework.ckl.sqlsession;

import com.ckl.edu.mybatis.framework.ckl.config.Configuration;
import com.ckl.edu.mybatis.framework.ckl.config.MappedStatement;
import com.ckl.edu.mybatis.framework.ckl.executor.Executor;

import java.util.List;

/**
 * @author chenkanglin
 * @desc
 * @Date 2020-09-14 22:41
 */
public class DefaultSqlSession implements SqlSession {

    private Configuration configuration;
    private Executor executor;

    public DefaultSqlSession(Configuration configuration, Executor executor) {
        this.configuration = configuration;
        this.executor = executor;
    }

    @Override
    public <T> List<T> selectList(String statementId, Object param) {
        // 获取mappedStatement
        MappedStatement mappedStatement = configuration.getMappedStatementById(statementId);
        // 调用Executor的方法
        return executor.query(configuration,mappedStatement, param);
    }

    @Override
    public <T> T selectOne(String statementId, Object param) {
        List<T> list = this.selectList(statementId, param);
        if (list != null && list.size() == 1){
            return list.get(0);
        }else{
            // TODO 抛出异常
        }
        return null;
    }
}
