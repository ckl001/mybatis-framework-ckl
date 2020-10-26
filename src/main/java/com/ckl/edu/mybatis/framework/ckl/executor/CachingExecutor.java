package com.ckl.edu.mybatis.framework.ckl.executor;

import com.ckl.edu.mybatis.framework.ckl.config.Configuration;
import com.ckl.edu.mybatis.framework.ckl.config.MappedStatement;

import java.util.List;

/**
 * @author chenkanglin
 * @desc
 * @Date 2020-09-14 20:39
 */
public class CachingExecutor implements Executor {

    // 真正干活的执行器
    private Executor delegate;

    public CachingExecutor(Executor delegate) {
        this.delegate = delegate;
    }

    @Override
    public <T> List<T> query(Configuration configuration, MappedStatement mappedStatement, Object param) {

        // TODO 二级缓存(SqlSession)的处理
        // 可以从 mappedStatement 中获取它的二级缓存对象

        // 如果没有配置二级缓存，则走真正的处理器
        return delegate.query(configuration,mappedStatement,param);
    }

}
