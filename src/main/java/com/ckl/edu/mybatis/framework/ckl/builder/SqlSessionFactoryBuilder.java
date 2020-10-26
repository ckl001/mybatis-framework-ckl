package com.ckl.edu.mybatis.framework.ckl.builder;

import com.ckl.edu.mybatis.framework.ckl.config.Configuration;
import com.ckl.edu.mybatis.framework.ckl.factory.DefaultSqlSessionFactory;
import com.ckl.edu.mybatis.framework.ckl.factory.SqlSessionFactory;
import com.ckl.edu.mybatis.framework.ckl.utils.DocumentUtils;
import org.dom4j.Document;

import java.io.InputStream;

/**
 * @author chenkanglin
 * @desc
 *      构建SqlSessionFactory
 *      SqlSessionFactoryBuilder#build：构建SqlSessionFactory
 * @Date 2020-09-14 10:21
 */
public class SqlSessionFactoryBuilder {


    public SqlSessionFactory builder(InputStream in) {
        // 获取配置文件对应的document对象
        Document document = DocumentUtils.createDocument(in);

        // 通过字节流封装Configuration
        XMLConfigBuilder configBuilder = new XMLConfigBuilder();
        Configuration configuration = configBuilder.parseConfiguration(document.getRootElement());
        return build(configuration);
    }


    private SqlSessionFactory build(Configuration configuration) {
        return new DefaultSqlSessionFactory(configuration);
    }
}
