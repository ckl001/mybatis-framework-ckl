package com.ckl.edu.mybatis.framework.ckl.builder;

import com.ckl.edu.mybatis.framework.ckl.config.Configuration;
import com.ckl.edu.mybatis.framework.ckl.io.Resources;
import com.ckl.edu.mybatis.framework.ckl.utils.DocumentUtils;
import org.apache.commons.dbcp.BasicDataSource;
import org.dom4j.Document;
import org.dom4j.Element;

import java.io.InputStream;
import java.util.List;
import java.util.Properties;

/**
 * @author chenkanglin
 * @desc
 *      解析全局配置文件（XML），获取Configuration对象
 * @Date 2020-09-14 10:23
 */
public class XMLConfigBuilder {


    private Configuration configuration;

    public XMLConfigBuilder() {
        this.configuration = new Configuration();
    }

    /***
     * description:
     *      从xml中解析出Configuration信息
     * @param rootElement
     * @return com.ckl.edu.mybatis.framework.ckl.config.Configuration
     */
    public Configuration parseConfiguration(Element rootElement) {

        //解析environments
        Element environments = rootElement.element("environments");
        parseEnvironments(environments);

        //解析Mapper
        Element mappers = rootElement.element("mappers");
        parseMappers(mappers);

        return configuration;
    }

    /***
     * description: 解析mappers标签
     *
     * @param mappers
     * @return void
     */
    private void parseMappers(Element mappers) {
        List<Element> mapperList = mappers.elements();
        for (Element mapper : mapperList) {
            // 获取 xml 路径
            String resource = mapper.attributeValue("resource");
            // 获取配置文件对应的流对象
            InputStream inputStream = Resources.getResourceAsStream(resource);
            // 获取配置文件对应的Document对象
            Document document = DocumentUtils.createDocument(inputStream);


            // 按照指定的语义去解析Document文档对象
            XMLMapperBuilder xmlMapperBuilder = new XMLMapperBuilder(configuration);
            xmlMapperBuilder.parseMapper(document.getRootElement());
        }
    }




    /***
     * description: 按照指定的语义去解析 Document 文档的 environments对象
     *
     * @param environments
     * @return void
     */
    private void parseEnvironments(Element environments) {
        //获取environments标签的 default 属性
        String aDefault = environments.attributeValue("default");

        //获取 default 的配置环境 （dev）
        List<Element> environmentList = environments.elements("environment");
        for (Element environment : environmentList) {
            String id = environment.attributeValue("id");
            if(aDefault.equals(id)){
                parseDataSource(environment);
            }
        }
    }

    /***
     * description: 解析数据源配置信息,并设置到 Configuration 类中
     *
     * @param environment
     * @return void
     */
    private void parseDataSource(Element environment) {
        Element dataSource = environment.element("dataSource");
        String type = dataSource.attributeValue("type");

        // 解析 property 标签并放到 Properties 类中
        Properties properties = parseProperties(dataSource);

        if("DBCP".equals(type)){
            BasicDataSource ds = new BasicDataSource();
            ds.setDriverClassName(properties.getProperty("db.driver"));
            ds.setUrl(properties.getProperty("db.url"));
            ds.setUsername(properties.getProperty("db.username"));
            ds.setPassword(properties.getProperty("db.password"));

            // 设置到 configuration 中
            configuration.setDataSource(ds);
        }
    }

    /***
     * description: 解析 dataSource -》property 节点属性
     *
     * @param dataSource
     * @return java.util.Properties
     */
    private Properties parseProperties(Element dataSource) {
        List<Element> propertyList = dataSource.elements("property");
        Properties properties = new Properties();
        for (Element property : propertyList) {
            String name = property.attributeValue("name");
            String value = property.attributeValue("value");
            properties.put(name,value);
        }
        return properties;
    }


}
