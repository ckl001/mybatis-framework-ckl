package com.ckl.edu.mybatis.framework.ckl.sqlnode;

/**
 * @author chenkanglin
 * @desc
 *      存储不带有${}的SQL文本信息
 * @Date 2020-09-08 11:32
 */
public class StaticTextSqlNode implements SqlNode {


    private String sqlText;

    public StaticTextSqlNode(String sqlText) {
        this.sqlText = sqlText;
    }

    @Override
    public void apply(DynamicContext context) {
        context.appendSql(sqlText);
    }
}
