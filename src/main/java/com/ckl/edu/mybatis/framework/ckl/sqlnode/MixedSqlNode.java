package com.ckl.edu.mybatis.framework.ckl.sqlnode;

import java.util.List;

/**
 * @author chenkanglin
 * @desc
 *      存储同级别下的多个SqlNode节点信息，方便统一处理
 * @Date 2020-09-08 11:21
 */
public class MixedSqlNode implements SqlNode {


    private List<SqlNode> sqlNodes;

    public MixedSqlNode(List<SqlNode> sqlNodes) {
        this.sqlNodes = sqlNodes;
    }


    /***
     * description:
     *      拼接同级别下的SqlNode
     * @param context
     * @return void
     */
    @Override
    public void apply(DynamicContext context) {
        for (SqlNode sqlNode : sqlNodes) {
            sqlNode.apply(context);
        }
    }
}
