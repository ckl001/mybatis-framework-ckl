package com.ckl.edu.mybatis.framework.ckl.executor;

import com.ckl.edu.mybatis.framework.ckl.cache.PerpetualCache;
import com.ckl.edu.mybatis.framework.ckl.config.Configuration;
import com.ckl.edu.mybatis.framework.ckl.config.MappedStatement;
import com.ckl.edu.mybatis.framework.ckl.sqlsource.BoundSql;
import com.ckl.edu.mybatis.framework.ckl.sqlsource.SqlSource;

import java.sql.Statement;
import java.util.List;

/**
 * @author chenkanglin
 * @desc
 *      抽象一级缓存处理业务逻辑出来
 * @Date 2020-09-14 20:29
 */
public abstract class BaseExecutor implements Executor {


    private PerpetualCache cache = new PerpetualCache();

    @Override
    public <T> List<T> query(Configuration configuration, MappedStatement mappedStatement, Object param) {
        SqlSource sqlSource = mappedStatement.getSqlSource();
        BoundSql boundSql = sqlSource.getBoundSql(param);
        String cacheKey = crateCacheKey(boundSql);
        // 先检查一级缓存（namespace）是否存在
        List<T> list = (List<T>) cache.get(cacheKey);

        // 为空，则查询数据库
        if(null == list){
            list = queryFromDataBase(configuration, mappedStatement, boundSql, param);
            cache.put(cacheKey, list);
        }

        return list;
    }

    protected abstract <T> List<T> queryFromDataBase(Configuration configuration, MappedStatement mappedStatement,  BoundSql boundSql, Object param);


    protected String crateCacheKey(BoundSql boundSql){
        return boundSql.getSql();
    }
}
