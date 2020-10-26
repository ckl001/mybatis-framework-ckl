package com.ckl.edu.mybatis.framework.ckl.sqlnode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chenkanglin
 * @desc
 *      1、用于拼接SqlNode解析后的SQL片段
 *      2、用户传递解析SqlNode时需要的参数信息
 * @Date 2020-09-08 11:10
 */
public class DynamicContext {

    // 本次查询的 SQL 语句
    private StringBuffer sb = new StringBuffer();

    // 存放 SQL 语句 ${} 和 #{} 参数信息
    private Map<String, Object> bindings = new HashMap<>();

    public DynamicContext(Object param) {
        this.bindings.put("_parameter", param);
    }


    public String getSql() {
        return sb.toString();
    }

    /***
     * description:
     *      每解析完一个SqlNode后得到的SQL，拼接到 sb（当前SqlNode不需要解析或已经解析好的SQL ： select * from user where sex = #{sex}  ） 之后
     * @param sqlText
     * @return void
     */
    public void appendSql(String sqlText) {
        this.sb.append(sqlText);
        this.sb.append(" ");
    }

    public Map<String, Object> getBindings() {
        return bindings;
    }

}
