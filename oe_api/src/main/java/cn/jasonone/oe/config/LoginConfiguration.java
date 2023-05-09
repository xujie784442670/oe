package cn.jasonone.oe.config;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.MD5;
import cn.jasonone.oe.handler.PasswordEncoder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoginConfiguration {
	@Bean
	@ConditionalOnMissingBean(PasswordEncoder.class)
	public PasswordEncoder passwordEncoder() {
		return new PasswordEncoder() {
			@Override
			public String encode(CharSequence rawPassword) {
				return MD5.create().digestHex(rawPassword.toString());
			}
			
			@Override
			public boolean matches(CharSequence rawPassword, String encodedPassword) {
				return encodedPassword.equals(encode(rawPassword));
			}
			
			@Override
			public String addSalt(CharSequence rawPassword, String salt) {
				return StrUtil.wrap(rawPassword, salt);
			}
			
			@Override
			public String createSalt() {
				return RandomUtil.randomString(6);
			}
		};
	}
}
