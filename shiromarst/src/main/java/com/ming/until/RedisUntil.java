package com.ming.until;

import redis.clients.jedis.JedisPool;

import java.util.Properties;

public class RedisUntil {
    private RedisUntil(){}
    private static JedisPool jedisPool=null;
    public static JedisPool getJedis(){
        if(null == jedisPool){
            synchronized (RedisUntil.class){
                if(null == jedisPool){
                    Properties read = PropertiesUntil.read("dbconfig.properties");
                    String host=read.get("redis.host").toString();
                    int port=Integer.parseInt(read.get("redis.port").toString());
                    jedisPool=new JedisPool(host,port);
                }
            }
        }
        return jedisPool;
    }
}
