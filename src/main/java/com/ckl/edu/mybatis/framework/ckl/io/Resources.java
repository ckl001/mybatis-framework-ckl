package com.ckl.edu.mybatis.framework.ckl.io;

import java.io.InputStream;

/**
 * @author chenkanglin
 * @desc
 * @Date 2020-09-14 12:12
 */
public class Resources {


    /***
     * description: 获取配置文件对应的流对象
     *
     * @param location
     * @return java.io.InputStream
     */
    public static InputStream getResourceAsStream(String location){
        return Resources.class.getClassLoader().getResourceAsStream(location);
    }

}
