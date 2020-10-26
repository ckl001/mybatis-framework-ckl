package com.ckl.edu.mybatis.framework.ckl.sqlsession;

import java.util.List;

/**
 * @author chenkanglin
 * @desc
 * @Date 2020-09-14 11:45
 */
public interface SqlSession {

    /**
     * 查询信息集合
     * @param statementId
     * @param param
     * @param <T>
     * @return
     */
    <T> List<T> selectList(String statementId, Object param);

    /**
     * 查询单个信息
     * @param statementId
     * @param param
     * @param <T>
     * @return
     */
    <T> T selectOne(String statementId, Object param);


}
