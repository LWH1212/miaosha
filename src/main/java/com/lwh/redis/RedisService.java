package com.lwh.redis;

import com.alibaba.fastjson.JSON;
import com.lwh.util.SerializeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;

@Service
public class RedisService {

    @Autowired
    JedisPool jedisPool;

    //获取当个对象
    public <T> T get(KeyPrefix prefix,String key,Class<T> clazz){
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String realKey = prefix.getPrefix()+key;
            String str = jedis.get(realKey);
            T t = stringToBean(str,clazz);
            return t;
        }finally {
            returnToPool(jedis);
        }
    }

    public Long expice(KeyPrefix prefix,String key,int exTime){
        Jedis jedis = null;
        Long result = null;
        try {
            jedis = jedisPool.getResource();
            result = jedis.expire(prefix.getPrefix()+key,exTime );
            return result;
        }finally {
            returnToPool(jedis);
        }
    }

    //设置对象
    public <T> boolean set(KeyPrefix prefix,String key,T value,int exTime){
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String str = beanToString(value);
            if (str == null || str.length()<=0){
                return false;
            }
            //生成真正的key
            String realKey = prefix.getPrefix()+key;
            if (exTime == 0){
                jedis.set(realKey,str);
            }else {
                jedis.setex(realKey,exTime,str);
            }
            return true;
        }finally {
            returnToPool(jedis);
        }
    }

    //设置List集合
    public void setList(String key, List<?> list,int exTime){
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            if (list == null || list.size() == 0){
                jedis.set(key.getBytes(),"".getBytes());
            }else if (exTime == 0){//如果list为空，则设置一个为空
                jedis.set(key.getBytes(), SerializeUtil.serializeList(list));
            }else {
                jedis.setex(key.getBytes(),exTime,SerializeUtil.serializeList(list));
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            returnToPool(jedis);
        }
    }

    //获取list集合
    public List<?> getList(String key){
        Jedis jedis = jedisPool.getResource();
        if (jedis == null || !jedis.exists(key)){
            return null;
        }
        byte[] data = jedis.get(key.getBytes());
        returnToPool(jedis);
        return SerializeUtil.unserializeList(data);
    }

    public Long del(KeyPrefix prefix,String key){
        Jedis jedis = null;
        Long result = null;
        try {
            jedis = jedisPool.getResource();
            result = jedis.del(prefix.getPrefix()+key);
            return result;
        }finally {
            returnToPool(jedis);
        }
    }

    public Long delRedis(String key){
        Jedis jedis = null;
        Long result ;
        try {
            jedis = jedisPool.getResource();
            result = jedis.del(key);
            return result;
        }finally {
            returnToPool(jedis);
        }
    }

    //判断key是否存在
    public <T> boolean exists(KeyPrefix prefix,String key){
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            //生成真正的key
            String realKey = prefix.getPrefix()+key;
            return jedis.exists(realKey);
        }finally {
            returnToPool(jedis);
        }
    }

    //增加值
    public <T> Long incr(KeyPrefix prefix,String key){
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            //生成真正的key
            String realKey = prefix.getPrefix()+key;
            return jedis.incr(realKey);
        }finally {
            returnToPool(jedis);
        }
    }

    //减少值
    public <T> Long decr(KeyPrefix prefix,String key){
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String realKey = prefix.getPrefix()+key;
            return jedis.decr(realKey);
        }finally {
            returnToPool(jedis);
        }
    }

    public static  <T> String beanToString(T value) {
        if (value == null){
            return null;
        }
        Class<?> clazz = value.getClass();
        if (clazz == int.class || clazz == Integer.class){
            return ""+value ;
        }else if (clazz == String.class){
            return (String)value;
        }else if (clazz == long.class || clazz == Long.class){
            return ""+value;
        }else {
            return JSON.toJSONString(value);
        }

    }

    public static  <T> T stringToBean(String str, Class<T> clazz) {
        if (str == null || str.length()<=0 || clazz == null){
            return null;
        }
        if (clazz == int.class || clazz == Integer.class){
            return (T)Integer.valueOf(str);
        }else if (clazz == String.class){
            return (T)str;
        }else if (clazz == long.class || clazz == Long.class){
            return (T)Long.valueOf(str);
        }else {
            return JSON.toJavaObject(JSON.parseObject(str),clazz);
        }
    }

    private void returnToPool(Jedis jedis) {
        if (jedis != null){
            jedis.close();
        }
    }

}
