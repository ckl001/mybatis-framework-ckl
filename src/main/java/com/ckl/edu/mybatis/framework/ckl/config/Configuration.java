package com.ckl.edu.mybatis.framework.ckl.config;

import com.ckl.edu.mybatis.framework.ckl.executor.CachingExecutor;
import com.ckl.edu.mybatis.framework.ckl.executor.Executor;
import com.ckl.edu.mybatis.framework.ckl.executor.SimpleExecutor;
import com.ckl.edu.mybatis.framework.ckl.handler.*;
import lombok.Data;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author chenkanglin
 * @desc  用于存储全局配置文件和映射文件中的数据
 * @Date 2020-09-05 02:02
 */
@Data
public class Configuration {

    private DataSource dataSource;

    private Map<String,MappedStatement> mappedStatementMap = new HashMap<>();

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public MappedStatement getMappedStatementById(String statementId) {
        return mappedStatementMap.get(statementId);
    }

    public void addMappedStatement(String statementId, MappedStatement mappedStatement) {
        this.mappedStatementMap.put(statementId,mappedStatement);
    }

    public StatementHandler newStatementHandler(String statementType) {
        RouterStatementHandler routerStatementHandler = new RouterStatementHandler(statementType, this);
        return routerStatementHandler;
    }

    public ParameterHandler newParameterHandler() {
        return new DefaultParameterHandler();
    }

    public ResultSetHandler newResultSetHandler() {
        return new DefaultResultSetHandler();
    }



    private boolean cacheEnabled = true;

    public Executor newExecutor(String executorType) {
        // 如果选择executorType，则默认为simple
        executorType = null == executorType || "".equals(executorType) ? "simple" : executorType;

        Executor executor = null;

        if("simple".equals(executorType)){
            executor = new SimpleExecutor();
        }

        // 装饰模式
        if (cacheEnabled){
            // 创建二级缓存处理功能的执行器，但是该执行器还是要继续往下执行，得调用真正干活的执行器
            executor = new CachingExecutor(executor) ;
        }
        return executor;
    }
}
