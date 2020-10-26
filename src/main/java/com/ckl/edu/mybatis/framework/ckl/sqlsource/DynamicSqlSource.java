package com.ckl.edu.mybatis.framework.ckl.sqlsource;

import com.ckl.edu.mybatis.framework.ckl.parser.SqlSourceParser;
import com.ckl.edu.mybatis.framework.ckl.sqlnode.DynamicContext;
import com.ckl.edu.mybatis.framework.ckl.sqlnode.SqlNode;

/**
 * @author chenkanglin
 * @desc
 *      封装和解析  带有 ${} 或者动态标签 SQL 信息
 *      SELECT * FROM USER WHERE id = ${id}
 *
 *      每一次根据参数的不同得到的sql不同
 *      SELECT * FROM USER WHERE name = ckl
 *      SELECT * FROM USER WHERE name = ckl01
 *
 *      解析时机：
 *      1、构造的时候 进行SQL解析（只会解析一次）
 *      2、每次调用getBoundSql的时候才会解析
 * @Date 2020-09-07 17:29
 */
public class DynamicSqlSource implements SqlSource{


    private SqlNode rootSqlNode;

    public DynamicSqlSource(SqlNode rootSqlNode) {
        this.rootSqlNode = rootSqlNode;
    }


    /***
     * description:
     *      每一次都需要重新解析sql语句
     * @param param 为了解析 ${} 需要的参数
     * @return com.ckl.edu.mybatisdemo.sqlsource.BoundSql
     */
    @Override
    public BoundSql getBoundSql(Object param) {
        // 1.解析SqlNode中的所有节点信息，最终会组成一条SQL语句(解析${}或者动态标签)
        DynamicContext context = new DynamicContext(param);
        // 解析 ${} 或者动态标签 SQL
        rootSqlNode.apply(context);
        // 2.解析#{}
        SqlSourceParser sqlSourceParser = new SqlSourceParser();
        SqlSource sqlSource = sqlSourceParser.parseSqlSource(context.getSql());
        return sqlSource.getBoundSql(param);
    }
}
