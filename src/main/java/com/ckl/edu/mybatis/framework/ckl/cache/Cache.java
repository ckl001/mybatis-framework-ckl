package com.ckl.edu.mybatis.framework.ckl.cache;


/**
 * @author chenkanglin
 * @Desc
 *      缓存接口
 */
public interface Cache {

    Object get(String key);

    void put(String key,Object value);

}
