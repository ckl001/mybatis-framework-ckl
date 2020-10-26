package com.ckl.edu.mybatis.framework.ckl.sqlsource;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenkanglin
 * @desc
 *      封装解析后的JDBC可以执行的SQL语句和#{}解析出来的参数列表
 * @Date 2020-09-07 17:09
 */
public class BoundSql {

    //SQL 语句
    private String sql;

    //参数信息集合
    private List<ParameterMapping> parameterMappings = new ArrayList<>();

    public BoundSql(String sql, List<ParameterMapping> parameterMappings) {
        this.sql = sql;
        this.parameterMappings = parameterMappings;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public List<ParameterMapping> getParameterMappings() {
        return parameterMappings;
    }

    public void addParameterMapping(ParameterMapping parameterMapping) {
        this.parameterMappings.add(parameterMapping);
    }


}
