package com.ckl.edu.mybatis.framework.ckl.sqlnode;

/**
 * @author chenkanglin
 * @Desc
 *      用于存储每个SQL片段，
 *      不同特点的SQL片段，封装成不同的SqlNode
 *
 *      MixedSqlNode： 封装成同级别的SqlNode集合
 *      TextSqlNode：  带有${}
 *      StaticTextSqlNode：不带有${}
 *      IfSqlNode： <if>标签
 *      WhereSqlNode： <where>标签
 *      ForEachSqlNode： <foreach>标签
 *
 *      解析 ${} 和 动态标签
 */
public interface SqlNode {

    void apply(DynamicContext context);

}
