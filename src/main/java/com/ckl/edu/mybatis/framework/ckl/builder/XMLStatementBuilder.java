package com.ckl.edu.mybatis.framework.ckl.builder;

import com.ckl.edu.mybatis.framework.ckl.config.Configuration;
import com.ckl.edu.mybatis.framework.ckl.config.MappedStatement;
import com.ckl.edu.mybatis.framework.ckl.sqlsource.SqlSource;
import org.dom4j.Element;

/**
 * @author chenkanglin
 * @desc
 *      解析Statement配置
 * @Date 2020-09-14 10:54
 */
public class XMLStatementBuilder {

    private Configuration configuration;
    public XMLStatementBuilder(Configuration configuration) {
        this.configuration = configuration;
    }


    /***
     * description: 存储映射文件中的crud标签的内容
     *
     * @param nameSpace
     * @param selectElement
     * @return void
     */
    void parseStatement(String nameSpace, Element selectElement) {

        String id =  selectElement.attributeValue("id");
        String statementId = nameSpace +"."+ id;
        String resultType = selectElement.attributeValue("resultType");
        String statementType = selectElement.attributeValue("statementType");

        // 封装SqlSource数据
        SqlSource sqlSource = createSqlSource(selectElement);

        // 封装MappedStatement
        MappedStatement mappedStatement = new MappedStatement(statementId,resultType,statementType,sqlSource);
        configuration.addMappedStatement(statementId, mappedStatement);
    }

    /***
     * description: 创建 SqlSource
     *
     * @param selectElement
     * @return com.ckl.edu.mybatisdemo.sqlsource.SqlSource
     */
    private SqlSource createSqlSource(Element selectElement) {
        XMLScriptBuilder scriptBuilder = new XMLScriptBuilder();
        SqlSource sqlSource = scriptBuilder.parseScriptNode(selectElement);
        return sqlSource;
    }


}
