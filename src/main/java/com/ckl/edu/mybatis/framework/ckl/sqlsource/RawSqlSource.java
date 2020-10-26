package com.ckl.edu.mybatis.framework.ckl.sqlsource;

import com.ckl.edu.mybatis.framework.ckl.parser.SqlSourceParser;
import com.ckl.edu.mybatis.framework.ckl.sqlnode.DynamicContext;
import com.ckl.edu.mybatis.framework.ckl.sqlnode.SqlNode;

/**
 * @author chenkanglin
 * @desc
 *      封装和解析 不带有 ${} 或者动态标签的SQL信息
 *      SELECT * FROM USER id = #{id}
 *
 *      被封装的SQL信息，只需要解析一次，就可以得到以下的SQL语句
 *      SELECT * FROM user WHERE id = ?
 *
 *      解析时机：
 *      1、构造的时候进行SQL解析（只能解析一次）
 *      2、每次调用getBoundSql的时候才会解析
 *
 * @Date 2020-09-08 09:22
 */
public class RawSqlSource implements SqlSource {


    private SqlSource staticSqlSource;

    /***
     * description:
     *         #{username} 不需要解析 value 到 SQL 中去，所以不需要把 param 传进来
     *         而且 #{username} 在解析UserMppaer.xml配置文件的时候，就会被解析，所以仅在构造的时候解析一次
     *         eg： select * from user where username = #{username}
     *         构造后：
     *              select * from user where username = ？
     * @param mixedSqlNode
     * @return
     */
    public RawSqlSource(SqlNode mixedSqlNode) {
        //1、解析SqlSource中所有节点信息，最终会组成一条SQL语句
        DynamicContext context = new DynamicContext(null);

        //2、解析#{}
        String sqlText = context.getSql();
        SqlSourceParser sqlSourceParser = new SqlSourceParser();
        // 这个方法会构造出 new StaticSqlSource(sql,tokenHandler.getParameterMappings());
        // 同时会将 需要的参数名称 放到 StaticSqlSource 的List （parameterMappings）中，供 statement 入参使用
        sqlSourceParser.parseSqlSource(sqlText);
    }

    @Override
    public BoundSql getBoundSql(Object param) {
        return staticSqlSource.getBoundSql(param);
    }


}
