package com.ckl.edu.mybatis.framework.ckl.sqlnode;

import com.ckl.edu.mybatis.framework.ckl.utils.GenericTokenParser;
import com.ckl.edu.mybatis.framework.ckl.utils.OgnlUtils;
import com.ckl.edu.mybatis.framework.ckl.utils.SimpleTypeRegistry;
import com.ckl.edu.mybatis.framework.ckl.utils.TokenHandler;

/**
 * @author chenkanglin
 * @desc
 *      存储带有${}的SQL文本信息
 *
 *
 * @Date 2020-09-08 11:46
 */
public class TextSqlNode implements SqlNode {

    private String sqlText;

    public TextSqlNode(String sqlText) {
        this.sqlText = sqlText;
    }


    public boolean isDynamic(){
        return sqlText.indexOf("${")> -1 ;
    }

    /***
     * description:
     *      拼接带有${}标签的的SQL，并替换相关参数
     *      eg：
     *          select * from user where username like '%${username}'
     *          param: Map<String, Object>  ==> <"username","ckl">
     *
     *          通过字符串截断得到参数 username 字段名；然后在param中获取对应的 value 值；使用 value 替换掉 ${username}
     *          apply后：
     *               select * from user where username like '%ckl'
     * @param context
     * @return void
     */
    @Override
    public void apply(DynamicContext context) {
        // 通用分词解析器
        // openToken:
        // closeToken:
        // TokenHandler:被分出来的词要作何处理
        GenericTokenParser tokenParser = new GenericTokenParser("${","}",new BindingTokenHandler(context));

        // 使用通用分词解析器针对指定文本进行解析，解析之后得到JDBC可以执行的SQL语句
        String sql = tokenParser.parse(sqlText);

        context.appendSql(sql);
    }

    class BindingTokenHandler implements TokenHandler {

        private DynamicContext context;
        public BindingTokenHandler(DynamicContext context) {
            this.context = context;
        }

        /**
         * 解析${}
         * @param content
         * @return 参数的值（sql字符串拼接需要）
         */
        @Override
        public String handleToken(String content) {
            Object parameter = context.getBindings().get("_parameter");
            if (SimpleTypeRegistry.isSimpleType(parameter.getClass())) {
                return parameter.toString();
            }
            // 非简单类型的使用OGNL表达式去获取指定参数的值
            Object value = OgnlUtils.getValue(content, parameter);

            return value == null ? "" : value.toString();
        }
    }
}
