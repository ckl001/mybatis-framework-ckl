package com.ckl.edu.mybatis.framework.ckl.executor;

import com.ckl.edu.mybatis.framework.ckl.config.Configuration;
import com.ckl.edu.mybatis.framework.ckl.config.MappedStatement;
import com.ckl.edu.mybatis.framework.ckl.sqlsource.BoundSql;

import java.util.List;

/**
 * @author chenkanglin
 * @desc 批处理执行器
 * @Date 2020-09-14 22:19
 */
public class BatchExecutor extends BaseExecutor {

    @Override
    protected <T> List<T> queryFromDataBase(Configuration configuration, MappedStatement mappedStatement, BoundSql boundSql, Object param) {
        return null;
    }

}
