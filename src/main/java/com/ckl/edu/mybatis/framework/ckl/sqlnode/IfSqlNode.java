package com.ckl.edu.mybatis.framework.ckl.sqlnode;

import com.ckl.edu.mybatis.framework.ckl.utils.OgnlUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author chenkanglin
 * @desc
 *      存储带有 <if><if/> 标签的SQL文本信息
 *         <if test="username != null and username !='' ">
 *             AND username like '%${username}'
 *             <if test="username != null and username !=''">
 *                 AND 1=1
 *             </if>
 *         </if>
 *
 * @Date 2020-09-08 11:29
 */
public class IfSqlNode implements SqlNode {

    private String test;

    private SqlNode mixedSqlNode;

    public IfSqlNode(String test, SqlNode mixedSqlNode) {
        this.test = test;
        this.mixedSqlNode = mixedSqlNode;
    }

    @Autowired
    /***
     * description:
     *      if 标签
     *      使用Ognl判断test条件是否成立
     *      成立的话即继续拼接 其里面SqlNode（mixedSqlNode）
     * @param context
     * @return void
     */
    @Override
    public void apply(DynamicContext context) {
        Object parmeter = context.getBindings().get("_parameter");
        boolean aBoolean = OgnlUtils.evaluateBoolean(test, parmeter);
        if(aBoolean){
            mixedSqlNode.apply(context);
        }
    }
}
