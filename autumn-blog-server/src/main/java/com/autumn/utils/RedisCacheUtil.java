package com.autumn.utils;

import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 操作redis的工具类
 * @author huangfu
 */
@SuppressWarnings(value = {"unchecked", "rawtypes"})
@Component
public class RedisCacheUtil {
    private final RedisTemplate redisTemplate;

    public RedisCacheUtil(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * 设置缓存
     * @param key 缓存的key
     * @param value 缓存的value
     */
    public void setValue(String key,Object value){
        redisTemplate.opsForValue().set(key,value);
    }

    /**
     * 设置缓存 带超时时间的
     * @param key 缓存主键
     * @param value 缓存值
     * @param timeout 过期时间
     * @param timeUnit 时间单位
     */
    public void setValue(String key,Object value, Integer timeout, TimeUnit timeUnit){
        redisTemplate.opsForValue().set(key,value,timeout,timeUnit);
    }

    /**
     * 获取缓存的值
     * @param key 缓存的主键
     * @param <T> 返回对象
     * @return 缓存值
     */
    public <T> T getCacheObject(String key){
        ValueOperations<String,T> valueOperations = redisTemplate.opsForValue();
        return valueOperations.get(key);
    }

    /**
     * 删除一个缓存
     * @param key 缓存主键
     */
    public void removeCache(String key){
        redisTemplate.delete(key);
    }

    /**
     * 删除集合对象
     * @param collection 集合
     */
    public void removeCache(Collection collection) {
        redisTemplate.delete(collection);
    }

    /**
     * 缓存一个集合
     * @param key 主键
     * @param datas 数据集
     * @param <T>
     */
    public <T> void cacheList(String key, List<T> datas ){
        ListOperations listOperations = redisTemplate.opsForList();
        if(listOperations != null){
            datas.forEach(data ->{
                listOperations.leftPush(key,data);
            });
        }
    }

    /**
     * 获取一个集合
     * @param key
     * @param <T>
     * @return
     */
    public <T> List<T> getList(String key){
        List<T> datas = new ArrayList<>(8);
        ListOperations<String,T> listOperations = redisTemplate.opsForList();
        for (int i = 0; i < listOperations.size(key); i++) {
            datas.add(listOperations.index(key,i));
        }
        return datas;
    }

}
