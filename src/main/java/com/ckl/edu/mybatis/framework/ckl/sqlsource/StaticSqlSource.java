package com.ckl.edu.mybatis.framework.ckl.sqlsource;

import java.util.List;

/**
 * @author chenkanglin
 * @desc
 *      只是用来存储DynamicSqlSource和RawSource解析后的结果
 *      StaticSqlSource 是 DynamicSqlSource和RawSqlSource解析之后，封装的一个SqlSource
 * @Date 2020-09-07 17:24
 */
public class StaticSqlSource implements SqlSource{

    private String sql;

    private List<ParameterMapping> parameterMappings;

    public StaticSqlSource(String sql, List<ParameterMapping> parameterMappings) {
        this.sql = sql;
        this.parameterMappings = parameterMappings;
    }

    @Override
    public BoundSql getBoundSql(Object param) {
        return new BoundSql(sql, parameterMappings);
    }
}
