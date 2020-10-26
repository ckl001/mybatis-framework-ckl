package com.ckl.edu.mybatis.framework.ckl.factory;

import com.ckl.edu.mybatis.framework.ckl.sqlsession.SqlSession;

/**
 * @author chenkanglin
 * @desc
 * @Date 2020-09-14 11:45
 */
public interface SqlSessionFactory {


    SqlSession openSession();

}
