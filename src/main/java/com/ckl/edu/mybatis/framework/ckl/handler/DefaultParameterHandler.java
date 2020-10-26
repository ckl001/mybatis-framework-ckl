package com.ckl.edu.mybatis.framework.ckl.handler;

import com.ckl.edu.mybatis.framework.ckl.sqlsource.BoundSql;
import com.ckl.edu.mybatis.framework.ckl.sqlsource.ParameterMapping;
import com.ckl.edu.mybatis.framework.ckl.utils.SimpleTypeRegistry;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Map;

/**
 * @author chenkanglin
 * @desc
 * @Date 2020-09-14 21:23
 */
public class DefaultParameterHandler implements ParameterHandler{

    @Override
    public void setParameter(PreparedStatement preparedStatement, Object param, BoundSql boundSql) throws Exception {
        if(SimpleTypeRegistry.isSimpleType(param.getClass())){
            preparedStatement.setObject(1,param);
        }else if (param instanceof Map){
            Map map = (Map) param;
            //需要进行SQL解析之后，才会处理该部分内容,需要解析#{}才会得到参数列表
            List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
            for (int i = 0 ; i<parameterMappings.size() ;i++) {
                ParameterMapping parameterMapping = parameterMappings.get(i);
                String name = parameterMapping.getName();
                Object value = map.get(name);

                preparedStatement.setObject(i+1,value);
            }
        }
    }
}
