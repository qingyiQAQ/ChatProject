package com.example.chatproject.utils;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;

public class SnowFlakeUtil {

    private static final Snowflake idUtil = IdUtil.getSnowflake(1,1);

    public static long nextId(){
        return idUtil.nextId();
    }
}
