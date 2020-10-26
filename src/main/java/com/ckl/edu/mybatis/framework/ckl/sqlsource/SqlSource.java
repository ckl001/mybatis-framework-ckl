package com.ckl.edu.mybatis.framework.ckl.sqlsource;

/**
 * @author chenkanglin
 * @Desc
 *      SqlSource接口
 *          解析被封装的SQL信息，获取JDBC可以执行的一条完整的【SQL语句】，
 *          但是带有 #{} 的SQL语句被解析时还需要保存解析出来的参数列表，
 *          所以我们使用一个类去组合【SQL语句】和【参数列表】--> BoundSQL
 *
 *      其实现类解析 #{}
 */
public interface SqlSource {


    /***
     * description:
     *
     * @param param 为了解析 ${} 需要的参数
     * @return com.ckl.edu.mybatisdemo.sqlsource.BoundSql
     */
    BoundSql getBoundSql(Object param);
}
