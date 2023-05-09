package cn.jasonone.oe.handler;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.Duration;

@Component
public class CacheHandler {
	@Resource
	private RedisTemplate<String, String> redisTemplate;
	
	@Resource
	private Gson redisGson;
	
	/**
	 * 缓存数据+1
	 *
	 * @param key
	 */
	public void increment(String key) {
		redisTemplate.opsForValue().increment(key);
	}
	
	/**
	 * 缓存数据+1
	 *
	 * @param key
	 */
	public void increment(String key, Duration duration) {
		if (!redisTemplate.hasKey(key)) {
			set(key, 0, duration);
		}
		redisTemplate.opsForValue().increment(key);
		expire(key, duration); // 重置过期时间
	}
	
	/**
	 * 设置过期时间
	 *
	 * @param key      缓存key
	 * @param duration 过期时间
	 */
	public void expire(String key, Duration duration) {
		redisTemplate.expire(key, duration);
	}
	
	/**
	 * 获取缓存
	 *
	 * @param key
	 * @return
	 */
	public String get(String key) {
		return redisTemplate.opsForValue().get(key);
	}
	
	/**
	 * 获取缓存
	 *
	 * @param key
	 * @return
	 */
	public <T> T get(String key, Class<T> type) {
		return redisGson.fromJson(get(key), type);
	}
	
	public Integer getInteger(String key) {
		return get(key, Integer.class);
	}
	
	public Long getLong(String key) {
		return get(key, Long.class);
	}
	
	public Double getDouble(String key) {
		return get(key, Double.class);
	}
	
	public Float getFloat(String key) {
		return get(key, Float.class);
	}
	
	public Boolean getBoolean(String key) {
		return get(key, Boolean.class);
	}
	
	
	/**
	 * 设置缓存
	 *
	 * @param key
	 * @param value
	 */
	public void set(String key, Object value) {
		redisTemplate.opsForValue().set(key, redisGson.toJson(value));
	}
	
	/**
	 * 设置缓存
	 *
	 * @param key        键
	 * @param value      值
	 * @param expireTime 过期时间，单位秒
	 */
	public void set(String key, Object value, long expireTime) {
		redisTemplate.opsForValue().set(key, redisGson.toJson(value), Duration.ofSeconds(expireTime));
	}
	
	/**
	 * 设置缓存
	 *
	 * @param key        键
	 * @param value      值
	 * @param expireTime 过期时间，单位秒
	 */
	public void set(String key, Object value, Duration expireTime) {
		redisTemplate.opsForValue().set(key, redisGson.toJson(value), expireTime);
	}
	
	/**
	 * 删除缓存
	 *
	 * @param key
	 */
	public void delete(String key) {
		redisTemplate.delete(key);
	}
	
	/**
	 * 获取过期时间
	 *
	 * @param key
	 * @return
	 */
	public Duration getExpire(String key) {
		return Duration.ofSeconds(redisTemplate.getExpire(key));
	}
	
}
