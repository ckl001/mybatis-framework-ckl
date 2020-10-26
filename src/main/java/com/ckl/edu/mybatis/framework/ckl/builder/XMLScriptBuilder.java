package com.ckl.edu.mybatis.framework.ckl.builder;

import com.ckl.edu.mybatis.framework.ckl.sqlnode.*;
import com.ckl.edu.mybatis.framework.ckl.sqlsource.DynamicSqlSource;
import com.ckl.edu.mybatis.framework.ckl.sqlsource.RawSqlSource;
import com.ckl.edu.mybatis.framework.ckl.sqlsource.SqlSource;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.Text;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenkanglin
 * @desc
 *      解析动态标签
 * @Date 2020-09-14 10:53
 */
public class XMLScriptBuilder {


    private boolean isDynamic = false;


    /***
     * description: 解析封装 SqlSource
     *
     * @param selectElement
     * @return com.ckl.edu.mybatisdemo.sqlsource.SqlSource
     */
    public SqlSource parseScriptNode(Element selectElement) {
        // 1、解析动态标签，获取 SqlNode 信息
        SqlNode mixedSqlNode = parseDynamicTags(selectElement);

        // 2、封装 SqlSource
        SqlSource sqlSource = null;
        if(isDynamic){ //是否带有动态标签 或 ${}
            sqlSource = new DynamicSqlSource(mixedSqlNode);
        }else {
            sqlSource = new RawSqlSource(mixedSqlNode);
        }
        return sqlSource;
    }

    /***
     * description: 解析动态标签
     *
     * @param selectElement
     * @return com.ckl.edu.mybatisdemo.sqlnode.SqlNode
     */
    private SqlNode parseDynamicTags(Element selectElement) {
        List<SqlNode> sqlNodes = new ArrayList<>();

        int nodeCount = selectElement.nodeCount();

        for (int i = 0; i < nodeCount; i++) {
            Node node = selectElement.node(i);
            if(node instanceof Text){

                String text = node.getText().trim();
                if(StringUtils.isEmpty(text)){
                    continue;
                }
                TextSqlNode textSqlNode = new TextSqlNode(text);
                // 是否带有动态标签 或 ${}
                if(textSqlNode.isDynamic()){
                    isDynamic = true;
                    sqlNodes.add(textSqlNode);
                }else {
                    sqlNodes.add(new StaticTextSqlNode(text));
                }

            }else if(node instanceof Element){
                // 动态标签的处理逻辑 存在 if where foreach
                isDynamic = true;

                Element element = (Element) node;
                String name = element.getName();

                //TODO 各种标签的处理 --》 可以使用策略模式
                if("if".equals(name)){
                    String test = element.attributeValue("test");
                    //递归解析该标签
                    SqlNode mixedSqlNode = parseDynamicTags(element);
                    SqlNode ifSqlNode = new IfSqlNode(test, mixedSqlNode);

                    sqlNodes.add(ifSqlNode);
                }else if("where".equals(name)){
                    // XXX
                }


            }else{
                //XXX
            }
        }

        return new MixedSqlNode(sqlNodes);
    }


}
