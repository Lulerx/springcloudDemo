package com.itle.chat_room;

import java.util.*;

/**
 * create by Luler on 2023/4/7 14:43
 *
 * @description
 */
public class CrazyMap<K,V> {
    //创建一个线程安全的 HashMap
    public Map<K,V> map = Collections.synchronizedMap(new HashMap<>());

    //根据 value 删除指定项
    public synchronized void removeByValue(Object value) {
        for (Object key : map.keySet()) {
            if (map.get(key) == value) {
                map.remove(key);
                break;
            }
        }
    }

    // 获取所有 value 组成的 set 集合
    public synchronized Set<V> valueSet(){
        Set<V> result = new HashSet<>();
        map.forEach(((k, v) -> result.add(v)));
        return result;
    }

    // 根据 key 查找 value
    public synchronized K getKeyByValue(V val){
        for (K key : map.keySet()) {
            if (map.get(key) == val || map.get(key).equals(val)) {
                return key;
            }
        }
        return null;
    }

    // 实现 put() 方法，不允许 value 重复
    public synchronized V put(K key, V value) {
        for (V val : valueSet()) {
            if (val.equals(val) && val.hashCode() == value.hashCode() ){
                throw new RuntimeException("MyMap 实例中不允许有重复 value ！");
            }
        }
        return map.put(key, value);
    }


}
