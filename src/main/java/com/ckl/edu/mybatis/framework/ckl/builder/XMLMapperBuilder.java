package com.ckl.edu.mybatis.framework.ckl.builder;

import com.ckl.edu.mybatis.framework.ckl.config.Configuration;
import com.ckl.edu.mybatis.framework.ckl.config.MappedStatement;
import com.ckl.edu.mybatis.framework.ckl.sqlsource.SqlSource;
import org.dom4j.Element;

import java.util.List;

/**
 * @author chenkanglin
 * @desc
 *      解析具体mapper映射文件
 * @Date 2020-09-14 10:52
 */
public class XMLMapperBuilder {

    private Configuration configuration;

    public XMLMapperBuilder(Configuration configuration) {
        this.configuration = configuration;
    }

    /***
     * description: <mapper></mapper>
     * 获取 select 标签的元素
     * @param rootElement
     * @return void
     */
    public void parseMapper(Element rootElement) {
        String nameSpace = rootElement.attributeValue("namespace");

        // TODO 映射文件中除了select标签还有很多其他的标签
        List<Element> selectElements = rootElement.elements("select");
        for (Element selectElement : selectElements) {
            XMLStatementBuilder xmlStatementBuilder = new XMLStatementBuilder(configuration);
            xmlStatementBuilder.parseStatement(nameSpace, selectElement);
        }

    }


}
