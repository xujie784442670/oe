package cn.jasonone.oe.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
@Data
@Configuration
@ConfigurationProperties(prefix = "login")
public class LoginConfigProperties {
	/**
	 * 登录失败最大尝试次数
	 */
	private int maxTryCount = 10;
	
	/**
	 * 登录失败最大尝试次数后，锁定时间（单位：秒）
	 */
	private int lockTime = 600;
	
}
