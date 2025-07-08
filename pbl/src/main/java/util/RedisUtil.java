package util;

import redis.clients.jedis.JedisPooled;

public class RedisUtil {
    private static final JedisPooled JEDIS_POOLED = new JedisPooled("localhost", 6380);

    // 기본 10분
    public static void set(String key, String value) {
        set(key, value, 600);
    }

    public static void set(String key, String value, int expiry) {
        JEDIS_POOLED.setex(key, expiry, value); //setex 초 단위
    }

    public static String get(String key) {
        return JEDIS_POOLED.get(key);
    }

    public static Long ttl(String key) {
        return JEDIS_POOLED.ttl(key); // time to left 남은시간.
    }
    public static boolean exists(String key) {
        return JEDIS_POOLED.exists(key); // 키가 사라졌는지 확인
    }
    
    public static void remove(String key) { // 키 삭제 : 로그아웃
    	JEDIS_POOLED.del(key);
    }
    
}