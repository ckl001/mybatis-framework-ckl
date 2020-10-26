package com.ckl.edu.mybatis.framework.ckl.sqlsource;

/**
 * @author chenkanglin
 * @desc
 *      封装#{}解析出来的参数名称和参数类型
 * @Date 2020-09-07 17:11
 */
public class ParameterMapping {

    // 参数名称
    private String name;
    //参数类型
    private Class type;


    public ParameterMapping(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Class getType() {
        return type;
    }

    public void setType(Class type) {
        this.type = type;
    }
}
