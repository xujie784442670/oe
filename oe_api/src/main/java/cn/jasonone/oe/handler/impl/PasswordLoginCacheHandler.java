package cn.jasonone.oe.handler.impl;

import cn.jasonone.oe.config.properties.LoginConfigProperties;
import cn.jasonone.oe.handler.CacheHandler;
import com.google.gson.Gson;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.Duration;

@Component
public class PasswordLoginCacheHandler extends CacheHandler {
	private static final String CACHE_PREFIX = "password_login::";
	private static final String TRY_LOGIN_COUNT = CACHE_PREFIX + "try_login_count::";
	@Resource
	private LoginConfigProperties loginConfigProperties;
	
	/**
	 * 获取尝试登录次数
	 *
	 * @param username 用户名
	 * @return 尝试登录次数
	 */
	public int getTryLoginCount(String username) {
		String key = TRY_LOGIN_COUNT + username;
		Integer count = getInteger(key);
		return count == null ? 0 : count;
	}
	
	/**
	 * 设置尝试登录次数
	 *
	 * @param username 用户名
	 * @param count    尝试登录次数
	 */
	public void setTryLoginCount(String username, int count) {
		String key = TRY_LOGIN_COUNT + username;
		set(key, count, loginConfigProperties.getLockTime());
	}
	
	/**
	 * 尝试登录次数+1
	 *
	 * @param username 用户名
	 */
	public void incrTryLoginCount(String username) {
		String key = TRY_LOGIN_COUNT + username;
		Integer integer = getInteger(key);
		if (integer == null) {
			set(key, 0);
			return;
		}
		increment(key, Duration.ofSeconds(loginConfigProperties.getLockTime()));
	}
	
	/**
	 * 检查账号是否锁定
	 *
	 * @param username
	 * @return
	 */
	public boolean isLock(String username) {
		return getTryLoginCount(username) >= loginConfigProperties.getMaxTryCount();
	}
	
	/**
	 * 获取剩余锁定时间
	 * @param username  用户名
	 * @return 剩余锁定时间
	 */
	public Duration getLockTime(String username) {
		String key = TRY_LOGIN_COUNT + username;
		return getExpire(key);
	}
	
	/**
	 * 重置尝试登录次数
	 * @param username
	 */
	public void resetTryLoginCount(String username) {
		String key = TRY_LOGIN_COUNT + username;
		delete(key);
	}
}
