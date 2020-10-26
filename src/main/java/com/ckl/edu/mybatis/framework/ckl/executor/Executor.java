package com.ckl.edu.mybatis.framework.ckl.executor;

import com.ckl.edu.mybatis.framework.ckl.config.Configuration;
import com.ckl.edu.mybatis.framework.ckl.config.MappedStatement;

import java.util.List;

/**
 * @author chenkanglin
 * @Desc
 *      执行器
 */
public interface Executor {

    <T> List<T> query(Configuration configuration, MappedStatement mappedStatement, Object param);
}
