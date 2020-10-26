package com.ckl.edu.mybatis.framework.ckl.executor;

import com.ckl.edu.mybatis.framework.ckl.config.Configuration;
import com.ckl.edu.mybatis.framework.ckl.config.MappedStatement;
import com.ckl.edu.mybatis.framework.ckl.handler.StatementHandler;
import com.ckl.edu.mybatis.framework.ckl.sqlsource.BoundSql;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author chenkanglin
 * @desc
 *      最普通简单的执行器，常用的jdbc就是用它去完成
 * @Date 2020-09-14 20:38
 */
public class SimpleExecutor extends BaseExecutor {

    @Override
    protected <T> List<T> queryFromDataBase(Configuration configuration, MappedStatement mappedStatement, BoundSql boundSql, Object param) {
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        List<T> results = new ArrayList<>();
        try {
            // 获取连接
            connection = getConnection(configuration);

            // 根据传入的statementType获取对应的 statementHandler 对象
            StatementHandler statementHandler = configuration.newStatementHandler(mappedStatement.getStatementType());

            // 获取StatementType,创建Statement
            statement = statementHandler.prepare(connection, boundSql.getSql());

            // 设置参数
            statementHandler.parammeterize(statement, param, boundSql);

            // 执行Statement
            results = statementHandler.query(statement,mappedStatement);

            //结果映射
            return results;

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            // 8、释放资源
            if(rs != null){
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(statement != null){
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(connection != null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }



    private Connection getConnection(Configuration configuration) throws Exception {
        DataSource dataSource = configuration.getDataSource();
        return dataSource.getConnection();
    }
}
