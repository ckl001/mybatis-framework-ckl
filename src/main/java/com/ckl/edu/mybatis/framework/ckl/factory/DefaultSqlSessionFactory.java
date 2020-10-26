package com.ckl.edu.mybatis.framework.ckl.factory;

import com.ckl.edu.mybatis.framework.ckl.config.Configuration;
import com.ckl.edu.mybatis.framework.ckl.executor.Executor;
import com.ckl.edu.mybatis.framework.ckl.sqlsession.DefaultSqlSession;
import com.ckl.edu.mybatis.framework.ckl.sqlsession.SqlSession;

/**
 * @author chenkanglin
 * @desc
 *      使用的是工厂方法设计模式
 * @Date 2020-09-14 11:53
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory {

    private Configuration configuration;

    public DefaultSqlSessionFactory(Configuration configuration) {
        this.configuration = configuration;
    }


    @Override
    public SqlSession openSession() {
        // 获取Executor
        // 一种是默认simple
        // 一种是全局的配置
        // 一种是制定的ExecutorType
        Executor executor = configuration.newExecutor(null);
        return new DefaultSqlSession(configuration, executor);
    }


}
