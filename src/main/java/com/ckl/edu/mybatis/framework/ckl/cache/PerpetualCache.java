package com.ckl.edu.mybatis.framework.ckl.cache;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chenkanglin
 * @desc
 *      缓存
 * @Date 2020-09-14 20:19
 */
public class PerpetualCache implements Cache {

    private Map<String, Object > cache = new HashMap<>();


    @Override
    public Object get(String key) {
        return cache.get(key);
    }

    @Override
    public void put(String key, Object value) {
        cache.put(key,value);
    }
}
