package com.ckl.edu.mybatis.framework.ckl.config;

import com.ckl.edu.mybatis.framework.ckl.sqlsource.SqlSource;
import lombok.Data;

/**
 * @author chenkanglin
 * @desc 存储映射文件中的crud标签的内容
 * @Date 2020-09-05 02:03
 */
@Data
public class MappedStatement {

    private String id;

    private String resultType;

    private String statementType;

    private SqlSource sqlSource;

    public MappedStatement(String id, String resultType, String statementType, SqlSource sqlSource) {
        this.id = id;
        this.resultType = resultType;
        this.statementType = statementType;
        this.sqlSource = sqlSource;
    }
}
